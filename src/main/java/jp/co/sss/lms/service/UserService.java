package jp.co.sss.lms.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.dto.UserDetailDto;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.MUser;
import jp.co.sss.lms.entity.TTemporaryPassStorage;
import jp.co.sss.lms.form.LoginForm;
import jp.co.sss.lms.repository.MLmsUserRepository;
import jp.co.sss.lms.repository.MUserRepository;
import jp.co.sss.lms.repository.TTemporaryPassStorageRepository;
import jp.co.sss.lms.util.DateUtil;
import jp.co.sss.lms.util.MessageUtil;
import jp.co.sss.lms.util.PasswordUtil;

//import jp.co.sss.lms.util.SettingUtil;

/**
 * ユーザーサービス
 * 
 * @author kasai
 */
@Service
public class UserService {

	@Autowired
	MUserRepository mUserRepository;
	@Autowired
	LoginService loginService;
	@Autowired
	HttpSession session;
	@Autowired
	TTemporaryPassStorageRepository tTemporaryPassStorageRepository;
	@Autowired
	private MLmsUserRepository mLmsUserRepository;
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private MessageUtil messageUtil;
	@Autowired
	private PasswordUtil passwordUtil;

	/**
	 * パスワード変更
	 *
	 */
	public String changePassword(LoginForm loginForm) {

		// 現在パスワードと変更後のパスワードが一致している場合
		// 変更後のパスワードと確認パスワードが不一致の場合はフロント側でチェックする

		// リポジトリから得た情報をエンティティに格納
		MUser mUser = mUserRepository.getOne(loginForm.getUserId());
		String hashedCurrentPassword = passwordUtil.getSaltedAndStrechedPassword(loginForm.getCurrentPassword(),
				mUser.getLoginId());
		// 現在パスワードチェック
		if (!mUser.getPassword().equals(hashedCurrentPassword)) {
			String currentPassword = "「" + messageUtil.getMessage("currentPassword") + "」";
			String registPassword = messageUtil.getMessage("registPassword");
			return messageUtil.getMessage("match", new String[] { currentPassword, registPassword });
		}

		mUser.setPassword(passwordUtil.getSaltedAndStrechedPassword(loginForm.getPassword(), mUser.getLoginId()));
		mUser.setPasswordChangeDate(dateUtil.stringToTimestamp(dateUtil.getCurrentDateString()));
		// 更新作業
		MUser newMUser = mUserRepository.save(mUser);
		
		// ログイン情報を取得し直し、画面に返す。

		return "";
	}

	/**
	 * パスワード変更(パスワード再設定用)
	 *
	 */
	public void changePasswordOfResetPassword(LoginForm loginForm) {
		// リポジトリから得た情報をエンティティに格納
		MUser mUser = mUserRepository.getOne(loginForm.getUserId());

		// パスワードとパスワード変更時間をセット
		mUser.setPassword(passwordUtil.getSaltedAndStrechedPassword(loginForm.getPassword(), mUser.getLoginId()));
		mUser.setPasswordChangeDate(dateUtil.stringToTimestamp(dateUtil.getCurrentDateString()));
		
		// リポジトリ更新
		mUserRepository.save(mUser);
	}

	/**
	 * メールアドレスからユーザー情報を取得
	 * 
	 * @param mailaddress
	 * @return MUser
	 */
	public MUser getMUser(String mailaddress) {
		return mUserRepository.findByMailAddress(mailaddress);
	}

	public MUser setMUser(MUser form) {
		MUser muser = mUserRepository.getOne(form.getUserId());
		muser.setPasswordChangeDate(null);
		muser.setLastModifiedDate(form.getLastModifiedDate());
		muser.setLastModifiedUser(muser.getUserId());

		return mUserRepository.save(muser);
	}

	public void getUserId(String password) {

		// ログインIDを元にユーザーマスタ情報を取得
		Integer UserId = (Integer) session.getAttribute("loginId");
		MUser mUser = mUserRepository.getOne(UserId);

		// ユーザーマスタ情報にパスワードと現在日時をセットする。
		Timestamp PasswordChangeDate = new Timestamp(new Date().getTime());

		mUser.setPassword(password);
		mUser.setPasswordChangeDate(PasswordChangeDate);

		mUserRepository.save(mUser);

	}

	/**
	 * ユーザー詳細画面のDTOを取得
	 * 
	 * @param lmsUserId LMSユーザーID
	 * @return ユーザー詳細画面のDTO
	 */
	public UserDetailDto getUserDetailDto(Integer lmsUserId) {
		UserDetailDto userDetailDto = new UserDetailDto();

		// 基本情報を取得
		MLmsUser mLmsUser = mLmsUserRepository.getUserDetailBasicInfo(lmsUserId);

		BeanUtils.copyProperties(mLmsUser, userDetailDto);
		BeanUtils.copyProperties(mLmsUser.getMUser(), userDetailDto);
		BeanUtils.copyProperties(mLmsUser.getTUserCompany().getMCompany(), userDetailDto);
		BeanUtils.copyProperties(mLmsUser.getTCourseUser().getMCourse(), userDetailDto);
		BeanUtils.copyProperties(mLmsUser.getTUserPlace().getMPlace(), userDetailDto);

		// ※試験・レポート・成果物情報に関しては工数が足りないため削減 2020/12/29 naraoka

		return userDetailDto;
	}
	
	/**
	 * パスワード再設定画面　ログインLMSユーザーDTOの処理
	 * @author sasaki
	 */
	public LoginUserDto setLoginUserDto(MLmsUser mLmsUser) {
		// ログインLMSユーザーDTOを作成
		LoginUserDto loginUserDto = new LoginUserDto();
		BeanUtils.copyProperties(mLmsUser, loginUserDto);
				
		// ロールが受講生(0001)の場合、情報をセット
		if (mLmsUser.getRole().equals("0001")) {
		loginUserDto.setCompanyId((mLmsUser.getTUserCompany().getCompanyId()));
		loginUserDto.setPlaceId(mLmsUser.getTUserPlace().getPlaceId());
		loginUserDto.setCourseId(mLmsUser.getTCourseUser().getCourseId());
		loginUserDto.setSupportAvailable(mLmsUser.getTUserPlace().getMPlace().getSupportAvailable());
		// DB内のファイル共有フラグがnullの場合はエラーになるので注意
		}
		
		// 講師権限(0002)以外かつ管理者権限(0004)以外の場合
		if (!(mLmsUser.getRole().equals("0002")) && !(mLmsUser.getRole().equals("0004"))) {
			// 企業マスタ情報からファイル共有フラグを取得
			loginUserDto.setFileShareFlg(mLmsUser.getTUserCompany().getMCompany().getFileShareFlg());
		}
				
		return loginUserDto; 
	}
}

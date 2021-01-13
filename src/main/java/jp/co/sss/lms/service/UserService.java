package jp.co.sss.lms.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public String changePassword(LoginForm loginForm, Integer userId) {

		// 現在パスワードと変更後のパスワードが一致している場合
		// 変更後のパスワードと確認パスワードが不一致の場合はフロント側でチェックする

		// リポジトリから得た情報をエンティティに格納
		MUser mUser = mUserRepository.getOne(userId);
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
	public void changePasswordOfResetPassword(LoginForm loginForm, Integer userId) {

		// 変更後のパスワードと確認パスワードが不一致の場合のチェックはフロントで行う。

		// リポジトリから得た情報をエンティティに格納
		MUser mUser = mUserRepository.getOne(userId);

		mUser.setPassword(passwordUtil.getSaltedAndStrechedPassword(loginForm.getPassword(), mUser.getLoginId()));
		mUser.setPasswordChangeDate(dateUtil.stringToTimestamp(dateUtil.getCurrentDateString()));
		// 更新作業
		MUser newMUser = mUserRepository.save(mUser);
		
		// ログイン情報を取得しなおして画面に返す。

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

	// パスワード変更情報を取得
	public TTemporaryPassStorage getPassStorage(String mailaddress) {

		MUser mUser = mUserRepository.findByMailAddress(mailaddress);
		Integer UserId = mUser.getUserId();

		return tTemporaryPassStorageRepository.findByUserId(UserId);
	}

	public TTemporaryPassStorage insertTTemporaryPassStorage(TTemporaryPassStorage tTemporarypassstorage) {
		// TemporaryPassStorageを取得
		TTemporaryPassStorage Temporarypassstorage = tTemporaryPassStorageRepository
				.getOne(tTemporarypassstorage.getTemporaryPassStorageId());

		// パスワード変更情報を作成・更新
		if (Temporarypassstorage == null) {
			TTemporaryPassStorage newTemporaryPassStorage = new TTemporaryPassStorage();
			newTemporaryPassStorage.setChangeKey(tTemporarypassstorage.getChangeKey());
			newTemporaryPassStorage.setTimeLimit(tTemporarypassstorage.getTimeLimit());
			newTemporaryPassStorage.setUserId(tTemporarypassstorage.getUserId());
			newTemporaryPassStorage.setDeleteFlg(tTemporarypassstorage.getDeleteFlg());
			newTemporaryPassStorage.setLastModifiedUser(tTemporarypassstorage.getLastModifiedUser());
			newTemporaryPassStorage.setLastModifiedDate(tTemporarypassstorage.getLastModifiedDate());
			newTemporaryPassStorage.setFirstCreateDate(tTemporarypassstorage.getFirstCreateDate());
			newTemporaryPassStorage.setFirstCreateUser(tTemporarypassstorage.getFirstCreateUser());

			return tTemporaryPassStorageRepository.save(newTemporaryPassStorage);

		} else {
			Temporarypassstorage.setChangeKey(tTemporarypassstorage.getChangeKey());
			Temporarypassstorage.setTimeLimit(tTemporarypassstorage.getTimeLimit());
			Temporarypassstorage.setUserId(tTemporarypassstorage.getUserId());
			Temporarypassstorage.setDeleteFlg(tTemporarypassstorage.getDeleteFlg());
			Temporarypassstorage.setLastModifiedUser(tTemporarypassstorage.getLastModifiedUser());
			Temporarypassstorage.setLastModifiedDate(tTemporarypassstorage.getLastModifiedDate());

			return tTemporaryPassStorageRepository.save(Temporarypassstorage);
		}

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
}

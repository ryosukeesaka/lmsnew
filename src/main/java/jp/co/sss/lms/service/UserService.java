package jp.co.sss.lms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.CompanyDto;
import jp.co.sss.lms.dto.LmsUserDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.dto.PlaceDto;
import jp.co.sss.lms.dto.UserCourseCompanyPlaceBasicInfoDto;
import jp.co.sss.lms.dto.UserDetailDto;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.MUser;
import jp.co.sss.lms.entity.UserCourseCompanyPlaceInfo;
import jp.co.sss.lms.form.CompanyAttendanceForm;
import jp.co.sss.lms.form.LoginForm;
import jp.co.sss.lms.repository.MLmsUserRepository;
import jp.co.sss.lms.repository.MUserRepository;
import jp.co.sss.lms.repository.TTemporaryPassStorageRepository;
import jp.co.sss.lms.repository.UserCourseCompanyPlaceBasicInfoRepository;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.DateUtil;
import jp.co.sss.lms.util.MessageUtil;
import jp.co.sss.lms.util.PasswordUtil;


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
	private UserCourseCompanyPlaceBasicInfoRepository userCourseCompanyPlaceBasicInfoRepository;
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
	
	
	
	/**
	 * メールアドレスからユーザー情報を取得
	 *   ついかーーーーーーーーーーーーーーーーーーーーーーーーーー
	 * @param mailaddress
	 * @return MUser
	 */
	public MUser getMUser(Integer userid) {
		return mUserRepository.findByUserId(userid);
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
	
	
	/**
	 * lmsユーザー情報を検索
	 * 
	 * @param form 入力フォーム
	 * @return lmsユーザー情報リスト
	 */
	public List<LmsUserDto> getUserListWithAddress (CompanyAttendanceForm form) {	
		List<LmsUserDto> lmsUserDtoList = new ArrayList<LmsUserDto>();
		
		// ユーザー情報を取得
		MLmsUser mLmsUserInfo = mLmsUserRepository.getUserWithCompany(Integer.parseInt(form.getUserId()));
		
		String courseName = "";
		String companyName = "";
		Integer placeId = 0;
		String userName = "";
		String role = "";
		Integer accountId = mLmsUserInfo.getAccountId();
		
		//入力フォームからの情報を変数に格納
		List<MLmsUser> mLmsUserList = new ArrayList<MLmsUser>();
		if(mLmsUserInfo.getRole().equals(Constants.CODE_VAL_ROLL_TEACHER)) {
			if(!StringUtils.isEmpty(form.getCourseName())) {
				courseName = form.getCourseName();
			}
			if(!StringUtils.isEmpty(form.getCompanyName())) {
				companyName = form.getCompanyName();
			}
			if(!Objects.isNull(form.getPlaceId())) {
				placeId = Integer.parseInt(form.getPlaceId());
			}
			if(!StringUtils.isEmpty(form.getUserName())) {
				userName = form.getUserName();
			}
			role = Constants.CODE_VAL_ROLL_STUDENT;
		}

		//検索フォームに入力した情報で検索処理
		mLmsUserList = mLmsUserRepository.findByStudentWithAddress(courseName, companyName, placeId, userName, role, accountId);
		
		//検索結果をlmsユーザー情報リストに格納
		for (MLmsUser mLmsUser : mLmsUserList) {
            lmsUserDtoList.add(getLmsUserDto(mLmsUser));
        }
	
	//lmsユーザー情報リストを返す	
	return lmsUserDtoList;
}
	
	public LmsUserDto getLmsUserDto(MLmsUser mLmsUser) {
    	LmsUserDto lmsUserDto = new LmsUserDto();
        BeanUtils.copyProperties(mLmsUser, lmsUserDto);
        BeanUtils.copyProperties(mLmsUser.getMUser(), lmsUserDto);
        
        //取得したlmsユーザー情報をDtoに格納する
        if(!Objects.isNull(mLmsUser.getMUser())) {
        	lmsUserDto.userName = mLmsUser.getMUser().getUserName();
        	lmsUserDto.userId = mLmsUser.getMUser().getUserId();
        }
        if (!Objects.isNull(mLmsUser.getTCourseUser())) {
            lmsUserDto.courseName = mLmsUser.getTCourseUser().getMCourse().getCourseName();
        }
        if (!Objects.isNull(mLmsUser.getTUserCompany()) && 
        		mLmsUser.getTUserCompany().getDeleteFlg().equals(Constants.DB_FLG_FALSE)) {
        	CompanyDto companyDto = new CompanyDto();
            companyDto.setCompanyName(mLmsUser.getTUserCompany().getMCompany().getCompanyName());
            lmsUserDto.setCompanyDto(companyDto);
        }
        if (!Objects.isNull(mLmsUser.getTUserPlace()) && 
        		mLmsUser.getTUserPlace().getDeleteFlg().equals(Constants.DB_FLG_FALSE)) {
        	PlaceDto placeDto = new PlaceDto();
            placeDto.setPlaceName(mLmsUser.getTUserPlace().getMPlace().getPlaceName());
            lmsUserDto.setPlaceDto(placeDto);
        }

        //lmsユーザー情報を格納したDtoを返す
        return lmsUserDto;
    }
	/**
	 * ユーザー一覧リストの取得	
	 * @author 梶山
	 * @param map<string,string>
	 * @return List <UserCourseCompanyPlaceBasicInfoDto> ユーザ、コース、企業、会場情報のdto List
	 * */
	public List<UserCourseCompanyPlaceBasicInfoDto> getList(Map<String,String>map) {
		List<UserCourseCompanyPlaceBasicInfoDto> list = new ArrayList<>();
		String placeId = map.get("placeId") ;
		String userName = map.get("userName");
		String companyName = map.get("companyName");
		String courseName = map.get("courseName");
		List<UserCourseCompanyPlaceInfo> userCourseCompanyPlaceInfoList =  userCourseCompanyPlaceBasicInfoRepository.searchUserCourseCompanyPlaceInfoListByForm(userName,courseName,companyName,placeId);

		for(UserCourseCompanyPlaceInfo info:userCourseCompanyPlaceInfoList) {	
				//nullチェック
				if(null == info.getCompanyId() || null== info.getCourseId() || null == info.getPlaceId()) {
					continue;
				}
				UserCourseCompanyPlaceBasicInfoDto userInfoDto = new UserCourseCompanyPlaceBasicInfoDto();
				BeanUtils.copyProperties(info,userInfoDto);
				list.add(userInfoDto);
		}
		return list;
	}
}

package jp.co.sss.lms.service;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.form.LoginForm;
import jp.co.sss.lms.repository.LoginRepository;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.LoggingUtil;
import jp.co.sss.lms.util.PasswordUtil;

@Service
public class LoginService {

	@Autowired
	private LoginRepository repository;
	@Autowired
	private PasswordUtil passwordUtil;
	@Autowired
	private LoggingUtil loggingUtil;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * ログイン情報取得
	 * @param loginForm
	 * @return LoginUserDto
	 * 
	 * @author 平澤 友乃
	 */
	public LoginUserDto getLoginInfo(LoginForm loginForm) {
		String saltPassword = passwordUtil.getSaltedAndStrechedPassword(loginForm.getPassword(), loginForm.getLoginId());
		MLmsUser mLmsUser = repository.findByLoginIdAndPassword(loginForm.getLoginId(), saltPassword);
		
		//ログイン情報が存在しない
		if (mLmsUser == null) {
			
			StringBuffer sb = new StringBuffer("ログインに失敗しました。");
			loggingUtil.appendLog(sb);
			logger.error(sb.toString());
			
			return null;
			
		} else {
			
			LoginUserDto loginUserDto = setLoginUserDto(mLmsUser);
			
			// 講師且つ非表示フラグが立っていた場合
			if(loginUserDto.getRole().equals(Constants.CODE_VAL_ROLL_TEACHER)&&
					loginUserDto.getHiddenFlg().equals(Constants.DB_HIDDEN_FLG_TRUE)) {
				
				StringBuffer sb = new StringBuffer("担当会場が終了しているため、ログインできません。詳しくは東京ITスクール運営事務局までお問い合わせください。");
				loggingUtil.appendLog(sb);
				logger.error(sb.toString());
				
			}else {
				
				// 上記以外
				StringBuffer sb = new StringBuffer("ログインしました。");
				loggingUtil.appendLog(sb);
				logger.info(sb.toString());
				
			}
			return loginUserDto;
		} 
	}

	// ログイン情報を設定
	private LoginUserDto setLoginUserDto(MLmsUser mLmsUser) {
		LoginUserDto loginUserDto = new LoginUserDto();
		BeanUtils.copyProperties(mLmsUser, loginUserDto);
		BeanUtils.copyProperties(mLmsUser.getMUser(), loginUserDto);

		// 受講生の情報をDTOに格納
		if (mLmsUser.getRole().equals(Constants.CODE_VAL_ROLL_STUDENT)) {
			loginUserDto.setCompanyId(mLmsUser.getTUserCompany().getCompanyId());
			loginUserDto.setPlaceId(mLmsUser.getTUserPlace().getPlaceId());
			loginUserDto.setCourseId(mLmsUser.getTCourseUser().getCourseId());
			loginUserDto.setSupportAvailable(mLmsUser.getTUserPlace().getMPlace().getSupportAvailable());

		// 講師の情報をDTOに格納
		} else if (mLmsUser.getRole().equals(Constants.CODE_VAL_ROLL_TEACHER)) {

			if (mLmsUser.getTUserPlace() != null) {
				loginUserDto.setPlaceId(mLmsUser.getTUserPlace().getPlaceId());
			}
			//担当会場が非表示になっていたらログイン不可とする
			if (mLmsUser.getTUserPlace().getMPlace().getHiddenFlg().equals(Constants.DB_HIDDEN_FLG_TRUE)) {
				loginUserDto.setLmsUserId(null);
				loginUserDto.setHiddenFlg(mLmsUser.getTUserPlace().getMPlace().getHiddenFlg());
			}else {
				loginUserDto.setHiddenFlg(Constants.DB_HIDDEN_FLG_FALSE);
			}
		}

		//パスワードに変更日がある場合
		if (mLmsUser.getMUser().getPasswordChangeDate() != null) {
			loginUserDto.setPasswordChangeDate(new Timestamp(mLmsUser.getMUser().getPasswordChangeDate().getTime()));
		}

		// 管理者権限、講師以外はファイル共有フラグ確認
		if(!mLmsUser.getRole().equals(Constants.CODE_VAL_ROLL_ADMIN) &&
				!mLmsUser.getRole().equals(Constants.CODE_VAL_ROLL_TEACHER)){
			loginUserDto.setFileShareFlg(mLmsUser.getTUserCompany().getMCompany().getFileShareFlg());
		}

		return loginUserDto;
	}
}

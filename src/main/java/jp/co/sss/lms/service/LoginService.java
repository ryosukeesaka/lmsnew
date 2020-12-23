package jp.co.sss.lms.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.repository.LoginRepository;
import jp.co.sss.lms.util.PasswordUtil;

@Service
public class LoginService {

	@Autowired
	private LoginRepository repository;
	@Autowired
	private PasswordUtil passwordUtil;
	@Autowired
	private HttpSession session;

	public LoginUserDto getLoginInfo(String loginId, String password) {
		String saltPassword = passwordUtil.getSaltedAndStrechedPassword(password, loginId);
		MLmsUser mLmsUser = repository.findByLoginIdAndPassword(loginId, saltPassword);
		if (mLmsUser == null) {
			return null;
		} else {
			return setLoginUserDto(mLmsUser);
		}
	}

	// ログイン情報を設定
	private LoginUserDto setLoginUserDto(MLmsUser mLmsUser) {
		LoginUserDto loginUserDto = new LoginUserDto();
		BeanUtils.copyProperties(mLmsUser, loginUserDto);
		BeanUtils.copyProperties(mLmsUser.getMUser(), loginUserDto);
		loginUserDto.setCompanyId(mLmsUser.getTUserCompany().getCompanyId());
		loginUserDto.setCompanyName(mLmsUser.getTUserCompany().getMCompany().getCompanyName());
		loginUserDto.setPlaceId(mLmsUser.getTUserPlace().getPlaceId());
		loginUserDto.setPlaceName(mLmsUser.getTUserPlace().getMPlace().getPlaceName());
		loginUserDto.setCourseId(mLmsUser.getTCourseUser().getCourseId());
		loginUserDto.setSupportAvailable(mLmsUser.getTUserPlace().getMPlace().getSupportAvailable());
		loginUserDto.setFileShareFlg(mLmsUser.getTUserCompany().getMCompany().getFileShareFlg());
		session.setAttribute("loginUserDto", loginUserDto);
		return loginUserDto;
	}

}

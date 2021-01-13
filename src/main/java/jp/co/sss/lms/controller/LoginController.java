package jp.co.sss.lms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.form.LoginForm;
import jp.co.sss.lms.service.LoginService;
import jp.co.sss.lms.util.LoggingUtil;

@RestController
@RequestMapping("")
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private LoggingUtil loggingUtil;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginUserDto> login(@RequestBody LoginForm loginForm) {
		
		LoginUserDto loginUserDto = loginService.getLoginInfo(loginForm);
		if (loginUserDto != null) {
			StringBuffer sb = new StringBuffer("ログインしました。");
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
		}

		return new ResponseEntity<>(loginUserDto, HttpStatus.OK);
		
	}

}

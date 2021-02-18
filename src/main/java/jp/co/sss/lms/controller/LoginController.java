package jp.co.sss.lms.controller;

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

@RestController
@RequestMapping("")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginUserDto> login(@RequestBody LoginForm loginForm) {
		
		LoginUserDto loginUserDto = loginService.getLoginInfo(loginForm);
		return new ResponseEntity<>(loginUserDto, HttpStatus.OK);
		
	}

}

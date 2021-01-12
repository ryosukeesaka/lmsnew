package jp.co.sss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.form.LoginForm;
import jp.co.sss.lms.service.UserService;

/**
 * パスワード変更コントローラー
 * 
 * @author
 */
@RestController
@RequestMapping("/password")
public class ChangePasswordController {

	@Autowired
	private UserService userService;

	/**
	 * 変更ボタン押下
	 * 
	 * @return
	 */
	@RequestMapping(value = "/changePassword/change", method = RequestMethod.POST)
	public ResponseEntity<String> change(@RequestBody LoginForm loginForm) {

		// 相関チェック
		String message = userService.changePassword(loginForm);
		if (!message.equals("")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(message, HttpStatus.OK);

	}
}

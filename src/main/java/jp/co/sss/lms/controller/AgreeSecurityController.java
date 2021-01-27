package jp.co.sss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.service.MUserService;

/**
 * 利用規約画面用コントローラー
 * 
 * @author Shimizu Takaaki
 */
@RestController
@RequestMapping("/user/agreeSecurity")
public class AgreeSecurityController {
	@Autowired
	private MUserService mUserService;

	/**
	 * 利用規約画面でセキュリティ同意フラグを押下
	 * 
	 * @return パスワード変更画面(利用規約に同意した場合)
	 * @return 利用規約画面 (利用規約に同意しない場合)
	 * @param model モデル
	 * @param form  ログインフォーム
	 */
	@RequestMapping(value = "/changePassword")
	public ResponseEntity<HttpStatus> index(@RequestParam("userId") Integer userId) {
		mUserService.update(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

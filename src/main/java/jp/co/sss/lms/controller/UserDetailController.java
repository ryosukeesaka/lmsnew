package jp.co.sss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.UserDetailDto;
import jp.co.sss.lms.service.UserService;

/**
 * ユーザー詳細コントローラー
 * 
 * @author naraoka
 */
@RestController
@RequestMapping("/user/detail")
public class UserDetailController {
	
	@Autowired
	private UserService userService;

	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @return ユーザー詳細画面
	 */
	@RequestMapping("")
	public ResponseEntity<UserDetailDto> index(@RequestParam("lmsUserId") Integer lmsUserId) {
		
		// DTO取得
		UserDetailDto userDetailDto = userService.getUserDetailDto(lmsUserId);

        return new ResponseEntity<>(userDetailDto, HttpStatus.OK);
	}

}

package jp.co.sss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.form.LmsUserForm;
import jp.co.sss.lms.service.UserService;

/**
 * パスワード再発行コントローラー
 * /password/reissuePassword
 * @author 牛久
 */
@RestController
@RequestMapping("/password")
public class ReissuePasswordController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/reissuePassword", method = RequestMethod.GET)
	public ResponseEntity<LmsUserForm> index(@RequestParam ("lmsUserId") String LmsUserId) {
		
		String loginId = userService.getMUser(LmsUserId).getLoginId();
		String rowPassword = userService.reissuePassword(LmsUserId);
		LmsUserForm form = new LmsUserForm();
		form.setLoginId(loginId);
		form.setPassword(rowPassword);
		form.setRole(userService.getMUser(LmsUserId).getMLmsUser().getRole());
		
//        // ユーザ登録の場合のみ
//        if(StringUtil.equals(Constants.CODE_VAL_ROLL_STUDENT, userForm.role) && LoginUserUtil.isAdmin() && (userForm.companyId != null || userForm.courseId != null)){
//           placeAssignId = userFacade.getPlaceAssignId(userForm);
//        }

		return new ResponseEntity<>(form, HttpStatus.OK);

	}
}

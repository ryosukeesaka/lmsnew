package jp.co.sss.lms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.MeetingDto;
import jp.co.sss.lms.dto.UserDetailDto;
import jp.co.sss.lms.form.MeetingForm;
import jp.co.sss.lms.service.MeetingService;
import jp.co.sss.lms.service.UserService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.LoggingUtil;
import jp.co.sss.lms.util.MessageUtil;

/**
 * MeetingControllerクラス
 *@author キョーソーリン
 */

@RestController
@RequestMapping("/meeting")
public class MeetingController {
	
	@Autowired
	private MeetingService meetingService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	HttpSession session;

	@Autowired
	private LoggingUtil loggingUtil;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 初期表示
	 * 
	 * @param role ログインユーザーロール
	 * @param lmsUserId 受講生LMSユーザID
	 */
	
	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public ResponseEntity<Map<String ,Object>> index(@RequestParam("role") String role,@RequestParam("lmsUserId") Integer lmsUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		//ログインしているユーザーが講師権限以外の場合	
		if(!Constants.CODE_VAL_ROLL_TEACHER.equals(role)) {
			String authorization = messageUtil.getMessage(Constants.VALID_KEY_AUTHORIZATION);
			StringBuffer sb = new StringBuffer(authorization);
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
		//ログインしているユーザーが存在しない場合
		if(role == null || role == ""){
			String alreadydelete = messageUtil.getMessage(Constants.VALID_KEY_ALREADYDELETE);
			StringBuffer sb = new StringBuffer(alreadydelete);
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
		//リクエストされたユーザー情報を取得する
		else {
			UserDetailDto userDetailDto = userService.getUserDetailDto(lmsUserId);
			map.put("lmsUserDto", userDetailDto);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}

	}
	

	/**
	 * 面談記録登録
	 * 
	 * @param 面談記録フォーム
	 * @param lmsUserId ログインユーザーLMSユーザID
	 */
	
	@RequestMapping(path = "/regist", method = RequestMethod.POST)
	public ResponseEntity<MeetingDto> regist(@RequestBody MeetingForm meetingForm,
			@RequestParam ("lmsUserId") Integer lmsUserId) {
		meetingService.regist(meetingForm,lmsUserId);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
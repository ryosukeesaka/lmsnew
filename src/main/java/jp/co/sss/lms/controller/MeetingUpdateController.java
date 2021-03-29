package jp.co.sss.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.dto.MeetingDto;
import jp.co.sss.lms.dto.UserDetailDto;
import jp.co.sss.lms.form.MeetingForm;
import jp.co.sss.lms.service.MeetingUpdateService;
import jp.co.sss.lms.service.UserService;
import jp.co.sss.lms.util.LoggingUtil;

/**
 * FileShareController
 * 面談コントローラ
 * @author 横山
 */

@RestController
@RequestMapping("/meeting/update")
public class MeetingUpdateController {
	@Autowired
	HttpSession session;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MeetingUpdateService meetingUpdateService;
	
	@Autowired
	LoginUserDto loginUserDto;
	
	@Autowired
	MeetingForm meetingForm;
	
	//@Autowired
	//LoggingUtil loggingUtil;
	
	//private final Logger logger = LoggerFactory.getLogger(getClass());
	
/*
 * 面談記録変更
 */
	@RequestMapping(value = "", method= { RequestMethod.POST})
	public ResponseEntity<MeetingDto> update(@RequestParam("meetingId") Integer meetingId) {
		//ユーザー詳細呼び出し
//		UserDetailDto userDetailDto = userService.getUserDetailDto(meetingId);
//		MeetingUpdateService.setValForm(meetingForm);
		
		// 面談記録の取得
//		MeetingDto meetingDto = meetingUpdateService.getMeetingDto(meetingId);
		MeetingDto meetingDto = new MeetingDto();

		return new ResponseEntity<>(meetingDto,HttpStatus.OK);
	}
}
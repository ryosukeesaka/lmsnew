package jp.co.sss.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.ExamDto;
import jp.co.sss.lms.dto.LoginLmsUserDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.form.ExamForm;
import jp.co.sss.lms.form.LoginForm;
import jp.co.sss.lms.service.ExamListService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.DateUtil;
import jp.co.sss.lms.util.SettingUtil;

/**
 * 試験一覧画面用コントローラー
 * 
 * @author Satoru Ushiku
 *
 */
@RestController

public class ExamListController {
	
	LoginUserDto loginUserDto;
	@Autowired
	ExamListService examListService;
	@Autowired
	DateUtil dateUtil;
	
	@RequestMapping(path = "/exam/list", method = RequestMethod.POST)
	
	public ResponseEntity<List<ExamDto>> index(@RequestBody LoginUserDto sessionDto) {
	  
	  List<ExamDto> examDtoList = new ArrayList<ExamDto>();
	  examDtoList = examListService.getExamDtoListFromSession(sessionDto); 
	  
	  return new ResponseEntity<>(examDtoList, HttpStatus.OK); 
	}
	
	 
}

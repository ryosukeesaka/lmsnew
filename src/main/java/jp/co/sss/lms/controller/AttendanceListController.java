package jp.co.sss.lms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.AttendanceManagementDto;
import jp.co.sss.lms.dto.CompanyAttendanceSearchDto;
import jp.co.sss.lms.dto.CompanyDto;
import jp.co.sss.lms.dto.CourseServiceCourseListDto;
import jp.co.sss.lms.dto.CourseServiceSectionDto;
import jp.co.sss.lms.dto.LmsUserDto;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.form.AttendanceForm;
import jp.co.sss.lms.form.CompanyAttendanceForm;
import jp.co.sss.lms.repository.MLmsUserRepository;
import jp.co.sss.lms.service.CompanyService;
import jp.co.sss.lms.service.CourseService;
import jp.co.sss.lms.service.UserService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.LoggingUtil;
import jp.co.sss.lms.util.MessageUtil;

/**
 * 勤怠情報確認コントローラ
 * 
 * @author 山木 翔
 */

@RestController
@RequestMapping("/attendance")
public class AttendanceListController {

	// @Autowired
	// サービスを呼び出す
	@Autowired
	private CourseService courseService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageUtil messageUtil;
	@Autowired
	private MLmsUserRepository mLmsUserRepository;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> index(@RequestParam("lmsUserId") Integer userId, @RequestParam("role") String role) {
		List<CourseServiceCourseListDto> courseServiceCourseListDto = new ArrayList<CourseServiceCourseListDto>();
		List<CompanyDto> companyDto = new ArrayList<CompanyDto>();
		if(role.equals(Constants.CODE_VAL_ROLL_TEACHER) || role.equals(Constants.CODE_VAL_ROLL_ADMIN)) {
			courseServiceCourseListDto = courseService.getCourseList(userId);
			companyDto = companyService.getCompanyDtoList(userId);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("courseServiceCourseListDto", courseServiceCourseListDto);
		map.put("companyDto", companyDto);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	
	@RequestMapping(path = "/list/search", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> search(@RequestBody CompanyAttendanceForm companyAttendanceForm) {
		Map<String, Object> map = new HashMap<>();
		List<LmsUserDto> lmsUserDto = new ArrayList<LmsUserDto>();
		String error = "";
		MLmsUser mLmsUserInfo = mLmsUserRepository.getUserWithCompany(Integer.parseInt(companyAttendanceForm.getUserId()));
		
		if(!StringUtils.isNumeric(companyAttendanceForm.getPlaceId())) {
			error = messageUtil.getMessage(Constants.VALID_KEY_INVALID);
			map.put("error", error);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}
		if(mLmsUserInfo.getRole().equals(Constants.CODE_VAL_ROLL_ADMIN) && 
			mLmsUserInfo.getTUserCompany().getMCompany().getCompanyName().matches(".*$.*")) {
			error = messageUtil.getMessage(Constants.VALID_KEY_SEARCHTOCOMPANYNAMEERROR);
			map.put("error", error);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}
		
		companyAttendanceForm.setRole(Constants.CODE_VAL_ROLL_STUDENT);
		lmsUserDto = userService.getUserListWithAddress(companyAttendanceForm);
		
		map.put("lmsUserDto", lmsUserDto);
		
		// 更新成功メッセージを登録して返す
		return new ResponseEntity<>(map, HttpStatus.OK);

	}
}

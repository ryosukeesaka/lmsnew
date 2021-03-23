package jp.co.sss.lms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import jp.co.sss.lms.form.AttendanceForm;
import jp.co.sss.lms.form.CompanyAttendanceForm;
import jp.co.sss.lms.service.CompanyService;
import jp.co.sss.lms.service.CourseService;
import jp.co.sss.lms.service.UserService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.LoggingUtil;

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
	public ResponseEntity<List<LmsUserDto>> search(@RequestBody CompanyAttendanceForm companyAttendanceForm) {
		//Map<String, Object> map = new HashMap<>();
		
		companyAttendanceForm.setRole(Constants.CODE_VAL_ROLL_STUDENT);
		List<LmsUserDto> lmsUserDto = userService.getUserListWithAddress(companyAttendanceForm);
		//List<CompanyAttendanceSearchDto> searchList = userService.getUserListWithAddress(companyAttendanceForm);
		
		
		// 更新成功メッセージを登録して返す
		return new ResponseEntity<>(lmsUserDto, HttpStatus.OK);

	}
}

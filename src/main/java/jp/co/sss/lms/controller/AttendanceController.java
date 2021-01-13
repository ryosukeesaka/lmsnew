package jp.co.sss.lms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.AttendanceManagementDto;
import jp.co.sss.lms.dto.CourseServiceSectionDto;
import jp.co.sss.lms.dto.StudentAttendanceDto;
import jp.co.sss.lms.service.CourseService;
import jp.co.sss.lms.service.StudentAttendanceService;

/**
 * 勤怠管理コントローラ
 * 
 * @author kurihara
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentAttendanceService studentAttendanceService;

	/**
	 * 勤怠管理画面の初期表示
	 * 
	 * @return List<AttendanceManagementDto>
	 */
	@RequestMapping(path = "/detail", method = RequestMethod.GET)
	public ResponseEntity<List<AttendanceManagementDto>> index(@RequestParam("courseId") Integer courseId, @RequestParam("lmsUserId") Integer lmsUserId) {

		// コースに紐付くセクション情報を取得
		List<CourseServiceSectionDto> courseServiceSectionDtoList = courseService
				.getSectionDtoList(courseId);

		// 生徒の勤怠情報を取得
		Map<String, StudentAttendanceDto> studentAttendanceDtoMap = studentAttendanceService
				.getStudentAttendanceDtoMap(lmsUserId);

		// 勤怠管理画面用のDTOを作成する
		// 引数としてcourseServiceSectionDtoListとstudentAttendanceDtoMapを渡す
		List<AttendanceManagementDto> attendanceManagementDtoList = studentAttendanceService
				.createAttendanceManagementDto(courseServiceSectionDtoList, studentAttendanceDtoMap);

		return new ResponseEntity<>(attendanceManagementDtoList, HttpStatus.OK);

	}

}
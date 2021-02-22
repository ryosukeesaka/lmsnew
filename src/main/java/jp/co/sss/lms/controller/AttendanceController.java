package jp.co.sss.lms.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.lms.dto.AttendanceManagementDto;
import jp.co.sss.lms.dto.CourseServiceSectionDto;
import jp.co.sss.lms.dto.StudentAttendanceDto;
import jp.co.sss.lms.service.CourseService;
import jp.co.sss.lms.service.StudentAttendanceService;
import jp.co.sss.lms.util.AttendanceUtil;

/**
 * 勤怠管理コントローラ
 * 
 * @author kurihara
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentAttendanceService studentAttendanceService;
	

	/**
	 * 勤怠管理画面の初期表示
	 * 
	 * @param model モデル
	 * @return 勤怠管理画面への遷移
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ResponseEntity<List<AttendanceManagementDto>> index(@RequestParam("courseId") Integer courseId,
			@RequestParam("lmsUserId") Integer lmsUserId){
		
		// コースに紐付くセクション情報を取得
		List<CourseServiceSectionDto> courseServiceSectionDtoList = courseService.getSectionDtoList(courseId);
		
		// 生徒の勤怠情報を取得
		Map<String, StudentAttendanceDto> studentAttendanceDtoMap = studentAttendanceService.getStudentAttendanceDtoMap(lmsUserId);
		
		// 勤怠管理画面用のDTOを作成する
		// 引数としてcourseServiceSectionDtoListとstudentAttendanceDtoMapを渡す
		List<AttendanceManagementDto> attendanceManagementDtoList = studentAttendanceService
				.createAttendanceManagementDto(courseServiceSectionDtoList, studentAttendanceDtoMap);
		
		return new ResponseEntity<>(attendanceManagementDtoList, HttpStatus.OK);
	}
	
	
	/**
	 *出勤ボタン押下後の処理
	 *@return 勤怠管理画面への遷移
	 */
	@RequestMapping(value="/punchIn", method = RequestMethod.GET)
	public String punchIn(int lmsUserId, int courseId, int accountId) {

		//本日日時を取得
		Date trainingDate = AttendanceUtil.getTrainingDate();
		
		studentAttendanceService.punchIn(lmsUserId, trainingDate, courseId, accountId);
		
		return "/attendance/detail/";
	}
	
	/**
	 *退勤ボタン押下後の処理
	 *@return 勤怠管理画面への遷移
	 */
	@RequestMapping(value = "/punchOut", method = RequestMethod.GET)
	public String punchOut(int lmsUserId, int courseId) {
		
		List<CourseServiceSectionDto> courseServiceSectionDtoList = courseService
				.getSectionDtoList(courseId);

		//本日日時を取得
		Date trainingDate = AttendanceUtil.getTrainingDate();
		
		studentAttendanceService.punchOut(lmsUserId, trainingDate, courseServiceSectionDtoList);
		
		return "/attendance/detail/";
	}	
	
}
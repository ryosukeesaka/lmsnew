package jp.co.sss.lms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.AttendanceManagementDto;
import jp.co.sss.lms.dto.CourseServiceSectionDto;
import jp.co.sss.lms.dto.StudentAttendanceDto;
import jp.co.sss.lms.form.AttendanceForm;
import jp.co.sss.lms.service.CourseService;
import jp.co.sss.lms.service.StudentAttendanceService;

/**
 * 勤怠情報直接編集コントローラ
 * 
 * @author 菅原
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceUpdateController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentAttendanceService studentAttendanceService;

	/**
	 * 勤怠情報直接編集画面の初期表示
	 * 
	 * @param courseId  コースID
	 * @param lmsUserId LMSユーザID
	 * @return 勤怠管理直接変更画面へ遷移
	 */
	@RequestMapping(path = "/update", method = RequestMethod.GET)
	public ResponseEntity<List<AttendanceManagementDto>> index(@RequestParam("courseId") Integer courseId,
			@RequestParam("lmsUserId") Integer lmsUserId) {

		// コースに紐付くセクション情報を取得
		List<CourseServiceSectionDto> courseServiceSectionDtoList = courseService.getSectionDtoList(courseId);

		// 生徒の勤怠情報を取得
		Map<String, StudentAttendanceDto> studentAttendanceDtoMap = studentAttendanceService
				.getStudentAttendanceDtoMap(lmsUserId);

		// 勤怠管理画面用のDTOを作成する
		// 引数としてcourseServiceSectionDtoListとstudentAttendanceDtoMapを渡す
		List<AttendanceManagementDto> attendanceManagementDtoList = studentAttendanceService
				.createAttendanceManagementDto(courseServiceSectionDtoList, studentAttendanceDtoMap);

		return new ResponseEntity<>(attendanceManagementDtoList, HttpStatus.OK);

	}

	/**
	 * 勤怠直接編集画面の更新
	 * 
	 * @param attendanceForm 勤怠情報のフォーム
	 * @return 勤怠情報直接編集画面の初期表示メソッドへ遷移
	 */
	@RequestMapping(path = "/attendance/update/complete", method = RequestMethod.GET)
	public ResponseEntity<String> complete(@RequestBody AttendanceForm attendanceForm,
			@RequestParam("courseId") Integer courseId, @RequestParam("lmsUserId") Integer lmsUserId) {

		String message;

		// 不正な入力が場合に更新メソッド
		message = studentAttendanceService.update(attendanceForm);
		// 更新成功メッセージを登録して返す
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

}
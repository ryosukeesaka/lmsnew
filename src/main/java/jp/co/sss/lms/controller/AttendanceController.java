package jp.co.sss.lms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.lms.dto.AttendanceManagementDto;
import jp.co.sss.lms.dto.CourseServiceSectionDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.dto.StudentAttendanceDto;
import jp.co.sss.lms.entity.TStudentAttendance;
import jp.co.sss.lms.service.CourseService;
import jp.co.sss.lms.service.StudentAttendanceService;
import jp.co.sss.lms.util.AttendanceUtil;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.MessageUtil;

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

	@Autowired
	private HttpSession session;


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
	 *納期に間に合わず、出勤ボタン押下後の処理は未実装
	 */
	@RequestMapping(value="/punchIn", params="punchIn", method = RequestMethod.GET)
	public ResponseEntity<List<AttendanceManagementDto>>  punchIn(Model model, @RequestParam("lmsUserId") Integer lmsUserId
			, @RequestParam("courseId") Integer courseId, @RequestParam("accountId") Integer accountId) {

		//本日日時を取得
		Date trainingDate = AttendanceUtil.getTrainingDate();
		System.out.println(lmsUserId);

		String errors = studentAttendanceService.validPunchIn(lmsUserId, trainingDate, courseId);
		String message = studentAttendanceService.punchIn(lmsUserId, trainingDate, courseId, accountId);


		//メッセージ
		//		model.addAttribute("message", message);
		//		model.addAttribute("error", errors);
		//		return "forward:/attendance/detail";

		//		return new ResponseEntity<>(message,HttpStatus.OK);

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
	 *納期に間に合わず、退勤ボタン押下後の処理は未実装
	 */
	@RequestMapping(value = "/punchOut", params="punchOut", method = RequestMethod.GET)
	public String punchOut(Model model) {

		LoginUserDto loginUserDto = (LoginUserDto) session.getAttribute("loginUserDto");
		List<CourseServiceSectionDto> courseServiceSectionDtoList = courseService
				.getSectionDtoList(loginUserDto.getCourseId());
		int lmsUserId = loginUserDto.getLmsUserId();
		//本日日時を取得
		Date trainingDate = AttendanceUtil.getTrainingDate();
		TStudentAttendance tStudentAttendance = new TStudentAttendance();
		MessageUtil messageUtil = new MessageUtil();
		String error = null;
		if (StringUtils.isNotBlank(tStudentAttendance.getTrainingEndTime())){
			error = messageUtil.getMessage(Constants.VALID_KEY_ATTENDANCE_PUNCHALREADYEXISTS);
			return error;
		}
		String errors = studentAttendanceService.validPunchOut(lmsUserId, trainingDate, courseServiceSectionDtoList);
		String message = studentAttendanceService.punchOut(lmsUserId, trainingDate, courseServiceSectionDtoList);
		//メッセージ
		model.addAttribute("message", message);
		model.addAttribute("error", errors);

		return "/attendance/detail/";
	}	

}
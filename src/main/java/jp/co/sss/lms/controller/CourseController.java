package jp.co.sss.lms.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.CourseServiceCourseDto;
import jp.co.sss.lms.service.CourseService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.LoggingUtil;

/**
 * CourseController コース詳細画面への画面遷移を行うコントローラー
 * 
 * @author 大串清
 */
@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	HttpSession session;
	@Autowired
	CourseService courseService;
	@Autowired
	LoggingUtil loggingUtil;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * ログインしているユーザーの権限、コースIDの状態に応じて 画面遷移とログ出力を行う行うメソッド
	 * 
	 * @return コース詳細情報
	 */
	@RequestMapping("/detail")
	public ResponseEntity<CourseServiceCourseDto> detail(@RequestParam("courseId") Integer courseId, @RequestParam("role") String role) {

		CourseServiceCourseDto courseServiceCourseDto = new CourseServiceCourseDto();
		HttpStatus httpStatus = HttpStatus.OK;

		if (!Constants.CODE_VAL_ROLL_STUDENT.equals(role)) {

			// 入力パラメータのチェックを行い、問題があった場合はmessageにエラーメッセージを代入する
			String message = courseService.getCourseInfo(courseId);

			if (!message.isEmpty()) {

				StringBuffer sb = new StringBuffer(message);
				loggingUtil.appendLog(sb);
				logger.info(sb.toString());

				httpStatus = HttpStatus.NOT_FOUND;

			}
			
		} else {

			// コース詳細関連情報の取得
			courseServiceCourseDto = courseService.getCourseDetail(courseId);

		}

		return new ResponseEntity<CourseServiceCourseDto>(courseServiceCourseDto, httpStatus);

	}
}

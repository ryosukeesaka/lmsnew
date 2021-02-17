package jp.co.sss.lms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.CategoryListDto;
import jp.co.sss.lms.dto.CourseListDto;
import jp.co.sss.lms.dto.CourseServiceCategoryDto;
import jp.co.sss.lms.dto.CourseServiceCourseDto;
import jp.co.sss.lms.dto.CourseUserServiceDto;
import jp.co.sss.lms.dto.CourseViewDto;
import jp.co.sss.lms.dto.MovieCategoryDto;
import jp.co.sss.lms.service.CourseService;
import jp.co.sss.lms.service.MUserService;
import jp.co.sss.lms.service.UserService;
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
	public ResponseEntity<CourseServiceCourseDto> detail(@RequestParam("courseId") String courseId, @RequestParam("role") String role) {

		CourseServiceCourseDto courseServiceCourseDto = new CourseServiceCourseDto();
		HttpStatus httpStatus = HttpStatus.OK;

		//受講生権限の場合
		if(Constants.CODE_VAL_ROLL_STUDENT.equals(role)) {
			// 入力パラメータのチェックを行い、問題があった場合はmessageにエラーメッセージを代入する
			String message = courseService.getCourseInfo(courseId);

			if (!message.isEmpty()) {
				StringBuffer sb = new StringBuffer(message);
				loggingUtil.appendLog(sb);
				logger.info(sb.toString());
				//Reponseステータスの変更
				httpStatus = HttpStatus.NOT_FOUND;
				return new ResponseEntity<CourseServiceCourseDto>(courseServiceCourseDto, httpStatus);

			} else {
				// コース詳細関連情報の取得
				courseServiceCourseDto = courseService.getCourseDetail(Integer.parseInt(courseId));
				return new ResponseEntity<CourseServiceCourseDto>(courseServiceCourseDto, httpStatus);
			}
			//受講生権限以外の場合
		}else {
			// 入力パラメータのチェックを行い、問題があった場合はmessageにエラーメッセージを代入する
			String message = courseService.getCourseInfo(courseId);

			if (!message.isEmpty()) {
				StringBuffer sb = new StringBuffer(message);
				loggingUtil.appendLog(sb);
				logger.info(sb.toString());
				//Reponseステータスの変更
				httpStatus = HttpStatus.NOT_FOUND;
				return new ResponseEntity<CourseServiceCourseDto>(courseServiceCourseDto, httpStatus);

			} else {
				// コース詳細関連情報の取得
				courseServiceCourseDto = courseService.getCourseDetail(Integer.parseInt(courseId));
				return new ResponseEntity<CourseServiceCourseDto>(courseServiceCourseDto, httpStatus);
			}			
		}
	}


	@RequestMapping("/list")
	public ResponseEntity<List<CourseViewDto>> index(@RequestParam("userId") Integer userId) {
		//Ⅰ.現在ログインしているLMSユーザの情報を取得する
		CourseUserServiceDto user=courseService.getUser(userId);
		//コース一覧
		List<CourseListDto> list = new ArrayList<CourseListDto>();
		//Ⅱ.取得したLMSユーザの権限が企業担当者、または育成担当者である場合			
		//企業IDをパラメータとし、下記サービスを利用し、コース情報リストを取得する		
		if(user.getRole().equals(Constants.CODE_VAL_ROLL_COMPANY)||user.getRole().equals(Constants.CODE_VAL_ROLL_TRAINING)) {
			//ひつよう？
			list=courseService.getCourseListByCompanyId(user.getCompanyId());
		}else {
			list=courseService.getCourseListByCompanyId(user.getCompanyId());
			//list=courseService.getCourseListByAccountCompanyId(user.getAccountId());
		}

		//	画面表示に必要な情報へと詰め替える
		List<CourseViewDto> view=new ArrayList<CourseViewDto>();

		//Ⅳ-1.コース情報リスト内にある各コース情報の開校日がNullでなく、かつ閉校日がNullでない場合	
		//	開校日と閉校日のパラメータを利用し、コース情報DTOの開校可否（isOpenCourse）に値を格納する	
		for(CourseListDto courselist:list) {
			if(courselist.getOpenTime()!=null||courselist.getCloseTime()!=null) {
				CourseViewDto courseview=new CourseViewDto();
				courseview.isOpenCourse=true;
				courseview.courseId = courselist.getCourseId();
				courseview.courseName = courselist.getCourseName();
				courseview.courseDescription = courselist.getCourseDescription();
				courseview.openTime = courselist.getOpenTime();
				courseview.closeTime =courselist.getCloseTime();
				courseview.courseType =courselist.getCourseType();
				courseview.CategoryDtoList=courselist.getCategoryDtoList();
				courseview.password = courselist.getPassword();
				courseview.teachingMaterialCount = courseService.countByteachingMaterialCount(courselist.getCourseId());

				view.add(courseview);
			}
		}
		//用意したデータをもとにコース一覧を表示する				
		return new ResponseEntity<>(view, HttpStatus.OK);
	}
}

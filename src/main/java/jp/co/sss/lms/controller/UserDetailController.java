package jp.co.sss.lms.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.DailyReportDto;
import jp.co.sss.lms.dto.DeliverablesResultDto;
import jp.co.sss.lms.dto.ExamDto;
import jp.co.sss.lms.dto.ExamServiceExamDto;
import jp.co.sss.lms.dto.ExamServiceExamResultDto;
import jp.co.sss.lms.dto.UserDetailDto;
import jp.co.sss.lms.form.ExamPlayForm;
import jp.co.sss.lms.repository.ExamRepository;
import jp.co.sss.lms.service.DeliverableService;
import jp.co.sss.lms.service.ExamService;
import jp.co.sss.lms.service.DailyReportSubmitService;
import jp.co.sss.lms.service.UserService;

/**
 * ユーザー詳細コントローラー
 * 
 * @author naraoka
 */
@RestController
@RequestMapping("/user/detail")
public class UserDetailController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ExamService examService;
	@Autowired
	private DeliverableService deliverableService;
	@Autowired
	private DailyReportSubmitService dailyReportSubmitService;
	
	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @return ユーザー詳細画面
	 */
	@RequestMapping("")
	public ResponseEntity<Map<String, Object>> index(@RequestParam("lmsUserId") Integer lmsUserId) {
		
		
		UserDetailDto userDetailDto = userService.getUserDetailDto(lmsUserId);
		List<ExamDto> examDto = examService.getExamDto(lmsUserId);
		List<DeliverablesResultDto> deliverablesResultDto = deliverableService.getDeliverablesResultDto(lmsUserId);
		List<DailyReportDto> dailyReportDto = dailyReportSubmitService.getDailyReportDto(lmsUserId);

		// 上で取得した試験結果情報をもとに試験結果詳細情報の項目を設定
		Map<String, Object> map = new HashMap<>();
		map.put("userDetailDto", userDetailDto);
		map.put("examDto", examDto);
		map.put("deliverablesResultDto", deliverablesResultDto);
		map.put("dailyReportDto", dailyReportDto);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
}

package jp.co.sss.lms.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import jp.co.sss.lms.dto.ExamServiceExamDto;
import jp.co.sss.lms.dto.ExamServiceExamResultDto;
import jp.co.sss.lms.form.ExamResultDetailForm;
import jp.co.sss.lms.service.ExamService;
import jp.co.sss.lms.util.MessageUtil;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.LoggingUtil;
/**
 * 試験詳細画面コントローラー 試験詳細画面から試験結果詳細画面への遷移と判定を行う
 *
 * @author 弥
 */
@RestController
public class ExamDetailController {
	@Autowired
	ExamService examService;
	@Autowired
	MessageUtil messageUtil;
	@Autowired
	LoggingUtil loggingUtil;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * チェックを先に行う 試験詳細画面に遷移
	 *
	 * @return 試験詳細画面&不正画面のURL
	 */
	@RequestMapping(path = "/exam/detail", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> index(@RequestBody ExamResultDetailForm examResultDetailForm) {
		// 試験情報を取得
		ExamServiceExamDto examDto = examService.getExam(examResultDetailForm.getExamId(), examResultDetailForm.getAccountId());
		// 平均点を取得するメソッドをここに入れる (getExamScoreAvg未更新)
		Double avgScore = examService.getExamScoreAvg(examResultDetailForm.getExamId());
		// 試験結果情報を取得する (examResultDetailForm未更新)
		List<ExamServiceExamResultDto> examResultDtoList = examService
				.getExamResult(examResultDetailForm.getExamSectionId(), examResultDetailForm.getLmsUserId(),
						examResultDetailForm.getAccountId());
		// DTOをビューに渡	す
		Map<String, Object> map = new HashMap<>();
		map.put("examDto", examDto);
		map.put("avgScore", avgScore);
		map.put("examResultDtoList", examResultDtoList);
		
		// examId・examSectionId・lmsUserIdが数値でない場合、400エラーを送信
		try {
			
			int examId = examResultDetailForm.getExamId();
			int examSectionId = examResultDetailForm.getExamSectionId();
			int lmsUserId = examResultDetailForm.getLmsUserId();
			return new ResponseEntity<>(map, HttpStatus.OK);
			
		} catch (NumberFormatException nfex) {
			
			// エラーメッセージを格納
			String message = messageUtil.getMessage(Constants.VALID_KEY_AUTHORIZATION);
			
			// エラーメッセージをログに出力
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.error(sb.toString());
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
	}
}
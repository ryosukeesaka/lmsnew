package jp.co.sss.lms.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import jp.co.sss.lms.dto.ExamServiceExamDto;
import jp.co.sss.lms.dto.ExamServiceExamResultDto;
import jp.co.sss.lms.form.ExamPlayForm;
import jp.co.sss.lms.service.ExamService;
import jp.co.sss.lms.util.MessageUtil;
/**
 * 試験開始コントローラー<br>
 *
 * 試験開始画面のコントローラー
 *
 * @author 上原 尚也
 *
 *
 */
@RestController
public class ExamStartController {
	@Autowired
	ExamService examService;
	@Autowired
	MessageUtil messageUtil;
	/**
	 * 試験開始画面初期表示
	 *
	 * @param form  試験情報form
	 * @param model
	 * @return 遷移情報
	 */
	@RequestMapping(path = "/exam/start", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> index(@RequestBody ExamPlayForm examPlayForm) {
		// 試験情報を取得
		ExamServiceExamDto examDto = examService.getExam(examPlayForm, examPlayForm.getLmsUserId());
		// 試験結果情報を取得
		List<ExamServiceExamResultDto> examResultDtoList = examService.getExamResult(examPlayForm.getExamSectionId(),
				examPlayForm.getLmsUserId(), examPlayForm.getAccountId());
		// DTOをビューに渡す
		Map<String, Object> map = new HashMap<>();
		map.put("examDto", examDto);
		map.put("examResultDtoList", examResultDtoList);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
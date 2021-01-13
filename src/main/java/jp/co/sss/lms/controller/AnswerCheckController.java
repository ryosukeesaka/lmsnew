package jp.co.sss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.ExamServiceExamDto;
import jp.co.sss.lms.form.ExamPlayForm;
import jp.co.sss.lms.service.ExamService;

/**
 * AnswerCheckController<br>
 * 解答確認画面のコントローラー<br>
 * 
 * @author higuchi
 */

@RestController
@RequestMapping("/exam")
public class AnswerCheckController {
	
	@Autowired
	ExamService examService;
	
	/**
	 * 初期表示
	 * 
	 * @param examPlayForm {@code Form} 試験問題フォーム
	 * @return ResponseEntity<ExamServiceExamDto>
	 */

	// URLが呼ばれたときの初期表示
	@PostMapping(value = "/answerCheck")
	public ResponseEntity<ExamServiceExamDto> index(@RequestBody ExamPlayForm examPlayForm, @RequestParam("lmsUserId") Integer lmsUserId) {

		// 試験情報.試験IDをパラメータとして試験情報サービス.試験情報取得のサービスを呼び出し、試験情報を取得する
		ExamServiceExamDto examServiceExamDto = examService.getExam(examPlayForm, lmsUserId);

		return new ResponseEntity<>(examServiceExamDto, HttpStatus.OK);
	}

}
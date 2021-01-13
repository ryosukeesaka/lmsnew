package jp.co.sss.lms.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.ExamServiceExamDto;
import jp.co.sss.lms.dto.ExamServiceExamResultDto;
import jp.co.sss.lms.service.ExamService;
import jp.co.sss.lms.form.ExamPlayForm;

/**
 * ExamResultController<br>
 * 試験結果画面のコントローラークラス<br>
 * 
 * @author kishida
 */
@RestController
public class ExamResultController {

	@Autowired
	ExamService examSerivce;

	/**
	 * 初期表示
	 *
	 * @param ExamPlayForm  {@code ExamPlayForm} 試験問題フォーム
	 * @param examSectionId {@code Integer} 試験・セクションID
	 * @return 試験結果画面
	 */
	@RequestMapping(value = "/exam/result")
	public ResponseEntity<Model> examResult(@RequestBody ExamPlayForm examPlayForm, @RequestParam("lmsUserId") Integer lmsUserId, @RequestParam("accountId") Integer accountId, @RequestParam("role") String role, Model model) {

		// ②．formをパラメータとして、試験情報サービスを呼び出し、試験結果登録を行う
		ExamServiceExamResultDto examResultDto = examSerivce.registExamResult(examPlayForm, lmsUserId, accountId, role);

		// ③．formをパラメータとして、試験情報サービス.試験問題・解答情報取得を呼び出し、試験結果情報を取得する
		ExamServiceExamDto examDto = examSerivce.getExamQuestionAndAnswer(examPlayForm, accountId);

		// ④．スコアを計算する（計算式 ：得点(score) / 問題数(numOfQuestion) * 100）
		double score = (examResultDto.getScore() / examDto.getNumOfQuestion()) * 100;

		// ⑤．④の結果の小数点を切り捨てる
		BigDecimal ResultScore = new BigDecimal(score);
		ResultScore = ResultScore.setScale(1, RoundingMode.DOWN);

		// ⑥．画面に表示する値を設定する
		model.addAttribute("examDto", examDto);
		model.addAttribute("score", ResultScore);
		model.addAttribute("answer", examPlayForm.getAnswer());
		model.addAttribute("questionDtoList", examDto.getQuestionDtoList());

		return new ResponseEntity<>(model, HttpStatus.OK);
	}

}

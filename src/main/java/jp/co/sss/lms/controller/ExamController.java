package jp.co.sss.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.ExamServiceExamDto;
import jp.co.sss.lms.dto.ExamServiceExamResultDto;
import jp.co.sss.lms.form.ExamPlayForm;

import jp.co.sss.lms.repository.TExamResultRepository;
import jp.co.sss.lms.service.ExamService;
import jp.co.sss.lms.util.LoggingUtil;
import jp.co.sss.lms.util.MessageUtil;

/**
 * ExamController 試験結果詳細画面へ遷移を行うコントローラー
 * 
 * @author 川上智代
 * 
 */

@RestController
public class ExamController {
	@Autowired
	ExamService examService;
	@Autowired
	TExamResultRepository repository;
	@Autowired
	MessageUtil messageUtil;
	@Autowired
	LoggingUtil loggingUtil;

	/**
	 * ログインしているユーザーの権限、試験結果IDの状態に応じて 画面遷移とログ出力を行う行うメソッド
	 * 
	 * 試験結果詳細画面 初期表示
	 * 
	 * @param model /exam/resultDetail 試験結果詳細画面に遷移
	 * @return /error エラー画面へ遷移
	 */

	@RequestMapping(path = "/exam/resultDetail", method = RequestMethod.POST)
	public ResponseEntity<Model> index(@RequestBody ExamPlayForm examPlayForm, Model model) {
		
		// 試験結果IDが数値でない場合のチェックはフロント側で実装する。

		// 試験情報サービス．試験結果情報取得
		ExamServiceExamDto examServiceExamDto = examService.getExam(examPlayForm.getExamId(), examPlayForm.getAccountId());
		ExamServiceExamResultDto singleExam = examService.getExamResultWithQuestion(examPlayForm.getExamResultId(), examPlayForm.getAccountId(), examPlayForm.getLmsUserId());
		List<ExamServiceExamResultDto> examResultDetailDto = examService.getExamResult(examPlayForm.getExamSectionId(), examPlayForm.getLmsUserId(), examPlayForm.getAccountId());

		// 上で取得した試験結果情報をもとに試験結果詳細情報の項目を設定
		model.addAttribute("examServiceExamDto", examServiceExamDto);
		model.addAttribute("singleExam", singleExam);
		model.addAttribute("examServiceExamResultDto", examResultDetailDto);

		return new ResponseEntity<>(model, HttpStatus.OK);
	}

}

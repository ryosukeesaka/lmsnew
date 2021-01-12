package jp.co.sss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.ExamServiceExamDto;
import jp.co.sss.lms.form.ExamPlayForm;
import jp.co.sss.lms.service.ExamService;

/**
 * ExamQuestionController 試験問題画面コントローラー
 * 
 * @author 染谷 悠太
 */

@RestController
public class ExamQuestionController {

	@Autowired
	ExamService examService;

	/**
	 * @param 入力先Form
	 * @param Model
	 * @return 試験問題画面
	 */
	@RequestMapping(path = "/exam/question", method = RequestMethod.POST)
	public ResponseEntity<Model> index(@RequestBody ExamPlayForm examPlayForm, Model model) {

		// サービスを呼ぶ（Idで検索し、Dtoにエンティティがセットされる ＊表示に必要なデータ）
		ExamServiceExamDto examServiceExamDto = examService.getExamQuestionAndAnswer(examPlayForm);

		// Dtoの値をビューに渡す
		model.addAttribute("examServiceExamDto", examServiceExamDto);

		// Formの値をセットできるようにする（submitする時）
		model.addAttribute("examPlayForm", examPlayForm);

		return new ResponseEntity<>(model, HttpStatus.OK);
	}

}

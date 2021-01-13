package jp.co.sss.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Model> index(@RequestBody ExamPlayForm examPlayForm,
			@RequestParam("lmsUserId") Integer lmsUserId, @RequestParam("accountId") Integer accountId, Model model) {

		// 試験情報を取得
		ExamServiceExamDto examDto = examService.getExam(examPlayForm, lmsUserId);

		// 取得した情報をビューに渡す
		model.addAttribute("examDto", examDto);

		// 試験結果情報を取得
		List<ExamServiceExamResultDto> examResultDtoList = examService.getExamResult(examPlayForm.getExamSectionId(),
				lmsUserId, accountId);

		// 取得した情報をビューに渡す
		model.addAttribute("examResultDtoList", examResultDtoList);

		return new ResponseEntity<>(model, HttpStatus.OK);

	}
}

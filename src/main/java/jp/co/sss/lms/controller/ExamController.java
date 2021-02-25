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
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.form.ExamPlayForm;
import jp.co.sss.lms.repository.TExamResultRepository;
import jp.co.sss.lms.service.ExamService;
import jp.co.sss.lms.util.Constants;
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
	@Autowired
	LoginUserDto loginUserDto;

private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * ログインしているユーザーの権限、
	 * 試験結果IDの状態に応じて 画面遷移とログ出力を行う行うメソッド
	 * 
	 * 試験結果詳細画面 初期表示
	 * 
	 * @param model /exam/resultDetail 試験結果詳細画面に遷移
	 * @return 不正アクセス画面へ遷移
	 */
	@RequestMapping(path = "/exam/resultDetail", method = RequestMethod.POST)
	public ResponseEntity<Map<String ,Object>> index(@RequestBody ExamPlayForm examPlayForm) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// エラーメッセージを格納
		String message = messageUtil.getMessage(Constants.VALID_KEY_AUTHORIZATION);				
		
		// 試験結果IDが数値でない場合、ログを出力しエラー画面へ遷移
		String srtExamResultId = String.valueOf(examPlayForm.getExamResultId());
		if (!isNumber(srtExamResultId)) {
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
		
		// ログインしているユーザが受講生権限の場合
		if (Constants.CODE_VAL_ROLL_STUDENT.equals(examPlayForm.getRole())) {
			
			// 試験情報サービス．試験結果情報取得
			List<ExamServiceExamResultDto> examResultDetailDto = examService.getExamResult(examPlayForm.getExamSectionId(), examPlayForm.getLmsUserId(), examPlayForm.getAccountId());
			
			// 上で取得したLMSユーザIDとログイン情報．LMSユーザIDが一致しない場合
			if (examResultDetailDto.get(0).getLmsUserId() != examPlayForm.getLmsUserId()){
				StringBuffer sb = new StringBuffer(message);
				loggingUtil.appendLog(sb);
				logger.info(sb.toString());
				return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
			}
		}			
		// 試験情報サービス．試験結果情報取得
		ExamServiceExamDto examServiceExamDto = examService.getExam(examPlayForm.getExamId(), examPlayForm.getAccountId());
		ExamServiceExamResultDto singleExam = examService.getExamResultWithQuestion(examPlayForm.getExamResultId(), examPlayForm.getAccountId(), examPlayForm.getLmsUserId());
		List<ExamServiceExamResultDto> examResultDetailDto = examService.getExamResult(examPlayForm.getExamSectionId(), examPlayForm.getLmsUserId(), examPlayForm.getAccountId());

		// 上で取得した試験結果情報をもとに試験結果詳細情報の項目を設定
		map.put("examServiceExamDto", examServiceExamDto);
		map.put("singleExam", singleExam);
		map.put("examServiceExamResultDto", examResultDetailDto);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	/**
	 * 数値かどうか判断するメソッド
	 * 
	 * 例外処理 整数型ではない場合false
	 */
	public boolean isNumber(String val) {
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}
}
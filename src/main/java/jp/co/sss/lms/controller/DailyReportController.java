package jp.co.sss.lms.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.DailyReportDto;
import jp.co.sss.lms.form.DailyReportDetailForm;
import jp.co.sss.lms.service.ReportService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.DateUtil;
import jp.co.sss.lms.util.LoggingUtil;

/**
 * DailyReportController
 * 
 * レポート詳細コントローラー
 * 
 * @author kawakubo
 */
@RestController
@RequestMapping("/dailyReport")
public class DailyReportController {

	@Autowired
	ReportService dailyReportService;
	@Autowired
	HttpSession session;
	@Autowired
	LoggingUtil loggingUtil;
	@Autowired
	DateUtil dateUtil;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 初期表示
	 * 
	 * @param dailyReportDetailForm レポート詳細フォーム
	 * @param userId ユーザID
	 * @param accountId 企業アカウントID
	 * @param role ロール
	 * @param lmsUserId LMSユーザID
	 * @return dailyReportDto レポート提出情報
	 */
	@RequestMapping(path = "/detail", method = RequestMethod.POST)
	public ResponseEntity<DailyReportDto> detail(@RequestBody DailyReportDetailForm dailyReportDetailForm,
			@RequestParam("userId") Integer userId, @RequestParam("accountId") Integer accountId,
			@RequestParam("role") String role, @RequestParam("lmsUserId") Integer lmsUserId) {

		HttpStatus httpStatus = HttpStatus.OK;

		// 日報提出IDの存在チェック
		String message = dailyReportService.checkDailyReportSubmitId(dailyReportDetailForm.getDailyReportSubmitId());

		if (!message.isEmpty()) {
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());

			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		} else {
			if (Constants.CODE_VAL_ROLL_STUDENT.equals(role)) {
				// ログインユーザのLMSユーザIDチェック
				message = dailyReportService.checkLmsUserId(dailyReportDetailForm.getDailyReportSubmitId(), lmsUserId);

				if (!message.isEmpty()) {
					StringBuffer sb = new StringBuffer(message);
					loggingUtil.appendLog(sb);
					logger.info(sb.toString());

					httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				}
			}
		}

		if (httpStatus == HttpStatus.INTERNAL_SERVER_ERROR) {
			return new ResponseEntity<DailyReportDto>(new DailyReportDto(), httpStatus);
		}

		// レポート情報サービス.レポート提出情報取得
		DailyReportDto dailyReportDto = dailyReportService
				.getDailyReportSubmit(dailyReportDetailForm.getDailyReportSubmitId(), userId, accountId);

		// レポート情報サービス.フィードバックコメント提出情報取得
		DailyReportDto dailyReportFbDtoList = dailyReportService.getDailyReportFeedbackSubmit(dailyReportDetailForm.getDailyReportSubmitId());
		dailyReportDto.setDailyReportFbDtoList(dailyReportFbDtoList.getDailyReportFbDtoList());

		return new ResponseEntity<DailyReportDto>(dailyReportDto, httpStatus);
	}

	/**
	 * フィードバックコメント登録
	 * 
	 * @param dailyReportDetailForm レポート詳細フォーム
	 * @param lmsUserId LMSユーザID
	 * @param accountId 企業アカウントID
	 * @return insertCount 登録件数
	 */
	@RequestMapping(path = "/result", method = RequestMethod.POST)
	public Integer insert(@RequestBody DailyReportDetailForm dailyReportDetailForm,
			@RequestParam("lmsUserId") Integer lmsUserId, @RequestParam("accountId") Integer accountId) {

		// 日報フィードバックコメント情報登録API
		Integer insertCount = dailyReportService.insertDailyReportFeedback(dailyReportDetailForm, lmsUserId, accountId);

		return insertCount;
	}

	/**
	 * フィードバックコメント削除
	 * 
	 * @param dailyReportFbId 日報フィードバックコメントID
	 * @param lmsUserId LMSユーザID
	 * @return deleteCount 削除件数
	 */
	@RequestMapping(value = "/delete")
	public Integer delete(@RequestParam("dailyReportFbId") Integer dailyReportFbId, @RequestParam("lmsUserId") Integer lmsUserId) {

		// 日報フィードバックコメント情報削除API
		Integer deleteCount = dailyReportService.deleteDailyReportFeedback(dailyReportFbId, lmsUserId);

		return deleteCount;
	}

}

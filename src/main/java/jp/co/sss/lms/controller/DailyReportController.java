package jp.co.sss.lms.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.DailyReportDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.form.DailyReportDetailForm;
import jp.co.sss.lms.service.ReportService;
import jp.co.sss.lms.util.DateUtil;
import jp.co.sss.lms.util.LoggingUtil;
import jp.co.sss.lms.util.LoginUserUtil;

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
	LoginUserUtil loginUserUtil;
	@Autowired
	LoginUserDto loginUserDto;
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
	 * @param result                入力チェック
	 * @param model                 モデル属性のホルダー
	 * @return /report/detail
	 */

	@RequestMapping(path = "/detail", method = RequestMethod.POST)
	public ResponseEntity<DailyReportDto> detail(@RequestBody DailyReportDetailForm dailyReportDetailForm) {

		HttpStatus httpStatus = HttpStatus.OK;

		// TODO 実装バグ
		// 日報提出ID(dailyReportSubmitId)が数値以外の場合、不正アクセス画面(illegal.html)へ遷移しない。
		// エラー情報を取得
		String message = dailyReportService.errorShow(dailyReportDetailForm.getDailyReportSubmitId());

		if (!message.isEmpty()) {
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());

			httpStatus = HttpStatus.NOT_FOUND;
		}

		if (loginUserUtil.isStudent()) {
			message = dailyReportService.erroerUserShow(dailyReportDetailForm.getDailyReportSubmitId());

			if (!message.isEmpty()) {
				StringBuffer sb = new StringBuffer(message);
				loggingUtil.appendLog(sb);
				logger.info(sb.toString());

				httpStatus = HttpStatus.NOT_FOUND;
			}
		}
		if (httpStatus == HttpStatus.NOT_FOUND) {
			return new ResponseEntity<DailyReportDto>(new DailyReportDto(), httpStatus);
		}

		// TODO 実装バグ
		// dailyReportSubmitIdの値が
		// ・1の場合、report/detail.htmlでは企業担当者フィールドバック以外が出力される。
		// 本来であれば、企業担当者側のフィードバックと受講者側の情報全てが出力される。
		// ・2の場合、週報が出力される。
		// dailyReportSubmitIdではなく、dailyReportIdを検索対象として
		// 出力している可能性がある。
		// レポート情報サービス.レポート提出情報取得
		DailyReportDto dailyReportDto = dailyReportService.getDailyReportSubmit(
				dailyReportDetailForm.getDailyReportSubmitId(), loginUserDto.getUserId(),
				/* date, */ loginUserDto.getAccountId());
//		model.addAttribute("dailyReportDto", dailyReportDto);
//		model.addAttribute("intelligibilityDto", dailyReportDto.getIntelligibilityDtoList());

		// レポート情報サービス.フィードバックコメント提出情報取得
//		DailyReportDto dailyReportDto = dailyReportService.getDailyReportFeedbackSubmit(dailyReportDetailForm.getDailyReportSubmitId());
//		model.addAttribute("dailyReportFbDto", dailyReportDto.getDailyReportFbDtoList());

		return new ResponseEntity<DailyReportDto>(dailyReportDto, httpStatus);
	}

	/**
	 * フィードバックコメント登録
	 * 
	 * @param dailyReportDetailForm レポート詳細フォーム
	 * @param result                入力チェック
	 * @param model                 モデル属性のホルダー
	 * @return /report/detail
	 */
	@RequestMapping(path = "/result", method = RequestMethod.POST)
	public Integer insert(@RequestBody DailyReportDetailForm dailyReportDetailForm) {

		// TODO 実装バグ
		// フィードバック登録後に更新ボタンを押下で再度登録される。
		// ボタン押下又はページを更新するたびに、DailyReportController.javaへ遷移し、
		// フィールドバック登録機能であるdailyReportService.insertDailyReportが実行されてしまうため。
		// レポート情報サービス.フィードバックコメント情報登録
		Integer insertCount = dailyReportService.insertDailyReportFeedback(dailyReportDetailForm);

		return insertCount;
	}

	/**
	 * フィードバックコメント削除
	 * 
	 * @param dailyReportDetailForm レポート詳細フォーム
	 * @param model                 モデル属性のホルダー
	 * @return /report/detail
	 */
	// TODO 実装バグ
	// フィードバック登録後に戻るボタンで登録したものが消える。
	@RequestMapping(value = "/delete")
	public Integer delete(@RequestBody DailyReportDetailForm dailyReportDetailForm,
			@RequestParam Integer dailyReportSubmitId, @RequestParam Integer dailyReportFbId, Model model) {

		// 日報フィードバックコメント情報削除API
		Integer deleteCount = dailyReportService.dailyReportFbId(dailyReportFbId);

//		// レポート情報サービス.レポート提出情報取得
//		dailyReportDto = dailyReportService.getDailyReportSubmit(dailyReportSubmitId,  loginUserDto.getUserId(),/*date,*/ loginUserDto.getAccountId());
//		model.addAttribute("dailyReportDto", dailyReportDto);
//		model.addAttribute("intelligibilityDto", dailyReportDto.getIntelligibilityDtoList());
//
//		// レポート情報サービス.フィードバックコメント提出情報取得
//		dailyReportDto = dailyReportService.getDailyReportFeedbackSubmit(dailyReportSubmitId);
//		model.addAttribute("dailyReportFbDto", dailyReportDto.getDailyReportFbDtoList());

		return deleteCount;
	}

}

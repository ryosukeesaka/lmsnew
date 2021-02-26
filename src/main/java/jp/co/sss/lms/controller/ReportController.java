package jp.co.sss.lms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.LoggingUtil;
import jp.co.sss.lms.util.MessageUtil;
import jp.co.sss.lms.dto.*;
import jp.co.sss.lms.entity.MDailyReportDetail;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.TDailyReportSubmit;
import jp.co.sss.lms.entity.TIintelligibility;
import jp.co.sss.lms.form.ReportForm;
import jp.co.sss.lms.service.DailyReportService;;

/**
 * レポート登録画面の初期表示と登録処理
 *@author 福田,牛久,小松
 */
@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	LoginUserDto loginUserDto;
	@Autowired
	LoggingUtil loggingUtil;
	@Autowired
	MessageUtil messageUtil;
	@Autowired
	HttpSession session;
	@Autowired
	DailyReportService DailyReportService;

	private final Logger logger = LoggerFactory.getLogger(getClass()); // ログ出力用
	public DailyReportDto dailyReportDto;
	public IntelligibilityDto tIintelligibilityDto;

	/**
	 *レポート登録画面の初期表示 
	 *@param reportForm
	 *@return DailyReportDto
	 */
	@RequestMapping(path = "/index", method = RequestMethod.POST)
	public ResponseEntity<DailyReportDto> index(@RequestBody ReportForm reportForm) {
		// ログインユーザ情報取得
		loginUserDto = DailyReportService.getMLmsUser(reportForm.getLmsUserId());
		Integer accountId = loginUserDto.getAccountId();
		Integer userId;

		String errorMessage = getReportInfo(reportForm);

		if (!errorMessage.isEmpty()) {
			// エラーメッセージが空でない場合, ログに出力して不正アクセス画面に遷移
			StringBuffer sb = new StringBuffer(errorMessage);
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());

			HttpStatus httpStatus = HttpStatus.NOT_FOUND;
			return new ResponseEntity<DailyReportDto>(dailyReportDto, httpStatus);
		}
		
		// MDailyReportを取得してdailyReportDtoに格納する
		dailyReportDto = DailyReportService.setMDailyReport(reportForm, accountId, dailyReportDto);
		
		Date date = stringToDate(reportForm.getDate());

		if (Constants.CODE_VAL_ROLL_STUDENT.equals(reportForm.getRole())) {
			// ロールが受講生の場合, loginUserDtoのLmsUserIdをユーザIdに格納する
			userId = loginUserDto.getLmsUserId();
		} else {
			// 受講生でない場合, reportFormのLmsUserIdをユーザIdに格納する
			userId = reportForm.getLmsUserId();
		}
		
		// TDailyReportSubmitを取得する
		TDailyReportSubmit tDailyReportSubmit = DailyReportService.getTDailyReportSubmit(reportForm.getDailyReportId(),new Timestamp(date.getTime()), userId);
		
		dailyReportDto = DailyReportService.createDailyReportDTO(tDailyReportSubmit, dailyReportDto, date);

		HttpStatus httpStatus = HttpStatus.OK;
		return new ResponseEntity<DailyReportDto>(dailyReportDto, httpStatus);
	}

	/**
	 *レポート登録
	 *@param reportForm
	 * 
	 */
	@RequestMapping(path = "/regist", method = RequestMethod.POST)
	public String regist(@RequestBody ReportForm reportForm) {
		Date date = stringToDate(reportForm.getDate());

		// 入力チェック
		if(!DailyReportService.Valid(reportForm,date)) {
			loginUserDto = DailyReportService.getMLmsUser(reportForm.getLmsUserId());
			getDailyReportDto(reportForm,loginUserDto);
		}
		
		// 登録・更新・削除処理
//		DailyReportService.registDailyReport(reportForm, date);
		
//		Integer dailyReportId = reportForm.getDailyReportId();
//		Integer lmsUserId = reportForm.getLmsUserId();
		
		return "ok";
		
	}
	/**
	 *エラーメッセージの取得 
	 *@param reportForm
	 *@return 日付がnullまたは日報Idがnullであり,且つDailyReportSubmitが0件以上であればエラーメッセージ
	 *         それ以外は空文字  
	 */
	public String getReportInfo(ReportForm reportForm) {
		Integer lmsUserId = loginUserDto.getLmsUserId();
		Integer dailyReportId = reportForm.getDailyReportId();
		Date date = this.stringToDate(reportForm.getDate());
		Timestamp timestamp = new Timestamp(date.getTime());

		if (date == null) {
			// 日付がnullの場合, エラーメッセージを返す
			return "メッセージID：already.report";
		}
		if (dailyReportId == null) {
			// 日報Idがnullの場合, DailyReportSubmitの件数を取得し,0件以上であればエラーメッセージを返す
			Integer count = DailyReportService.getDailyReportSubmit(dailyReportId, lmsUserId, timestamp,
					Constants.DB_FLG_FALSE);
			if (count > 0)
				return "メッセージID：already.report";
		}
	
		return "";
	}

	/**
	 * 日付文字列をDate型に変換
	 * @param getDate 
	 * @return trueならdate型の日付, falseならnewDate
	 */
	private Date stringToDate(String getDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(getDate);
			return date;
		} catch (ParseException e) {
			return new Date();
		}
	}

	/**
	 * 文字列をInteger型に変換
	 * @param String[] str
	 * @return trueまたはfalse
	 * */
	private boolean isNumeric(String[] str) {
		try {
			for (String num : str) {
				Integer.parseInt(num);
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// TODO javadoc
	/**
	 * DailyReportDto取得処理
	 * @param reportForm, loginUserDto
	 * @return DailyReportDto
	 * 
	 * */
	private DailyReportDto getDailyReportDto(ReportForm reportForm, LoginUserDto loginUserDto) {
		Integer accountId = reportForm.getAccountId();
		Integer userId;

		DailyReportService.setMDailyReport(reportForm, accountId, dailyReportDto);
		Date date = stringToDate(reportForm.getDate());

		if (Constants.CODE_VAL_ROLL_STUDENT.equals(reportForm.getRole())) {
			// ロールが受講生の場合, loginUserDtoのLmsUserIdをユーザIdに格納する
			userId = loginUserDto.getLmsUserId();
		} else {
			// 受講生でない場合, reportFormのLmsUserIdをユーザIdに格納する
			userId = reportForm.getLmsUserId();
		}

		TDailyReportSubmit reportinfo = DailyReportService.getTDailyReportSubmit(reportForm.getDailyReportId(),
				new Timestamp(date.getTime()), userId);

			dailyReportDto = DailyReportService.createDailyReportDTO(reportinfo, dailyReportDto, date);
		return dailyReportDto;
	}
}

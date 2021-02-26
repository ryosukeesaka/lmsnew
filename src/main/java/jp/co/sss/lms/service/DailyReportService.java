package jp.co.sss.lms.service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.DailyReportDto;
import jp.co.sss.lms.dto.IntelligibilityDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.entity.MDailyReport;
import jp.co.sss.lms.entity.MDailyReportDetail;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.TCourseDailyReport;
import jp.co.sss.lms.entity.TDailyReportSubmit;
import jp.co.sss.lms.entity.TDailyReportSubmitDetail;
import jp.co.sss.lms.entity.TIintelligibility;
import jp.co.sss.lms.entity.TSectionDailyReport;
import jp.co.sss.lms.form.ReportForm;
import jp.co.sss.lms.repository.MDailyReportDetailRepository;
import jp.co.sss.lms.repository.MLmsUserRepository;
import jp.co.sss.lms.repository.TCourseDailyReportRepository;
import jp.co.sss.lms.repository.TDailyReportSubmitDetailRepository;
import jp.co.sss.lms.repository.TDailyReportSubmitRepository;
import jp.co.sss.lms.repository.TIntelligibilityRepository;
import jp.co.sss.lms.repository.TSectionDailyReportRepository;
import jp.co.sss.lms.util.Constants;

/**
 * レポート登録画面の初期表示と登録のサービス
 * @author 小松由佳
 */
@Service
public class DailyReportService {

	@Autowired
	TDailyReportSubmitRepository sRepository;
	@Autowired
	TDailyReportSubmitDetailRepository tDailyReportSubmitDetailRepository;
	@Autowired
	TCourseDailyReportRepository tCourseDailyReportRepository;
	@Autowired
	MDailyReportDetailRepository mDailyReportDetailRepository; 
	@Autowired
	TSectionDailyReportRepository tSectionDailyReportRepository;
	@Autowired
	MDailyReportService mDailyReportService;
	@Autowired
	TIntelligibilityRepository tIntelligentRepository;
	@Autowired
	MLmsUserRepository mLmsUserRepository;

	
	/**
	 * ユーザ情報取得
	 * @param lmsUserId
	 * @return LoginUserDto
	 * */
	public LoginUserDto getMLmsUser(Integer lmsUserId) {
		LoginUserDto loginUserDto = new LoginUserDto();
		MLmsUser mLmsUser = mLmsUserRepository.findByUserId(lmsUserId);
		loginUserDto.setAccountId(mLmsUser.getAccountId());
		loginUserDto.setLmsUserId(mLmsUser.getLmsUserId());
		
		return loginUserDto;
	}
	
	/**
	 * 日報提出情報を取得
	 * 
	 * @param dailyReportId, date, lmsUserId, accountId
	 * @return tDailyReportSubmit
	 *
	 */
	public TDailyReportSubmit getTDailyReportSubmit(Integer dailyReportId, Timestamp date, Integer lmsUserId) {

		TDailyReportSubmit tDailyReportSubmit = sRepository.findByLmsUserIdAndDate(lmsUserId, date, dailyReportId,Constants.DB_FLG_FALSE);
		return tDailyReportSubmit;
	}

	/**
	 * 日報詳細情報をDailyReportDtoに格納
	 * 
	 * @param mDailyReportList
	 * @param DailyReportDto
	 * @return DailyReportDto
	 * 
	 */
	public DailyReportDto MDailyReportInDailyReportDto(MDailyReport mDailyReport,Integer courseId) {

		DailyReportDto dailyReportDto = new DailyReportDto();

		List<String> name = new ArrayList<>();
		List<Integer> row = new ArrayList<>();
		List<Integer> clm = new ArrayList<>();
		List<String> requireCheck = new ArrayList<>();
		List<String> type = new ArrayList<>();
		List<String> rangefrom = new ArrayList<>();
		List<String> rangeto = new ArrayList<>();

		for (MDailyReportDetail mDailyReportDetail : mDailyReport.getMDailyReportDetailList()) {
			name.add(mDailyReportDetail.getFieldName());
			row.add(mDailyReportDetail.getRow());
			clm.add(mDailyReportDetail.getClm());
			requireCheck.add(mDailyReportDetail.getRequiredFlg().toString());
			type.add(mDailyReportDetail.getInputType().toString());

			if (mDailyReportDetail.getRangeFrom() == null) {
				// 範囲fromがnullである場合, rangefromにnullを格納
				rangefrom.add(null);
			} else {
				rangefrom.add(mDailyReportDetail.getRangeFrom().toString());
			}
			if (mDailyReportDetail.getRangeTo() == null) {
				// 範囲toがnullである場合, rangetoにnullを格納
				rangeto.add(null);
			} else {
				rangeto.add(mDailyReportDetail.getRangeTo().toString());
			}
		}
		dailyReportDto.setFieldName(name);
		dailyReportDto.setRow(row);
		dailyReportDto.setClm(clm);
		dailyReportDto.setRequireCheck(requireCheck);
		dailyReportDto.setType(type);
		dailyReportDto.setRangeFrom(rangefrom);
		dailyReportDto.setRangeTo(rangeto);
		
		dailyReportDto.setDailyReportId(mDailyReport.getDailyReportId());
		dailyReportDto.setCourseId(courseId);
		dailyReportDto.setSectionDailyReportId(mDailyReport.getTSectionDailyReportList().get(0).getSectionDailyReportId()); // セクション・日報紐づけID
		dailyReportDto.setReportName(mDailyReport.getReportName()); // 日報名
		dailyReportDto.setFileName(mDailyReport.getFileName()); // ファイル名
		dailyReportDto.setSheetName(mDailyReport.getSheetName()); // シート名
		dailyReportDto.setRowCompany(mDailyReport.getRowCompany()); // 社名出力行番号
		dailyReportDto.setClmCompany(mDailyReport.getClmCompany()); // 社名出力列番号
		dailyReportDto.setRowUser(mDailyReport.getRowUser()); // ユーザ名出力行番号
		dailyReportDto.setClmUser(mDailyReport.getClmUser()); // ユーザ名出力列番号
		dailyReportDto.setRowDate(mDailyReport.getRowDate()); // 日付出力行番号
		dailyReportDto.setClmDate(mDailyReport.getClmDate()); // 日付出力列番号
		dailyReportDto.setIntelligibilityFlg(mDailyReport.getIntelligibilityFlg()); // 学習理解度入力フラグ
		dailyReportDto.setIntelligibilityFieldNum(mDailyReport.getIntelligibilityFieldNum()); // 学習理解度項目数
		dailyReportDto.setIntelligibilityNum(mDailyReport.getIntelligibilityNum()); // 学習理解度数
		dailyReportDto.setRowIntelFld(mDailyReport.getRowIntelFld()); // 理解度項目出力開始行番号
		dailyReportDto.setClmIntelFld(mDailyReport.getClmIntelFld()); // 理解度項目出力開始列番号
		dailyReportDto.setRowIntelFld(mDailyReport.getRowIntel()); // 理解度出力開始行番号
		dailyReportDto.setClmIntel(mDailyReport.getClmIntel()); // 理解度出力開始列番号

		return dailyReportDto;
	}

	/**
	 * 日報提出情報を取得できた場合,DailyReportDtoに格納
	 * 取得できなかった場合, 学習理解度とコンテンツの空リストをdailyReportDtoに格納
	 * 
	 * @param tDailyReportSubmit
	 * @return DailyReportDto
	 */
	public DailyReportDto createDailyReportDTO(TDailyReportSubmit tDailyReportSubmit, DailyReportDto dailyReportDto,Date date) {
		List<String> contents = new ArrayList<>();
		List<IntelligibilityDto> intelligents = new ArrayList<>();
		
		if ( tDailyReportSubmit != null) {
			// 日報提出情報がnullでない場合, dailyReportDtoに日報提出情報を格納
			dailyReportDto.setDailyReportSubmitId(tDailyReportSubmit.getDailyReportSubmitId());
			dailyReportDto.setUserName(tDailyReportSubmit.getMLmsUser().getMUser().getUserName());
			dailyReportDto.setDate(new Date(tDailyReportSubmit.getDate().getTime()));

			dailyReportDto.settDailyReportSubmitDetailFirstId(
					tDailyReportSubmit.getTDailyReportSubmitDetailList().get(0).getDailyReportSubmitDetailId());

			if (tDailyReportSubmit.getTDailyReportSubmitDetailList() != null) {
				// 日報提出詳細リスト情報がnullでない場合, contentsにコンテンツ内容を格納
				for (TDailyReportSubmitDetail tDailyReportSubmitDetail : tDailyReportSubmit.getTDailyReportSubmitDetailList()) {
					contents.add(tDailyReportSubmitDetail.getContent());
				}
			}

			if (tDailyReportSubmit.getTIintelligibilityList() != null && !tDailyReportSubmit.getTIintelligibilityList().isEmpty()) {
				// 学習理解度リスト情報がnullでなく, 且つ学習理解度リストが空でない場合, intelligentsに学習理解度情報を格納
				dailyReportDto.setIntelligentFirstId(tDailyReportSubmit.getTIintelligibilityList().get(0).getIntelligibilityId());
				for (TIintelligibility intel : tDailyReportSubmit.getTIintelligibilityList()) {
					IntelligibilityDto intelligentDto = new IntelligibilityDto();
					BeanUtils.copyProperties(intel, intelligentDto);
					intelligents.add(intelligentDto);
				}
			}
		}else {
			if (dailyReportDto.getIntelligibilityFieldNum() != null) {
				// 学習理解度項目数がnullでない場合, intelligentsに学習理解度項目数分リストを作成
				for (int i = 0; i < dailyReportDto.getIntelligibilityFieldNum(); i++) {
					IntelligibilityDto inteDto = new IntelligibilityDto();
					intelligents.add(inteDto);
				}
			}
			dailyReportDto.setDate(date);
		}
		dailyReportDto.setIntelligibilityDtoList(intelligents); 
		dailyReportDto.setContent(contents);
		return dailyReportDto;
	}
	
	/**
	 * MDailyReportを取得
	 * 
	 * @param courseId
	 * @param sectionId
	 * @param accountId 
	 * @param dailyReportId 
	 * @return DailyReportDto
	 */
	public DailyReportDto setMDailyReport(ReportForm reportForm, Integer accountId, DailyReportDto dailyReportDto) {
		
		Integer courseId = reportForm.getCourseId();
		Integer sectionId = reportForm.getSectionId();
		Integer dailyReportId = reportForm.getDailyReportId();
		Integer dailyReportSubmitId = reportForm.getDailyReportSubmitId();
		
		MDailyReport mDailyReport = new MDailyReport();
		
	    if (sectionId != null && sectionId != 0 ) { 
	    	// sectionIdがnullでない、且つsectionIdが0でない場合、TSectionDailyReportを取得する
	    	TSectionDailyReport tSectionDailyReport = tSectionDailyReportRepository.findBySectionId(sectionId,Constants.DB_FLG_FALSE);
	    	mDailyReport = tSectionDailyReport.getMDailyReport();
		
	    }else if (courseId != null && courseId != 0 ) {
	    	// courseIdがnullでない,且つcourseIdが0でない場合, TCourseDailyReportを取得する
			TCourseDailyReport tCourseDailyReport = tCourseDailyReportRepository.findByCourseIdANDDeleteFlg(courseId, Constants.DB_FLG_FALSE);
			mDailyReport = tCourseDailyReport.getMDailyReport();
			courseId = tCourseDailyReport.getMCourse().getCourseId();
			
		}else if (dailyReportId != null && dailyReportId != 0 ) {
			// dailyReportIdがnullでない, 且つdailyReportIdが0でない場合, MDailyReportを取得する
			mDailyReport = mDailyReportService.findByMDailyReport(dailyReportSubmitId);
		}
		
		if (mDailyReport.getMDailyReportDetailList() != null) { 
			// mDailyReportDetailListがnullでない場合, MDailyReportをDailyReportDtoに格納する
			dailyReportDto = MDailyReportInDailyReportDto(mDailyReport,courseId); 
	    }
		return dailyReportDto;
		
	}

	/**
	 * 日報提出情報の登録数を取得
	 * @param dailyReportId
	 * @param lmsUserId
	 * @param date
	 * @param dbFlgFalse
	 * @return count
	 * */
	public int getDailyReportSubmit(Integer dailyReportId, Integer lmsUserId, Timestamp date,short dbFlgFalse) {
		int count = sRepository.countByUserAndDate(dailyReportId, lmsUserId, date, Constants.DB_FLG_FALSE);
		return count;
	}
	
	/**
	 * レポートの登録・更新・削除
	 * @param reportForm, date
	 * 
	 */
	public void registDailyReport(ReportForm reportForm, Date date) {
		TDailyReportSubmit tDailyReportSubmit = new TDailyReportSubmit();
		Integer dailyReportSubmitId = reportForm.getDailyReportSubmitId();
		Integer dailyReportId = reportForm.getDailyReportId();
		Timestamp timestampDate = new Timestamp(date.getTime());
		tDailyReportSubmit.setDate(timestampDate);
		
		Integer lmsUserId = null;
		// TODO 未実装
		//受講生権限チェック
		lmsUserId = reportForm.getLmsUserId();
		Integer accountId = reportForm.getAccountId();
		
		tDailyReportSubmit.setDailyReportSubmitId(dailyReportSubmitId);
		MLmsUser mLmsUser = new MLmsUser();
		mLmsUser.setLmsUserId(lmsUserId);
		tDailyReportSubmit.setMLmsUser(mLmsUser);
		tDailyReportSubmit.setAccountId(accountId);
		
		Date registDate = new Date();
		Timestamp registTimeStamp = new Timestamp(registDate.getTime());
		short DeleteFlg = 0;
		if(dailyReportSubmitId == null) {
			//日報提出IDが空の場合,日報提出情報の新規登録
			tDailyReportSubmit.setDeleteFlg(DeleteFlg);
			tDailyReportSubmit.setFirstCreateUser(lmsUserId);
			tDailyReportSubmit.setFirstCreateDate(registTimeStamp);
			tDailyReportSubmit.setLastModifiedUser(lmsUserId);
			tDailyReportSubmit.setLastModifiedDate(timestampDate);
			
			MDailyReport mDailyReport = new MDailyReport();
			mDailyReport.setDailyReportId(dailyReportId);
			tDailyReportSubmit.setMDailyReport(mDailyReport);
			sRepository.save(tDailyReportSubmit);
						
		}else {
			// 日報提出詳細情報を取得
			List<TDailyReportSubmitDetail> tDailyReportSubmitDetailList = tDailyReportSubmitDetailRepository.findByDailyReportSubmitId(dailyReportSubmitId);
			if(tDailyReportSubmitDetailList != null) {
				//日報提出詳細情報リストがnullでない場合, 削除
				for(TDailyReportSubmitDetail tDailyReportSubmitDetail : tDailyReportSubmitDetailList) {
					tDailyReportSubmitDetailRepository.delete(tDailyReportSubmitDetail);
				}
			}
			// 学習理解度情報を取得
			List<TIintelligibility> tIntelligentList = tIntelligentRepository.findByDailyReportSubmitId(dailyReportSubmitId);
			if(tIntelligentList != null) {
				//学習理解度リストがnullでない場合, 削除
				for(TIintelligibility tIntelligent : tIntelligentList) {
					tIntelligentRepository.delete(tIntelligent);
				}
			}
		}
		for(int i = 0; i < reportForm.getContents().length; i++) {
			// 日報提出詳細情報の登録
			TDailyReportSubmit td = new TDailyReportSubmit();
			TDailyReportSubmitDetail tDailyReportSubmitDetail = new TDailyReportSubmitDetail();
			tDailyReportSubmitDetail.setContent(reportForm.getContents()[i]);
			td.setDailyReportSubmitId(tDailyReportSubmit.getDailyReportSubmitId());
			tDailyReportSubmitDetail.setTDailyReportSubmit(tDailyReportSubmit);
			tDailyReportSubmitDetail.setAccountId(tDailyReportSubmit.getAccountId());
			tDailyReportSubmitDetail.setFieldNum(i + 1);
			tDailyReportSubmitDetailRepository.save(tDailyReportSubmitDetail);
		}
		if(reportForm.getIntelligentFieldName()!=null && reportForm.getIntelligentFieldName().length > 0) {
			// 学習理解度項目名がnullでない, 且つサイズが0以上である場合, 学習理解度情報の登録
			for(int i = 0; i < reportForm.getIntelligentFieldName().length; i++) {
				TIintelligibility tIntelligent = new TIintelligibility();
				tIntelligent.setFieldName(reportForm.getIntelligentFieldName()[i]);
				if(reportForm.getIntelligentFieldValue()!=null) {
					// IntelligentFieldValueがnullでない場合, short型に変換する
					short fieldValue = reportForm.getIntelligentFieldValue()[i].shortValue();
					tIntelligent.setFieldValue(fieldValue);
				}
				TDailyReportSubmit td = new TDailyReportSubmit();
				td.setDailyReportSubmitId(tDailyReportSubmit.getDailyReportSubmitId());
				tIntelligent.setTDailyReportSubmit(td);
				tIntelligent.setAccountId(tDailyReportSubmit.getAccountId());
				tIntelligent.setFieldNum(i + 1);
				tIntelligentRepository.save(tIntelligent);
			}
		}
	}
	/**
	 *入力パラメータの判定関数を呼び出し,エラーメッセージの空判定を行う処理
	 *@param reportForm
	 *@param date
	 *@return true/false errorMessageが空の場合, true / 空でない場合, false
	 */
	public boolean Valid(ReportForm reportForm,Date date) {
		// TODO 未実装
		// エラーメッセージをログに出力
		
		List<String> errorMessage = Valid1(reportForm,date);
		if(!errorMessage.isEmpty()) {
			// errorMessageが空でない場合falseを返す
			return false;
		}
		
		errorMessage = Valid2(reportForm);
		if(!errorMessage.isEmpty()) {
			// errorMessageが空でない場合falseを返す
			return false;
		}
		
		errorMessage = Valid3(reportForm);
		if(!errorMessage.isEmpty()) {
			// errorMessageが空でない場合falseを返す
			return false;
		}
		return true;
	}
	/**
	 * 日時と日報提出Idの入力チェック
	 * @param reportForm
	 * @param date
	 * @return error
	 * */
	private List<String> Valid1(ReportForm reportForm, Date date){
		// ①
		List<String> errorMessage = new ArrayList<>();
		Integer dailyReportId = reportForm.getDailyReportId();
		Integer lmsUserId = reportForm.getLmsUserId();
		Integer dailyReportSubmitId = reportForm.getDailyReportSubmitId();
		
		if (reportForm.getDate() == null) {
			// reportFormの日付がnullである場合, エラーメッセージをオブジェクトに格納
			errorMessage.add("メッセージID：already.report");
		}else if (dailyReportSubmitId == null) {
			Integer count = sRepository.countByUserAndDate(dailyReportId, lmsUserId, new Timestamp(date.getTime()), Constants.DB_FLG_FALSE);
			if (count > 0) {
				errorMessage.add("メッセージID：already.report");
			}
		}
		return errorMessage;
		
	}
	/**
	 * 同時送信更新処理
	 * (日報提出詳細ファーストIdで日報詳細テーブルを、学習理解度ファーストIdで学習理解度テーブルを検索。
	 *  結果が0件であれば、先に他ユーザーに更新されているのでエラーで返す)
	 *  
	 *@param reportForm
	 *@return errorMessage
	 **/
	private List<String> Valid2(ReportForm reportForm){
		List<String> errorMessage = new ArrayList<>();
		Integer dailyReportSubmitDetailFirstId = reportForm.getDailyReportSubmitDetailFirstId();
		Integer intelligibilityFirstId = reportForm.getIntelligibilityFirstId();
		if (dailyReportSubmitDetailFirstId != null) {
			//日報提出詳細ファーストIdがnullでない場合, 日報提出詳細情報の登録件数を取得
			int count = tDailyReportSubmitDetailRepository.CountfindBydailyReportSubmitDetailFirstId(dailyReportSubmitDetailFirstId, Constants.DB_FLG_FALSE);
			if (count == 0) {
				// 日報提出詳細情報の登録件数が0である場合, エラーメッセージをオブジェクトに格納
				errorMessage.add("メッセージID：other.update");
				return errorMessage;
			}
		}
		if (intelligibilityFirstId != null) {
			// 学習理解度ファーストIdがnullでない場合, 学習理解度情報の登録件数を取得
			int count = tIntelligentRepository.findById(intelligibilityFirstId, Constants.DB_FLG_FALSE);
			if (count == 0) {
				// 学習理解度情報の登録件数が0である場合, エラーメッセージをオブジェクトに格納
				errorMessage.add("メッセージID：othre.update");
				return errorMessage;
				// getDailyReportDto(reportForm,loginUserDto);
			}
		}
		return errorMessage;
	}

	/**
	 * 日報詳細情報と入力パラメータの判定処理
	 * 
	 * @param reportForm
	 * @return errorMessage
	 * */
	private List<String> Valid3(ReportForm reportForm) {
		// ③
		List<String> errorMessage = new ArrayList<>();
		
		List<MDailyReportDetail> mDailyReportDetailList = mDailyReportDetailRepository
				.findByDailyReportId(reportForm.getDailyReportId());
		
		String[] contents = reportForm.getContents();
		
		for (int i = 0; i < contents.length; i++) {
			MDailyReportDetail mDailyReportDetail = mDailyReportDetailList.get(i);
			
			if (mDailyReportDetail.getRequiredFlg() == 1 && contents[i].isEmpty()) {
				// 日報詳細情報の必須入力フラグが1であり,且つコンテンツが空である場合
				errorMessage.add("メッセージID：required");
			}
			if (mDailyReportDetail.getInputType() == 0 && !contents[i].isEmpty()) {
				// レポート詳細情報の入力タイプが0であり、且つコンテンツが空でない場合, 下記入力チェックを行う
				
				// TODO 未実装 
				// コンテンツを数値に変換する必要はあるのか
//				 if(!isNumeric(contents[i])) {
				// コンテンツを数値に変換できない場合, エラーメッセージをオブジェクトに格納
//					 errorMessage.add("メッセージID：notHalfSizeNumber");
//				 }
				
				if (mDailyReportDetail.getRangeTo() <= contents[i].length()
						|| contents[i].length() <= mDailyReportDetail.getRangeFrom()) {
					// コンテンツが範囲Toを超えている, またはコンテンツが範囲fromに達していない場合, エラーメッセージをオブジェクトに格納
					errorMessage.add("メッセージID：outRangeNumber");
				}
			}
			if (contents[i] != null && !contents[i].isEmpty() && 2000 <= contents[i].length()) {
				// コンテンツがnullでない, 且つコンテンツが空でない, 且つコンテンツが2000文字以上である場合, エラーメッセージをオブジェクトに格納
				errorMessage.add("メッセージID：maxlength");
			}
		}

		String[] intelligentFeildName = reportForm.getIntelligentFieldName();
		Integer[] intelligentFeildValue = reportForm.getIntelligentFieldValue();
		if (intelligentFeildName == null && intelligentFeildName.length == 0) {
			// 学習理解度項目のコンテンツがnullであり, 且つ学習理解度項目のコンテンツが0文字である場合, エラーメッセージをオブジェクトに格納
			errorMessage.add("メッセージID：maxlength");
			return errorMessage;
		}
		for (int i = 0; i < intelligentFeildName.length; i++) {
			if (intelligentFeildName[i] != null && !intelligentFeildName[i].isEmpty()
					&& intelligentFeildName[i].length() > 100) {
				// 学習項目がnullでない, 且つ学習項目が空でない, 且つ学習項目が100文字超えている場合, エラーメッセージをオブジェクトに格納
				errorMessage.add("メッセージID：maxlength");
			}
			if (intelligentFeildValue[i] != null && intelligentFeildValue[i] != 0) {
				//学習理解度数がnullでない, 且つ学習理解度数が0でない場合, 学習理解度数が数値型であるか判定
				if (!(intelligentFeildValue[i] instanceof Integer)) {
					// 学習理解度数が数値型でない場合,エラーメッセージをオブジェクトに格納
						errorMessage.add("メッセージID：integer");
				}
			}
			if (intelligentFeildName[i] != null && !intelligentFeildName[i].isEmpty()
					&& intelligentFeildValue[i] == null || intelligentFeildValue[i] == 0) {
				// 学習項目がnullでない, 且つ学習項目が空でない, 且つ学習理解度数がnullであり, または学習理解度数が0である場合,エラーメッセージを格納
				errorMessage.add("メッセージID：required.when");
			}
			if (intelligentFeildValue[i] != null && intelligentFeildValue[i] != 0 && intelligentFeildName[i] == null
					|| intelligentFeildName[i].isEmpty()) {
				// 学習理解度数がnullでない, 且つ学習理解度数が0でない, 且つ学習項目がnullであり, 学習項目が空である場合,エラーメッセージを格納する
				errorMessage.add("メッセージID：required.when");
			}
		}
		return errorMessage;
		
	}


	
		
}

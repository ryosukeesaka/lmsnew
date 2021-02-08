package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.DailyReportDto;
import jp.co.sss.lms.dto.DailyReportFbDto;
import jp.co.sss.lms.dto.IntelligibilityDto;
import jp.co.sss.lms.entity.MDailyReportDetail;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.TDailyReportFb;
import jp.co.sss.lms.entity.TDailyReportSubmit;
import jp.co.sss.lms.entity.TDailyReportSubmitDetail;
import jp.co.sss.lms.entity.TIintelligibility;
import jp.co.sss.lms.form.DailyReportDetailForm;
import jp.co.sss.lms.repository.LoginRepository;
import jp.co.sss.lms.repository.TDailyReportFbRepository;
import jp.co.sss.lms.repository.TDailyReportSubmitRepository;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.DateUtil;
import jp.co.sss.lms.util.MessageUtil;

@Service
public class ReportService {

	@Autowired
	TDailyReportSubmitRepository sRepository;
	@Autowired
	LoginRepository loginRepository;
	@Autowired
	TDailyReportFbRepository tDailyReportFbRepository;
	@Autowired
	DateUtil dateUtil;
	@Autowired
	private MessageUtil messageUtil;

	/**
	 * 　日報情報取得に関するメソッドです。
	 * @param dailyReportSubmitId
	 * @param userId ユーザID
	 * @param accountId 企業アカウントID
	 * @return DailyReportDto　日報情報DTO
	 */
	public DailyReportDto getDailyReportSubmit(Integer dailyReportSubmitId, Integer userId, Integer accountId) {

		TDailyReportSubmit drData = sRepository.findByDailyReportSubmitIdANDLmsUserIdANDDate(dailyReportSubmitId, userId, accountId,Constants.DB_FLG_FALSE);
		DailyReportDto dto=  new DailyReportDto();
		if(drData != null) {
			dto=  createDTO(drData);
		}

		return dto;
	}

	//DTO作成
	public DailyReportDto createDTO(TDailyReportSubmit drData) {

		DailyReportDto drDto = new DailyReportDto();
		drDto.setDailyReportId(drData.getMDailyReport().getDailyReportId());
		drDto.setDailyReportSubmitId(drData.getDailyReportSubmitId());
		drDto.setReportName(drData.getMDailyReport().getReportName());
		drDto.setFileName(drData.getMDailyReport().getFileName());
		drDto.setSheetName(drData.getMDailyReport().getSheetName());
		drDto.setRowCompany(drData.getMDailyReport().getRowCompany());
		drDto.setClmCompany(drData.getMDailyReport().getClmCompany());
		drDto.setRowUser(drData.getMDailyReport().getRowUser());
		drDto.setClmUser(drData.getMDailyReport().getClmUser());
		drDto.setRowDate(drData.getMDailyReport().getRowDate());
		drDto.setClmDate(drData.getMDailyReport().getClmDate());
		drDto.setIntelligibilityFlg(drData.getMDailyReport().getIntelligibilityFlg());
		drDto.setIntelligibilityFieldNum(drData.getMDailyReport().getIntelligibilityFieldNum());
		drDto.setIntelligibilityNum(drData.getMDailyReport().getIntelligibilityNum());
		drDto.setRowIntelFld(drData.getMDailyReport().getRowIntelFld());
		drDto.setClmIntelFld(drData.getMDailyReport().getClmIntelFld());
		drDto.setRowIntelFld(drData.getMDailyReport().getRowIntel());
		drDto.setClmIntel(drData.getMDailyReport().getClmIntel());
		drDto.setUserName(drData.getMLmsUser().getMUser().getUserName());
		drDto.setDate(drData.getDate());

		//報告レポートに関する詰め替え処理
		List<String> fieldNameList = new ArrayList<String>();
		for (MDailyReportDetail mDailyReportDetail : drData.getMDailyReport().getMDailyReportDetailList()) {
			fieldNameList.add(mDailyReportDetail.getFieldName());
		}
		drDto.setFieldName(fieldNameList);

		List<Integer> fieldNumList = new ArrayList<Integer>();
		List<String> contentList = new ArrayList<String>();
		for (TDailyReportSubmitDetail tDailyReportSubmitDetail : drData.getTDailyReportSubmitDetailList()) {
			fieldNumList.add(tDailyReportSubmitDetail.getFieldNum());
			contentList.add(tDailyReportSubmitDetail.getContent());
		}
		drDto.setFieldNum(fieldNumList);
		drDto.setContent(contentList);

		//学習理解度に関する詰め替え処理
		IntelligibilityDto tIintelligibility = new IntelligibilityDto();
		List<IntelligibilityDto> tIintelligibilityList = new ArrayList<IntelligibilityDto>();
		for (TIintelligibility date : drData.getTIintelligibilityList()) {
			tIintelligibility.setIntelligibilityId(date.getIntelligibilityId());
			tIintelligibility.setDailyReportSubmitId(date.getTDailyReportSubmit().getDailyReportSubmitId());
			tIintelligibility.setFieldNum(date.getFieldNum());
			tIintelligibility.setFieldName(date.getFieldName());
			tIintelligibility.setFieldValue(date.getFieldValue());
			tIintelligibilityList.add(tIintelligibility);
		}
		drDto.setIntelligibilityDtoList(tIintelligibilityList);

		return drDto;
	}
	
	/**
	 * 日報フィードバックコメント削除処理
	 *
	 * @param dailyReportFbId 日報フィードバックコメントID
	 * @param lmsUserId LMSユーザID
	 * @return saveDailyReportFeedback 日報フィードバックコメント情報更新処理
	 */
	public Integer deleteDailyReportFeedback(Integer dailyReportFbId, Integer lmsUserId) {

		TDailyReportFb tDailyReportFb = tDailyReportFbRepository.findById(dailyReportFbId).orElse(null);
		tDailyReportFb.setDeleteFlg(Constants.DB_FEEDBACK_FLG_TRUE);
		tDailyReportFb.setLastModifiedUser(lmsUserId);
		tDailyReportFb.setLastModifiedDate(
				dateUtil.stringToTimestamp(dateUtil.getCurrentDateString(), "yyyy/MM/dd HH:mm:ss"));

		return saveDailyReportFeedback(tDailyReportFb);
	}

	/**
	 * 日報フィードバックコメント情報登録APIになります
	 * 
	 * @param dailyReportForm レポート詳細フォーム
	 * @param lmsUserId LMSユーザID
	 * @param accountId 企業アカウントID
	 * @return saveDailyReportFeedback 日報フィードバックコメント情報更新処理
	 */
	public Integer insertDailyReportFeedback(DailyReportDetailForm dailyReportDetailForm, Integer lmsUserId, Integer accountId) {

		// DailyReportFormのフィールドバックIDを取得
		TDailyReportFb tDailyReportFb = new TDailyReportFb();
		TDailyReportSubmit tDailyReportSubmit = sRepository
				.getOne(dailyReportDetailForm.getDailyReportSubmitId());

		// DailyReportIdがnullなら新規登録処理、ある場合には更新の処理を実行
		tDailyReportFb.setTDailyReportSubmit(tDailyReportSubmit);
		tDailyReportFb.setLmsUserId(lmsUserId);
		tDailyReportFb.setContent(dailyReportDetailForm.getContent());
		tDailyReportFb.setAccountId(accountId);
		tDailyReportFb.setDeleteFlg(Constants.DB_FEEDBACK_FLG_FALSE);
		tDailyReportFb.setFirstCreateUser(lmsUserId);
		tDailyReportFb
				.setFirstCreateDate(dateUtil.stringToTimestamp(dateUtil.getCurrentDateString(), "yyyy/MM/dd HH:mm:ss"));
		tDailyReportFb.setLastModifiedUser(lmsUserId);
		tDailyReportFb.setLastModifiedDate(
				dateUtil.stringToTimestamp(dateUtil.getCurrentDateString(), "yyyy/MM/dd HH:mm:ss"));

		return saveDailyReportFeedback(tDailyReportFb);
	}

	/**
	 * 日報フィードバックコメント情報更新処理
	 *
	 * @param tDailyReportFb 日報フィードバックコメントエンティティ
	 * @return 登録件数 0
	 * @return 登録件数 1
	 */
	public Integer saveDailyReportFeedback(TDailyReportFb tDailyReportFb) {
		// commit処理したものを参照渡し
		tDailyReportFb = tDailyReportFbRepository.save(tDailyReportFb);

		if (tDailyReportFb.getDailyReportFbId() == null || tDailyReportFb.getDailyReportFbId() == 0) {
			return 0;
		}
		return 1;
	}

	/**
	 * 日報フィードバック提出情報の取得APIになります
	 * 
	 * @param dailyReportSubmitId 日報提出ID
	 * @return dailyReportDto 日報情報取得DTO（フィードバック情報のリストのみのDTO）
	 */
	public DailyReportDto getDailyReportFeedbackSubmit(Integer dailyReportSubmitId) {
		// 日報IDを使いフィードバック情報を取得
		List<TDailyReportFb> tDailyReportFbList = tDailyReportFbRepository
				.findByDailyReportFbIdANDDeleteFlg(dailyReportSubmitId, Constants.DB_FLG_FALSE);

		List<DailyReportFbDto> dailyReportFbDtoList = new ArrayList<DailyReportFbDto>();
		// レポートフィードバックDTOへの詰め替え
		for (TDailyReportFb tDailyReportFb : tDailyReportFbList) {
			DailyReportFbDto dailyReportFbDto = new DailyReportFbDto();
			MLmsUser mLmsUser = loginRepository.getOne(tDailyReportFb.getLmsUserId());
			dailyReportFbDto.setDailyReportFbId(tDailyReportFb.getDailyReportFbId());
			dailyReportFbDto.setLmsUserId(mLmsUser.getUserId());
			dailyReportFbDto.setUserName(mLmsUser.getMUser().getUserName());
			dailyReportFbDto.setContent(tDailyReportFb.getContent());
			dailyReportFbDto.setDate(tDailyReportFb.getFirstCreateDate());
			// リストに追加
			dailyReportFbDtoList.add(dailyReportFbDto);
		}
		// リストをDTOにsetterメソッドを使ってセット
		DailyReportDto dailyReportDto = new DailyReportDto();
		dailyReportDto.setDailyReportFbDtoList(dailyReportFbDtoList);

		return dailyReportDto;
	}

	/**
	 * 日報提出IDの存在チェックをするメソッドです。
	 * 
	 * @param dailyReportSubmitId 日報提出ID
	 * @return errorMessage
	 */
	public String checkDailyReportSubmitId(Integer dailyReportSubmitId) {

		// 日報提出情報取得
		TDailyReportSubmit tDailyReportSubmit = sRepository.findById(dailyReportSubmitId).orElse(null);

		// 日報提出情報取得に失敗した場合
		if (tDailyReportSubmit == null) {
			String[] values = { "dailySubmitId" };
			return messageUtil.getMessage(Constants.VALID_KEY_TOKEN, values);
		} else {
			return "";
		}
	}

	/**
	 * ログインユーザとレポート提出情報のLMSユーザIDが一致か不一致かを判断するメソッドです。
	 * 
	 * @param dailyReportSubmitId 日報提出ID
	 * @param lmsUserId LMSユーザID
	 * @return errorMessage
	 */
	public String checkLmsUserId(Integer dailyReportSubmitId, Integer lmsUserId) {

		// 日報提出IDから日報提出情報を取得
		TDailyReportSubmit tDailyReportSubmit = sRepository.getOne(dailyReportSubmitId);

		// ログインユーザのLMSユーザIDと日報提出情報のLMSユーザIDを比較 ture:何も返さない, flese:エラーメッセージを返す
		if (lmsUserId == tDailyReportSubmit.getMLmsUser().getLmsUserId()) {
			// 一致した場合
			return "";
		} else {
			// 一致しない場合
			String[] values = { "dailySubmitId" };
			return messageUtil.getMessage(Constants.VALID_KEY_TOKEN, values);
		}
	}

}

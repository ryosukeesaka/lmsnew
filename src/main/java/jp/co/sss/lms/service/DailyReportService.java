package jp.co.sss.lms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.DailyReportDto;
import jp.co.sss.lms.dto.IntelligibilityDto;
import jp.co.sss.lms.entity.MDailyReport;
import jp.co.sss.lms.entity.MDailyReportDetail;
import jp.co.sss.lms.entity.TDailyReportSubmit;
import jp.co.sss.lms.entity.TDailyReportSubmitDetail;
import jp.co.sss.lms.entity.TIintelligibility;
import jp.co.sss.lms.repository.TDailyReportSubmitRepository;

/**
 * dailyReport用Service
 * 
 * @author 小松由佳
 *
 */
@Service
public class DailyReportService {

	@Autowired
	TDailyReportSubmitRepository sRepository;

	/**
	 * TDailyReportSubmit取得
	 * 
	 * @param dailyReportId, date, lmsUserId, accountId
	 * @return tDailyReportSubmit
	 *
	 */
	public TDailyReportSubmit getTDailyReportService(Integer dailyReportId, Timestamp date, Integer lmsUserId,
			Integer accountId) {

		TDailyReportSubmit tDailyReportSubmit = sRepository.findByLmsUserIdAndDate(lmsUserId, date, dailyReportId);

		return tDailyReportSubmit;
	}

	/**
	 * MDailyReportDetailListをDailyReportDtoに追加
	 * 
	 * @param mDailyReportList
	 * @param DailyReportDto
	 * @return DailyReportDto
	 * 
	 */
	public DailyReportDto MDailyReportInDailyReportDto(MDailyReport mDailyReport) {

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
				rangefrom.add(null);
			} else {
				rangefrom.add(mDailyReportDetail.getRangeFrom().toString());
			}
			if (mDailyReportDetail.getRangeTo() == null) {
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

		dailyReportDto
				.setSectionDailyReportId(mDailyReport.getTSectionDailyReportList().get(0).getSectionDailyReportId()); // セクション・日報紐づけID
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
	 * 日報情報取得時に使用するDTO
	 * 
	 * @param Tdr
	 * @return DailyReportDto
	 */
	public DailyReportDto createDailyReportDTO(TDailyReportSubmit tdr, DailyReportDto dailyReportDto) {

		dailyReportDto.setDailyReportSubmitId(tdr.getDailyReportSubmitId());
		dailyReportDto.setUserName(tdr.getMLmsUser().getMUser().getUserName());
		dailyReportDto.setDate(new Date(tdr.getDate().getTime()));

		List<String> contents = new ArrayList<>();

		for (TDailyReportSubmitDetail tDailyReportSubmitDetail : tdr.getTDailyReportSubmitDetailList()) {
			contents.add(tDailyReportSubmitDetail.getContent());
		}
		dailyReportDto.setContent(contents);

		List<IntelligibilityDto> intellies = new ArrayList<>();
		if (tdr.getTIintelligibilityList() != null && !tdr.getTIintelligibilityList().isEmpty()) {
			for (TIintelligibility inte : tdr.getTIintelligibilityList()) {
				IntelligibilityDto inteDto = new IntelligibilityDto();
				BeanUtils.copyProperties(inte, inteDto);
				intellies.add(inteDto);
			}
		}
		return dailyReportDto;
	}

	/**
	 * 学習理解度情報取得時に使用するDTO
	 * 
	 * @param tIList
	 * @return IntelligibilityDto
	 */
	public DailyReportDto createIntelligibilityDto(List<TIintelligibility> tIList, DailyReportDto drDto) {

		IntelligibilityDto iDto = new IntelligibilityDto();

		for (TIintelligibility item : tIList) {
			iDto.setIntelligibilityId(item.getIntelligibilityId());
			iDto.setDailyReportSubmitId(item.getTDailyReportSubmit().getDailyReportSubmitId());
			iDto.setFieldNum(item.getFieldNum());
			iDto.setFieldName(item.getFieldName());
			iDto.setFieldValue(item.getFieldValue());
		}

		drDto.getIntelligibilityDtoList().add(iDto);
		return drDto;
	}

}

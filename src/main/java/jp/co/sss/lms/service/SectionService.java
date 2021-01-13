package jp.co.sss.lms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.SectionServiceDailyReportDto;
import jp.co.sss.lms.dto.SectionServiceDeliverablesDto;
import jp.co.sss.lms.dto.SectionServiceDeliverablesSectionDto;
import jp.co.sss.lms.dto.SectionServiceExamDto;
import jp.co.sss.lms.dto.SectionServiceFileDownloadDto;
import jp.co.sss.lms.dto.SectionServiceFileDto;
import jp.co.sss.lms.dto.SectionServiceSectionDto;

import jp.co.sss.lms.entity.MSection;
import jp.co.sss.lms.entity.TCourseDailyReport;
import jp.co.sss.lms.entity.TDailyReportSubmit;
import jp.co.sss.lms.entity.TDeliverablesSection;
import jp.co.sss.lms.entity.TExamSection;
import jp.co.sss.lms.entity.TFileSection;
import jp.co.sss.lms.entity.TSectionDailyReport;
import jp.co.sss.lms.repository.MSectionRepository;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.MessageUtil;
import jp.co.sss.lms.util.PasswordUtil;

/**
 * セクション情報サービス
 * 
 * @author 橋爪 優哉
 */
@Service
public class SectionService {
	@Autowired
	MessageUtil messageUtil;
	@Autowired
	TDailyReportSubmitService tDailyReportSubmitService;
	@Autowired
	MSectionRepository mSectionRepository;
	@Autowired
	PasswordUtil passwordUtil;
	
	// TODO 全体的に見直しが必要

	/**
	 * セクション詳細画面の初期表示に行う入力パラメータチェック ログに出力するメッセージを呼び出しもとに返すメソッド
	 * 
	 * @param sectionId セクションID
	 * @return エラーメッセージ
	 */
	public String getSessionInfo(Integer sectionId) {

		// セクション情報サービス.セクション情報取得を利用する
		MSection mSection = mSectionRepository.findBySectionId(sectionId);

		if (mSection == null) {

			String[] values = { "sectionId" };
			return messageUtil.getMessage(Constants.VALID_KEY_ALREADYDELETE, values);

		}

		return "";
	}

	/**
	 * セクション情報取得メソッド
	 * 
	 * @param sectionId セクションID
	 * @return sectionServiceSectionDto セクションDto
	 */
	public SectionServiceSectionDto getSectionDto(Integer sectionId, Integer accountId, Integer lmsUserId, Integer userId) {

		// セクション関連情報を取得
		MSection mSection = mSectionRepository.getSectionDetail(sectionId, accountId, Constants.DB_FLG_FALSE);
		
		// SecitonServiceFileDtoListの作成
		List<TFileSection> tFileSectionList = mSection.getTFileSectionList();
		List<SectionServiceFileDto> sectionServiceFileDtoList = new ArrayList<SectionServiceFileDto>();

		for (TFileSection tf : tFileSectionList) {

			SectionServiceFileDto sectionServiceFileDto = new SectionServiceFileDto();

			BeanUtils.copyProperties(tf.getMFile(), sectionServiceFileDto);
			sectionServiceFileDto.setFileId(this.getHashedFiled(new SectionServiceFileDownloadDto(), tf.getFileId(), userId));

			sectionServiceFileDtoList.add(sectionServiceFileDto);
		}

		// SecitonServiceExamDtoListの作成
		List<SectionServiceExamDto> sectionServiceExamDtoList = new ArrayList<SectionServiceExamDto>();

		for (TExamSection tes : mSection.getTExamSectionList()) {

			if (tes.getDeleteFlg() == Constants.DB_FLG_TRUE || tes.getMExam().getDeleteFlg() == Constants.DB_FLG_TRUE) {
				continue;
			}
			SectionServiceExamDto sectionServiceExamDto = new SectionServiceExamDto();
			BeanUtils.copyProperties(tes, sectionServiceExamDto);
			//試験公開可否設定
			if (tes.getPublicDate().getTime() < new Date().getTime()) {
				sectionServiceExamDto.setPublicFlg(true);
			} else {
				sectionServiceExamDto.setPublicFlg(false);
			}

			BeanUtils.copyProperties(tes.getMExam(), sectionServiceExamDto);
			sectionServiceExamDto.setSectionId(tes.getMSection().getSectionId());
			sectionServiceExamDto.setCourseId(tes.getMSection().getMCourse().getAccountId());

			sectionServiceExamDtoList.add(sectionServiceExamDto);
		}
		
		// SecitonServiceDailyReportDtoListの作成
		List<TSectionDailyReport> tSectionDailyReportList = mSection.getTSectionDailyReportList();
		List<SectionServiceDailyReportDto> sectionServiceDailyReportDtoList = new ArrayList<SectionServiceDailyReportDto>();
		
		//セクションに紐づくレポートを取得する
		if (mSection.getTSectionDailyReportList() != null && !mSection.getTSectionDailyReportList().isEmpty()) {
			for (TSectionDailyReport ts : tSectionDailyReportList) {
				if (ts.getDeleteFlg() == Constants.DB_FLG_TRUE || ts.getMDailyReport().getDeleteFlg() == Constants.DB_FLG_TRUE) {
						continue;
				}
				SectionServiceDailyReportDto sectionServiceDailyReportDto = new SectionServiceDailyReportDto();
				BeanUtils.copyProperties(ts.getMDailyReport(), sectionServiceDailyReportDto);
				sectionServiceDailyReportDtoList.add(sectionServiceDailyReportDto);
			}
		//セクションに紐づくレポートを取得できない場合、コースに紐づくレポート情報を取得する
		}else {
			for(TCourseDailyReport tCourseDailyReport : mSection.getMCategory().getMCourse().getTCourseDailyReport()) {
				SectionServiceDailyReportDto sectionServiceDailyReportDto = new SectionServiceDailyReportDto();
				BeanUtils.copyProperties(tCourseDailyReport.getMDailyReport(), sectionServiceDailyReportDto);
				sectionServiceDailyReportDtoList.add(sectionServiceDailyReportDto);
			}
		}
	
		// SecitonServiceDeliverablesSectionDtoListの作成
		List<TDeliverablesSection> tDeliverablesSectionList = mSection.getTDeliverablesSectionList();
		List<SectionServiceDeliverablesSectionDto> sectionServiceDeliverablesSectionDtoList = new ArrayList<SectionServiceDeliverablesSectionDto>();

		for (TDeliverablesSection td : tDeliverablesSectionList) {

			SectionServiceDeliverablesSectionDto sectionServiceDeliverablesSectionDto = new SectionServiceDeliverablesSectionDto();

			BeanUtils.copyProperties(td, sectionServiceDeliverablesSectionDto);
			sectionServiceDeliverablesSectionDto.setDeliverablesDto(new SectionServiceDeliverablesDto());
			sectionServiceDeliverablesSectionDto.getDeliverablesDto()
					.setDeliverablesId(td.getMDeliverables().getDeliverablesId());
			sectionServiceDeliverablesSectionDto.getDeliverablesDto()
					.setDeliverablesName(td.getMDeliverables().getDeliverablesName());

			sectionServiceDeliverablesSectionDtoList.add(sectionServiceDeliverablesSectionDto);
		}

		// sectionServiceSectionDtoのフィールドに格納
		SectionServiceSectionDto sectionServiceSectionDto = new SectionServiceSectionDto();
		BeanUtils.copyProperties(mSection, sectionServiceSectionDto);
		sectionServiceSectionDto.setCourseId(mSection.getMCourse().getCourseId());
		//TODO 定数で設定済みではあるが、アップロード最大サイズが未定のため、決まり次第変更
		sectionServiceSectionDto.setMaxFileSize(Constants.DELIVERABLES_UPLOAD_MAX_SIZE);
		sectionServiceSectionDto.setFileDtoList(sectionServiceFileDtoList);
		sectionServiceSectionDto.setExamDtoList(sectionServiceExamDtoList);
		sectionServiceSectionDto.setReportDtoList(sectionServiceDailyReportDtoList);
		sectionServiceSectionDto.setDeliverablesDtoList(sectionServiceDeliverablesSectionDtoList);
		
		return this.setDailyReportSubmitId(mSection, sectionServiceSectionDto, lmsUserId) ;
	}
	
	
	/**
	 * レポート提出状態の確認し、レポート提出IDを付与するメソッド
	 * @param mSection セクションマスタ
	 * @param sectionServiceSectionDto セクションDto
	 * @return レポート提出IDを設定したセクションDto
	 */
	private SectionServiceSectionDto setDailyReportSubmitId(MSection mSection,SectionServiceSectionDto sectionServiceSectionDto, Integer lmsUserId) {
	    Timestamp date = new Timestamp(mSection.getDate().getTime());
        for (SectionServiceDailyReportDto dto : sectionServiceSectionDto.getReportDtoList()) {
        	//レポート提出情報を取得する
            TDailyReportSubmit tDailyReportSubmit = tDailyReportSubmitService.findByUserAndDate(
            		lmsUserId, date, dto.getDailyReportId());
            if (tDailyReportSubmit != null) {
                dto.setDailyReportSubmitId(tDailyReportSubmit.getDailyReportSubmitId());
            }
        }
        return sectionServiceSectionDto;
    }

	/**
	 * ファイルIDハッシュ化メソッド
	 * 
	 * @param sectionServiceFileDownloadDto
	 * @param fileId
	 * @return hashFileId
	 */
	private String getHashedFiled(SectionServiceFileDownloadDto sectionServiceFileDownloadDto, Integer fileId, Integer userId) {
		String hashFileId = passwordUtil.getSaltedAndStrechedPassword(fileId.toString(),
				userId.toString());
		Integer mappedFiled = sectionServiceFileDownloadDto.getFileIdMap().get(hashFileId);
		if (!fileId.equals(mappedFiled)) {
			sectionServiceFileDownloadDto.getFileIdMap().put(hashFileId, fileId);
		}
		return hashFileId;
	}

}

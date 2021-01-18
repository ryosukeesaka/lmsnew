package jp.co.sss.lms.dto;

import java.util.Date;

import java.util.List;

/**
 * セクションサービスDTO
 * 
 * @author 眞鍋 美佳
 */
public class SectionServiceSectionDto {
	
	/** セクションID */
	private Integer sectionId;
	/** セクション名 */
	private String sectionName;
	/** 概要 */
	private String sectionDescription;
	/** コースID */
	private Integer courseId;
	/** ファイルサイズ */
	private String maxFileSize;
	/** 日付 */
	private Date date;

	/** ファイルDtoリスト */
	private List<SectionServiceFileDto> fileDtoList;
	/** 試験Dtoリスト */
	private List<SectionServiceExamDto> examDtoList;
	/** レポートDtoリスト */
	private List<SectionServiceDailyReportDto> reportDtoList;
	/** 成果物Dtoリスト */
	private List<SectionServiceDeliverablesSectionDto> deliverablesDtoList;
	/** 成果物提出確認リスト */
	private List<DeliverableServiceDeliverablesWithSubmissionFlgDto> deliverablesWithSubmissionFlgDtoList;
	
	public SectionServiceSectionDto() {
		
	}
	
	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSectionDescription() {
		return sectionDescription;
	}

	public void setSectionDescription(String sectionDescription) {
		this.sectionDescription = sectionDescription;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<SectionServiceFileDto> getFileDtoList() {
		return fileDtoList;
	}

	public void setFileDtoList(List<SectionServiceFileDto> fileDtoList) {
		this.fileDtoList = fileDtoList;
	}

	public List<SectionServiceExamDto> getExamDtoList() {
		return examDtoList;
	}

	public void setExamDtoList(List<SectionServiceExamDto> examDtoList) {
		this.examDtoList = examDtoList;
	}

	public List<SectionServiceDailyReportDto> getReportDtoList() {
		return reportDtoList;
	}

	public void setReportDtoList(List<SectionServiceDailyReportDto> reportDtoList) {
		this.reportDtoList = reportDtoList;
	}

	public List<SectionServiceDeliverablesSectionDto> getDeliverablesDtoList() {
		return deliverablesDtoList;
	}

	public void setDeliverablesDtoList(List<SectionServiceDeliverablesSectionDto> deliverablesDtoList) {
		this.deliverablesDtoList = deliverablesDtoList;
	}

	public List<DeliverableServiceDeliverablesWithSubmissionFlgDto> getDeliverablesWithSubmissionFlgDtoList() {
		return deliverablesWithSubmissionFlgDtoList;
	}

	public void setDeliverablesWithSubmissionFlgDtoList(
			List<DeliverableServiceDeliverablesWithSubmissionFlgDto> deliverablesWithSubmissionFlgDtoList) {
		this.deliverablesWithSubmissionFlgDtoList = deliverablesWithSubmissionFlgDtoList;
	}
	
}

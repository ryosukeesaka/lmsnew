package jp.co.sss.lms.dto;

/**
 * セクションサービス成果物DTO
 * @author 眞鍋 美佳
 */
public class SectionServiceDeliverablesSectionDto {
	
	 /** 成果物・セクション紐付けID */
    public Integer deliverablesSectionId;

    /** 提出期限 */
    public String submissionDeadline;

    /** 成果物Dto */
    public SectionServiceDeliverablesDto deliverablesDto;

	public Integer getDeliverablesSectionId() {
		return deliverablesSectionId;
	}

	public void setDeliverablesSectionId(Integer deliverablesSectionId) {
		this.deliverablesSectionId = deliverablesSectionId;
	}

	public String getSubmissionDeadline() {
		return submissionDeadline;
	}

	public void setSubmissionDeadline(String submissionDeadline) {
		this.submissionDeadline = submissionDeadline;
	}

	public SectionServiceDeliverablesDto getDeliverablesDto() {
		return deliverablesDto;
	}

	public void setDeliverablesDto(SectionServiceDeliverablesDto deliverablesDto) {
		this.deliverablesDto = deliverablesDto;
	}

    
	
}

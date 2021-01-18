package jp.co.sss.lms.dto;

/**
 * 成果物サービスDTO
 * @author 橋爪　優哉
 */
public class DeliverableServiceDeliverablesWithSubmissionFlgDto {
	// 成果物ID
	private Integer deliverablesId;

	// 成果物・セクション紐づけID
	private Integer deliverablesSectionId;

	// 成果物名
	private String deliverablesName;

	// 提出期限
	private String submissionDeadLine;

	// 提出フラグ
	private Short submissionFlg;
	
	public DeliverableServiceDeliverablesWithSubmissionFlgDto() {
		
	}

	public Integer getDeliverablesId() {
		return deliverablesId;
	}

	public void setDeliverablesId(Integer deliverablesId) {
		this.deliverablesId = deliverablesId;
	}

	public String getDeliverablesName() {
		return deliverablesName;
	}

	public void setDeliverablesName(String deliverablesName) {
		this.deliverablesName = deliverablesName;
	}

	public Integer getDeliverablesSectionId() {
		return deliverablesSectionId;
	}

	public void setDeliverablesSectionId(Integer deliverablesSectionId) {
		this.deliverablesSectionId = deliverablesSectionId;
	}

	public String getSubmissionDeadLine() {
		return submissionDeadLine;
	}

	public void setSubmissionDeadLine(String submissionDeadLine) {
		this.submissionDeadLine = submissionDeadLine;
	}

	public Short getSubmissionFlg() {
		return submissionFlg;
	}

	public void setSubmissionFlg(Short submissionFlg) {
		this.submissionFlg = submissionFlg;
	}
	
}	
	
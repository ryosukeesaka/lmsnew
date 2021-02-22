package jp.co.sss.lms.dto;

import java.util.Date;

import org.springframework.web.context.annotation.RequestScope;

@RequestScope
public class DeliverablesResultDto {

	/** 成果物名 */
	private String deliverablesName;
	
	/** 日付 */
	private Date date;
	
	/** 採点 */
	private Integer score;
	
	/** スコアフラグ */
	private short scoreFlg;
	
	/** フィードバックフラグ */
	private short feedbackFlg;
	
	/** コンテンツ */
	private String content;
	
	/** 成果物・セクション紐付けID */
	private Integer deliverablesResultId;

    /** 成果物・セクション紐付けID */
	private Integer lmsUserId;

    /** フィードバック */
	private String feedback;

    /** ファイルパス */
	private String filePath;

    /** ファイルサイズ */
	private Long fileSize;

    /** 提出時間 */
	private Date submissionTime;

    /** セクションDTO */
	private SectionServiceSectionDto sectionDto;

    /** 成果物DTO */
	private SectionServiceDeliverablesDto deliverablesDto;

    /** 成果物・セクションDTO */
	private SectionServiceDeliverablesSectionDto deliverablesSectionDto;

	public String getDeliverablesName() {
		return deliverablesName;
	}

	public void setDeliverablesName(String deliverablesName) {
		this.deliverablesName = deliverablesName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public short getScoreFlg() {
		return scoreFlg;
	}

	public void setScoreFlg(short scoreFlg) {
		this.scoreFlg = scoreFlg;
	}

	public short getFeedbackFlg() {
		return feedbackFlg;
	}

	public void setFeedbackFlg(short feedbackFlg) {
		this.feedbackFlg = feedbackFlg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDeliverablesResultId() {
		return deliverablesResultId;
	}

	public void setDeliverablesResultId(Integer deliverablesResultId) {
		this.deliverablesResultId = deliverablesResultId;
	}

	public Integer getLmsUserId() {
		return lmsUserId;
	}

	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Date getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(Date submissionTime) {
		this.submissionTime = submissionTime;
	}

	public SectionServiceSectionDto getSectionDto() {
		return sectionDto;
	}

	public void setSectionDto(SectionServiceSectionDto sectionDto) {
		this.sectionDto = sectionDto;
	}

	public SectionServiceDeliverablesDto getDeliverablesDto() {
		return deliverablesDto;
	}

	public void setDeliverablesDto(SectionServiceDeliverablesDto deliverablesDto) {
		this.deliverablesDto = deliverablesDto;
	}

	public SectionServiceDeliverablesSectionDto getDeliverablesSectionDto() {
		return deliverablesSectionDto;
	}

	public void setDeliverablesSectionDto(SectionServiceDeliverablesSectionDto deliverablesSectionDto) {
		this.deliverablesSectionDto = deliverablesSectionDto;
	}
}

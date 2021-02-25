package jp.co.sss.lms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 成果報告会スケジュールDTO
 * 
 * @author Takayuki Nomoto
 */
public class PresentationScheduleDto implements Serializable {
	
	/** */
	private static final long serialVersionUID = 5870118178906934847L;

	/** 成果報告会スケジュールID */
	private Integer presentationScheduleId;

	/** 予約編集期限 */
	private Date editLimit;

	/** 成果報告会日時 */
	private Date presentationDate;

	/** 用途 */
	private String purpose;

	/** 予約編集期限切れフラグ */
	private boolean editLimitPast;

	public Integer getPresentationScheduleId() {
		return presentationScheduleId;
	}

	public void setPresentationScheduleId(Integer presentationScheduleId) {
		this.presentationScheduleId = presentationScheduleId;
	}

	public Date getEditLimit() {
		return editLimit;
	}

	public void setEditLimit(Date editLimit) {
		this.editLimit = editLimit;
	}

	public Date getPresentationDate() {
		return presentationDate;
	}

	public void setPresentationDate(Date presentationDate) {
		this.presentationDate = presentationDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public boolean isEditLimitPast() {
		return editLimitPast;
	}

	public void setEditLimitPast(boolean editLimitPast) {
		this.editLimitPast = editLimitPast;
	}

}

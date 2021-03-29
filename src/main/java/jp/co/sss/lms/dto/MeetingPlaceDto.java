package jp.co.sss.lms.dto;

import java.util.Date;

public class MeetingPlaceDto {
	
	 /** 対象会場ID */
    public Integer meetingPlaceId;

    /** 会場名 */
    public String placeName;

    /** 会場備考 */
    public String placeNote;

    /** 会場住所 */
    public String placeDescription;

    /**  面談開始日 */
    public Date meetingOpenDate;

    /**  面談終了日 */
    public Date meetingCloseDate;

    /**  編集期限 */
    public Date editLimit;

    /** 内部向けメモ */
    public String meetingPlaceNote;

    /** 公開フラグ */
    public String publishedFlg;

    /** 編集期限を過ぎている場合true */
    public boolean isAfterEditLimit;

    /** 用途 */
    public String purpose;

	public Integer getMeetingPlaceId() {
		return meetingPlaceId;
	}

	public void setMeetingPlaceId(Integer meetingPlaceId) {
		this.meetingPlaceId = meetingPlaceId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceNote() {
		return placeNote;
	}

	public void setPlaceNote(String placeNote) {
		this.placeNote = placeNote;
	}

	public String getPlaceDescription() {
		return placeDescription;
	}

	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}

	public Date getMeetingOpenDate() {
		return meetingOpenDate;
	}

	public void setMeetingOpenDate(Date meetingOpenDate) {
		this.meetingOpenDate = meetingOpenDate;
	}

	public Date getMeetingCloseDate() {
		return meetingCloseDate;
	}

	public void setMeetingCloseDate(Date meetingCloseDate) {
		this.meetingCloseDate = meetingCloseDate;
	}

	public Date getEditLimit() {
		return editLimit;
	}

	public void setEditLimit(Date editLimit) {
		this.editLimit = editLimit;
	}

	public String getMeetingPlaceNote() {
		return meetingPlaceNote;
	}

	public void setMeetingPlaceNote(String meetingPlaceNote) {
		this.meetingPlaceNote = meetingPlaceNote;
	}

	public String getPublishedFlg() {
		return publishedFlg;
	}

	public void setPublishedFlg(String publishedFlg) {
		this.publishedFlg = publishedFlg;
	}

	public boolean isAfterEditLimit() {
		return isAfterEditLimit;
	}

	public void setAfterEditLimit(boolean isAfterEditLimit) {
		this.isAfterEditLimit = isAfterEditLimit;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}


}

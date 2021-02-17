package jp.co.sss.lms.dto;

import java.util.Date;

/**
 * MeetingScheduleDTOクラス
 *@author 江坂亮典
 */

public class MeetingScheduleDto {
	
	/** ミーティングスケジュールID */
    private Integer meetingScheduleId;

    /**  予約編集期限 */
    private Date editLimit;

    /** 面談開始日 */
    private Date meetingOpenDate;

    /**  面談終了日 */
    private Date meetingCloseDate;

    /**  用途 */
    private String purpose;

    /** 面談会場ID */
    private Integer meetingPlaceId;

    /** 会場名 */
    private String placeName;

    /** 公開フラグ */
    private Short publishedFlg;

	public Integer getMeetingScheduleId() {
		return meetingScheduleId;
	}

	public void setMeetingScheduleId(Integer meetingScheduleId) {
		this.meetingScheduleId = meetingScheduleId;
	}

	public Date getEditLimit() {
		return editLimit;
	}

	public void setEditLimit(Date editLimit) {
		this.editLimit = editLimit;
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

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

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

	public Short getPublishedFlg() {
		return publishedFlg;
	}

	public void setPublishedFlg(Short publishedFlg) {
		this.publishedFlg = publishedFlg;
	}

}

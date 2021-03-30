package jp.co.sss.lms.form;

public class ScheduleMeetingReserveRegistForm {
	
	/** 面談対象会場ID */
    
    private String meetingPlaceId;

    /** 面談詳細ID */
   
    private String[] meetingScheduleDetailId;

    /** 面談対象企業ID */
    
    private String[] companyId;

    /** 面談日 */
    private String[] meetingDate;

    /** 面談時間 */
    private String[] meetingTime;

    /** 面談参加人数 */
    private String[] joinAmount;

    /** お客様ご要望欄 */
    private String[] companyRequest;

    /** 内部向けメモ */
    private String meetingPlaceNote;

    /** 企業担当者予約時用インデックス */
    private String reserveIndex;

	public String getMeetingPlaceId() {
		return meetingPlaceId;
	}

	public void setMeetingPlaceId(String meetingPlaceId) {
		this.meetingPlaceId = meetingPlaceId;
	}

	public String[] getMeetingScheduleDetailId() {
		return meetingScheduleDetailId;
	}

	public void setMeetingScheduleDetailId(String[] meetingScheduleDetailId) {
		this.meetingScheduleDetailId = meetingScheduleDetailId;
	}

	public String[] getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String[] companyId) {
		this.companyId = companyId;
	}

	public String[] getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String[] meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String[] getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String[] meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String[] getJoinAmount() {
		return joinAmount;
	}

	public void setJoinAmount(String[] joinAmount) {
		this.joinAmount = joinAmount;
	}

	public String[] getCompanyRequest() {
		return companyRequest;
	}

	public void setCompanyRequest(String[] companyRequest) {
		this.companyRequest = companyRequest;
	}

	public String getMeetingPlaceNote() {
		return meetingPlaceNote;
	}

	public void setMeetingPlaceNote(String meetingPlaceNote) {
		this.meetingPlaceNote = meetingPlaceNote;
	}

	public String getReserveIndex() {
		return reserveIndex;
	}

	public void setReserveIndex(String reserveIndex) {
		this.reserveIndex = reserveIndex;
	}
    
    

}
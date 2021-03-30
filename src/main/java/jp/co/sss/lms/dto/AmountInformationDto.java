package jp.co.sss.lms.dto;

import java.util.Date;

public class AmountInformationDto {
	
	/** 面談詳細ID **/
	public Integer meetingScheduleDetailId;

	/** 面談日 */
	public Date meetingDate;

	/** 面談時間 */
	public String meetingTime;

	/** 会場-企業紐付けID */
	public Integer meetingCompanyId;

	/** 企業ID */
	public Integer companyId;

	/** 予約企業名 */
	public String companyName;

	/** 予約人数 */
	public Integer joinAmount;

	public String trCls;

	public String textCls;

	/** お客様ご要望欄 */
	public String companyRequest;

	/** 予約企業住所 */
	public String companyAddress;
	
	

	public Integer getMeetingScheduleDetailId() {
		return meetingScheduleDetailId;
	}

	public void setMeetingScheduleDetailId(Integer meetingScheduleDetailId) {
		this.meetingScheduleDetailId = meetingScheduleDetailId;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}

	public Integer getMeetingCompanyId() {
		return meetingCompanyId;
	}

	public void setMeetingCompanyId(Integer meetingCompanyId) {
		this.meetingCompanyId = meetingCompanyId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getJoinAmount() {
		return joinAmount;
	}

	public void setJoinAmount(Integer joinAmount) {
		this.joinAmount = joinAmount;
	}

	public String getTrCls() {
		return trCls;
	}

	public void setTrCls(String trCls) {
		this.trCls = trCls;
	}

	public String getTextCls() {
		return textCls;
	}

	public void setTextCls(String textCls) {
		this.textCls = textCls;
	}

	public String getCompanyRequest() {
		return companyRequest;
	}

	public void setCompanyRequest(String companyRequest) {
		this.companyRequest = companyRequest;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

}

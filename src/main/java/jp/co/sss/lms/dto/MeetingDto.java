package jp.co.sss.lms.dto;

/**
 * MeetingDto
 * 
 * 面談DTOクラス
 * 
 * @author 横山
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingDto {

    private String meetingId;
    private String lmsUserId;
    private Date firstCreateDate;
    private List<MeetingDetailDto> meetingDetailDtoList = new ArrayList<MeetingDetailDto>();
    private Integer deleteFlg;
    
    
	public String getMeetingId() {
		return meetingId;
	}
	
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	
	public String getLmsUserId() {
		return lmsUserId;
	}
	
	public void setLmsUserId(String lmsUserId) {
		this.lmsUserId = lmsUserId;
	}
	
	public Date getFirstCreateDate() {
		return firstCreateDate;
	}
	
	public void setFirstCreateDate(Date firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}
	
	public List<MeetingDetailDto> getMeetingDetailDtoList() {
		return meetingDetailDtoList;
	}
	
	public void setMeetingDetailDtoList(List<MeetingDetailDto> meetingDetailDtoList) {
		this.meetingDetailDtoList = meetingDetailDtoList;
	}
	
	public Integer getDeleteFlg() {
		return deleteFlg;
	}
	
	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	
}

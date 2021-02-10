package jp.co.sss.lms.form;

import java.util.List;

/**
 * 勤怠情報入力フォーム(全件)
 * 
 * @author 菅原 俊大
 */
public class AttendanceForm {

	/** 勤怠情報入力フォーム(1件) */
	private List<DailyAttendanceForm> attendanceList;

	private Integer lmsUserId;
	
	private Integer courseId; 
	
	private Integer accountId;
	public Integer getLmsUserId() {
		return lmsUserId;
	}

	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public List<DailyAttendanceForm> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<DailyAttendanceForm> attendanceList) {
		this.attendanceList = attendanceList;
	}
	
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getAccountId() {
		// TODO 自動生成されたメソッド・スタブ
		return accountId;
	}

}
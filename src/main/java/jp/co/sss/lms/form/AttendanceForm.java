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

	public List<DailyAttendanceForm> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<DailyAttendanceForm> attendanceList) {
		this.attendanceList = attendanceList;
	}

}
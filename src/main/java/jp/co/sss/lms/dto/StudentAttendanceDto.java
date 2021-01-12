package jp.co.sss.lms.dto;

import java.util.Date;

/**
 * 勤怠情報DTOクラス
 * 
 * @author shin
 */
public class StudentAttendanceDto {

	/** 受講生入力勤怠情報ID */
	private Integer studentAttendanceId;

	/** LMSユーザID */
	private String lmsUserId;

	/** 日付 */
	private Date trainingDate;

	/** 出勤時間 */
	private String trainingStartTime;

	/** 退勤時間 */
	private String trainingEndTime;

	/** 勤怠状況 */
	private Short status;

	/** 備考 */
	private String note;

	/** 中抜け時間 */
	private Integer blankTime;

	/** 勤怠状態画面表示名 */
	private String statusDispName;

	public Integer getStudentAttendanceId() {
		return studentAttendanceId;
	}

	public void setStudentAttendanceId(Integer studentAttendanceId) {
		this.studentAttendanceId = studentAttendanceId;
	}

	public String getLmsUserId() {
		return lmsUserId;
	}

	public void setLmsUserId(String lmsUserId) {
		this.lmsUserId = lmsUserId;
	}

	public Date getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	public String getTrainingStartTime() {
		return trainingStartTime;
	}

	public void setTrainingStartTime(String trainingStartTime) {
		this.trainingStartTime = trainingStartTime;
	}

	public String getTrainingEndTime() {
		return trainingEndTime;
	}

	public void setTrainingEndTime(String trainingEndTime) {
		this.trainingEndTime = trainingEndTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getBlankTime() {
		return blankTime;
	}

	public void setBlankTime(Integer blankTime) {
		this.blankTime = blankTime;
	}

	public String getStatusDispName() {
		return statusDispName;
	}

	public void setStatusDispName(String statusDispName) {
		this.statusDispName = statusDispName;
	}

}

package jp.co.sss.lms.form;

/**
 * 勤怠情報入力フォーム(1件)
 * 
 * @author 菅原 俊大
 */
public class DailyAttendanceForm {

	/** 受講生入力勤怠情報ID */
	private Integer studentAttendanceId;

	/** 日付 */
	private String trainingDate;

	/** 出勤時間 */
	private String trainingStartTime;

	/** 退勤時間 */
	private String trainingEndTime;

	/** 中抜け時間 */
	private Integer blankTime;

	/** 中抜け時間 */
	public String blankTimeValue;

	/** 勤怠状況 */
	private String status;

	/** 備考 */
	private String note;

	public Integer getStudentAttendanceId() {
		return studentAttendanceId;
	}

	public void setStudentAttendanceId(Integer studentAttendanceId) {
		this.studentAttendanceId = studentAttendanceId;
	}

	public String getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(String trainingDate) {
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

	public Integer getBlankTime() {
		return blankTime;
	}

	public void setBlankTime(Integer blankTime) {
		this.blankTime = blankTime;
	}

	public String getBlankTimeValue() {
		return blankTimeValue;
	}

	public void setBlankTimeValue(String blankTimeValue) {
		this.blankTimeValue = blankTimeValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}

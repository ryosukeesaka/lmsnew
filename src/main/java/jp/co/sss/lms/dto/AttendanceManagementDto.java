package jp.co.sss.lms.dto;

/**
 * 勤怠管理画面用DTOクラス
 * 
 * @author 菅原
 */
public class AttendanceManagementDto extends StudentAttendanceDto {

	/** 当日フラグ */
	private boolean isToday;

	/** 中抜け時間（文字列） */
	private String blankTimeValue;

	/** セクション名 */
	private String sectionName;

	public boolean getIsToday() {
		return isToday;
	}

	public void setToday(boolean isToday) {
		this.isToday = isToday;
	}

	public String getBlankTimeValue() {
		return blankTimeValue;
	}

	public void setBlankTimeValue(String blankTimeValue) {
		this.blankTimeValue = blankTimeValue;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

}

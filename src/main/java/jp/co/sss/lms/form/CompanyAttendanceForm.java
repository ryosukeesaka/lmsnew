package jp.co.sss.lms.form;

import java.util.List;

/**
 * 勤怠情報入力フォーム(全件)
 * 
 * @author 山木 翔
 */
public class CompanyAttendanceForm {
	
	private String courseName;
	
	private String placeName;
	
	private Integer placeId;
	
	private String companyName;
	
	private String userName;
	
	private String userId;
	
	private String role;
	
	public String getCourseName() {
		return courseName;
	}
	
	public String getPlaceName() {
		return placeName;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public Integer getPlaceId() {
		return placeId;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
package jp.co.sss.lms.form;

/**
 * 勤怠情報入力フォーム(全件)
 * 
 * @author 山木 翔
 */
public class CompanyAttendanceForm {
	
	private String courseName;
	
	private String placeName;
	
	private String placeId;
	
	private String companyName;
	
	private String userName;
	
	private String userId;
	
	private String role;
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getPlaceName() {
		return placeName;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getPlaceId() {
		return placeId;
	}
	
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
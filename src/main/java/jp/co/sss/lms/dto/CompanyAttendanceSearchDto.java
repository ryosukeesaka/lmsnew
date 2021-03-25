package jp.co.sss.lms.dto;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

public class CompanyAttendanceSearchDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6833960393062689583L;
	private Integer userId;
	private String userName;
	private String courseName;
	private String companyName;
	private String placeName;
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getPlaceName() {
		return placeName;
	}
	
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
}
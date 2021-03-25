package jp.co.sss.lms.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
 * ユーザー、企業、コース、会場情報
 * @author 梶山
 * 
 * */

public class UserCourseCompanyPlaceBasicInfoDto {
	//lmsUserId
	private Integer lmsUserId;
	//ユーザー名
	private String userName;
	//role
	private String role; 
	//退校フラグ
	private Integer leaveFlg;
	//退校日
	private Date leaveDate;
	//企業id
	private Integer companyId;
	//企業name
	private String companyName;
	//コースId
	private Integer courseId;
	//コース名
	private String courseName;
	//会場Id
	private Integer placeId;
	//会場名
	private String placeName;
	//会場詳細
	private String placeDescription;
	public Integer getLmsUserId() {
		return lmsUserId;
	}
	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getLeaveFlg() {
		return leaveFlg;
	}
	public void setLeaveFlg(Integer leaveFlg) {
		this.leaveFlg = leaveFlg;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
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
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getPlaceId() {
		return placeId;
	}
	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceDescription() {
		return placeDescription;
	}
	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}
}
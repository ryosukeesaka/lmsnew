package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @author 梶山
 * */
@Entity

public class UserCourseCompanyPlaceInfo {
	@Id
	private Integer lmsUserId;
	
	//ユーザー名
	@Column
	private String userName;
	//role
	@Column
	private String role; 
	//退校フラグ
	//flgのため、shortにしたかったがnullになる可能性があるためIntegerにしてある。
	//
	@Column
	private Integer leaveFlg;
	//退校日
	@Column
	private Date leaveDate;
	//企業id
	@Column
	private Integer companyId;
	//企業name
	@Column
	private String companyName;
	//コースId
	@Column
	private Integer courseId;
	//コース名
	@Column
	private String courseName;
	//会場Id
	@Column
	private String placeId;
	//会場名
	@Column
	private String placeName;
	//会場詳細
	@Column
	private String placeDescription;
	
	//getter,setterメソッド
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
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
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
	//
}
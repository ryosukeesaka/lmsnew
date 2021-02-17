package jp.co.sss.lms.dto;

import java.util.Date;
import java.util.List;


/**
 * コース情報サービス コースリストDTO
 * 
 * @author 高谷
 *
 */
public class CourseListDto {

	/** 開講フラグ */
    public boolean isOpenCourse;

    /** コースID */
    public Integer courseId;

    /** コース名 */
    public String courseName;

    /** コース説明 */
    public String courseDescription;

    /** 開講日 */
    public Date openTime;

    /** 閉講日 */
    public Date closeTime;

    /** コース種別 */
    public Short courseType;

    /** カテゴリー */
    public List< CategoryListDto> CategoryDtoList;
	/** カテゴリーDtoリスト */
	public List<CourseServiceCategoryDto> CourseServiceCategoryDtoList;
    
    /** アカウントID */
    public String accountid;
    
    /** 削除フラグ */
    public int deleteflg;
    
    /** コース固定ユーザーパスワード */
    public String password;

    public boolean isOpenCourse() {
		return isOpenCourse;
	}

	public void setOpenCourse(boolean isOpenCourse) {
		this.isOpenCourse = isOpenCourse;
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

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public Short getCourseType() {
		return courseType;
	}

	public void setCourseType(Short courseType) {
		this.courseType = courseType;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public int getDeleteflg() {
		return deleteflg;
	}

	public void setDeleteflg(int deleteflg) {
		this.deleteflg = deleteflg;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CourseServiceCategoryDto> getCourseServiceCategoryDtoList() {
		return CourseServiceCategoryDtoList;
	}

	public void setCourseServiceCategoryDtoList(List<CourseServiceCategoryDto> courseServiceCategoryDtoList) {
		CourseServiceCategoryDtoList = courseServiceCategoryDtoList;
	}

	public List<CategoryListDto> getCategoryDtoList() {
		return CategoryDtoList;
	}

	public void setCategoryDtoList(List<CategoryListDto> categoryDtoList) {
		CategoryDtoList = categoryDtoList;
	}
    
}

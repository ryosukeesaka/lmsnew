package jp.co.sss.lms.dto;

import java.util.Date;
import java.util.List;

/**
 * コース情報サービス コース一覧画面表示DTO
 * 
 * @author 高谷
 * 
 */


public class CourseViewDto {


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

	/** カテゴリーDtoリスト */
	public List<CategoryListDto> CategoryDtoList;

	/** pass */
	public String password;
	
	/** コース教材数 */
	public long teachingMaterialCount;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getTeachingMaterialCount() {
		return teachingMaterialCount;
	}

	public void setTeachingMaterialCount(long teachingMaterialCount) {
		this.teachingMaterialCount = teachingMaterialCount;
	}

	public List<CategoryListDto> getCategoryDtoList() {
		return CategoryDtoList;
	}

	public void setCategoryDtoList(List<CategoryListDto> categoryDtoList) {
		CategoryDtoList = categoryDtoList;
	}
}

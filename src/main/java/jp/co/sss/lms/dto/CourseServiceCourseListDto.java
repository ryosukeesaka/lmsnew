package jp.co.sss.lms.dto;

import java.util.Date;
import java.util.List;

/**
 * コース情報サービス コース一覧画面表示DTO
 * 
 * @author 高谷
 * 
 */
public class CourseServiceCourseListDto {

	/** 開講フラグ */
	private boolean isOpenCourse;

	/** コースID */
	private Integer courseId;

	/** コース名 */
	private String courseName;

	/** コース説明 */
	private String courseDescription;

	/** 開講日 */
	private Date openTime;

	/** 閉講日 */
	private Date closeTime;

	/** コース種別 */
	private Short courseType;

	/** カテゴリーDtoリスト */
	private List<CourseServiceCategoryDto> courseServiceCategoryDto;

	/** pass */
	private String password;
	
	/** コース教材数 */
	private long teachingMaterialCount;

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

	public List<CourseServiceCategoryDto> getCourseServiceCategoryDto() {
		return courseServiceCategoryDto;
	}

	public void setCourseServiceCategoryDto(List<CourseServiceCategoryDto> courseServiceCategoryDto) {
		this.courseServiceCategoryDto = courseServiceCategoryDto;
	}

}

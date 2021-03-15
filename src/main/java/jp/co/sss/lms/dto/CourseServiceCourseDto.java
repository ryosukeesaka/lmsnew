package jp.co.sss.lms.dto;

import java.util.Date;
import java.util.List;

/**
 * コース情報サービス コースDTO
 * 
 * @author 橋爪優哉
 *
 */
public class CourseServiceCourseDto {

	/** コースマスタ */
	private Integer courseId;

	/** コース名 */
	private String courseName;

	/** 開校日 */
	private Date openTime;

	/** 閉校日 */
	private Date closeTime;
	
	/** 講師権限のコースパスワード取得 */
	private String password;

	/** カテゴリDtoリスト */
	private List<CourseServiceCategoryDto> CourseServiceCategoryDtoList;


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

	public List<CourseServiceCategoryDto> getCourseServiceCategoryDtoList() {
		return CourseServiceCategoryDtoList;
	}

	public void setCourseServiceCategoryDtoList(List<CourseServiceCategoryDto> courseServiceCategoryDtoList) {
		CourseServiceCategoryDtoList = courseServiceCategoryDtoList;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
    }
}

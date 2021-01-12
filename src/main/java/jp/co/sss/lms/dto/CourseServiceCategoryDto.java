package jp.co.sss.lms.dto;

import java.util.List;

/**
 * コース情報サービス カテゴリDTO
 * 
 * @author 橋爪優哉
 *
 */
public class CourseServiceCategoryDto {

	/** カテゴリ名 */
	public String categoryName;

	/** セクションDtoリスト */
	private List<CourseServiceSectionDto> CourseServiceSectionDtoList;

	/** カテゴリーDtoリスト */
	private List<CourseServiceCategoryDto> CourseServiceCategoryDtoList;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<CourseServiceSectionDto> getCourseServiceSectionDtoList() {
		return CourseServiceSectionDtoList;
	}

	public void setCourseServiceSectionDtoList(List<CourseServiceSectionDto> courseServiceSectionDtoList) {
		CourseServiceSectionDtoList = courseServiceSectionDtoList;
	}

	public List<CourseServiceCategoryDto> getCourseServiceCategoryDtoList() {
		return CourseServiceCategoryDtoList;
	}

	public void setCourseServiceCategoryDtoList(List<CourseServiceCategoryDto> courseServiceCategoryDtoList) {
		CourseServiceCategoryDtoList = courseServiceCategoryDtoList;
	}
}

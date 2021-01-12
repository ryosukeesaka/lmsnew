package jp.co.sss.lms.form;

/**
 * セクションフォーム
 * @author 橋爪 優哉
 *
 */
public class SectionForm {
	
	/** コースID */
	private Integer courseId;
	/** セクションID */
	private Integer sectionId;
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
}

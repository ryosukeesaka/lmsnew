package jp.co.sss.lms.dto;

import java.sql.Timestamp;

/**
 * セクションサービス試験DTO
 * @author 眞鍋 美佳
 */
public class SectionServiceExamDto {
	/** 試験・セクション紐づけID */
    private Integer examSectionId;

    /** 試験ID */
    private Integer examId;

    /** 試験名 */
    private String examName;

    /** 試験カテゴリID */
    private Integer genreId;

    /** 公開日時 */
    private Timestamp publicDate;

    /** 公開フラグ */
    private boolean publicFlg = false;

    /** セクションID(パンくず用に追加) */
    private Integer sectionId;
    
    /** コースID(パンくず用に追加) */
    private Integer courseId;
    
	public Integer getExamSectionId() {
		return examSectionId;
	}
	public void setExamSectionId(Integer examSectionId) {
		this.examSectionId = examSectionId;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	
	public Integer getGenreId() {
		return genreId;
	}
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	
	public Timestamp getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(Timestamp publicDate) {
		this.publicDate = publicDate;
	}
	
	public boolean isPublicFlg() {
		return publicFlg;
	}
	public void setPublicFlg(boolean publicFlg) {
		this.publicFlg = publicFlg;
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

}

package jp.co.sss.lms.dto;

import java.util.Date;

/**
 * コース情報サービス セクションDTO
 * 
 * @author 橋爪優哉
 *
 */
public class CourseServiceSectionDto {

	/** セクションID */
	private Integer sectionId;

	/** セクション名 */
	private String sectionName;

	/** 日付 */
	private Date date;

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

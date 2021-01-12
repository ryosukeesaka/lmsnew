package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * セクションマスタエンティティ
 * @author 眞鍋 美佳
 *
 */

@Entity
@Table(name = "m_section")
public class MSection {
	
	/** セクションID */
	 @Id
	 @Column(name = "section_id")
	 @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	 @TableGenerator(
			 name = "generator",
			 allocationSize = 1)	
	private Integer sectionId;
	 /** セクション名 */
	@Column
	private String sectionName;
	/** 概要 */
	@Column
	private String sectionDescription;
	/** 日付 */
	@Column
	private Timestamp date;
	/** 企業アカウントID */
	@Column
	private Integer accountId;
	/** 削除フラグ */
	@Column
	private Short deleteFlg;
	/** 初回作成者 */
	@Column
	private Integer firstCreateUser;
	/** 初回作成日時 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstCreateDate;
	/** 最終更新者 */
	@Column
	private Integer lastModifiedUser;
	/** 最終更新日時 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName = "category_id")
	private MCategory mCategory;

	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "course_id")
	private MCourse mCourse;

	@OneToMany(mappedBy = "mSection")
	@OrderBy("fileId ASC")
	private List<TFileSection> tFileSectionList;

	@OneToMany(mappedBy = "mSection")
	private List<TExamSection> tExamSectionList;

	@OneToMany(mappedBy = "mSection")
	private List<TSectionDailyReport> tSectionDailyReportList;

	@OneToMany(mappedBy = "mSection")
	private List<TDeliverablesSection> tDeliverablesSectionList;

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

	public String getSectionDescription() {
		return sectionDescription;
	}

	public void setSectionDescription(String sectionDescription) {
		this.sectionDescription = sectionDescription;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Short getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Short deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public Integer getFirstCreateUser() {
		return firstCreateUser;
	}

	public void setFirstCreateUser(Integer firstCreateUser) {
		this.firstCreateUser = firstCreateUser;
	}

	public Date getFirstCreateDate() {
		return firstCreateDate;
	}

	public void setFirstCreateDate(Date firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}

	public Integer getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(Integer lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public MCategory getMCategory() {
		return mCategory;
	}

	public void setMCategory(MCategory mCategory) {
		this.mCategory = mCategory;
	}

	public MCourse getMCourse() {
		return mCourse;
	}

	public void setMCourse(MCourse mCourse) {
		this.mCourse = mCourse;
	}

	public List<TFileSection> getTFileSectionList() {
		return tFileSectionList;
	}

	public void setTFileSectionList(List<TFileSection> tFileSectionList) {
		this.tFileSectionList = tFileSectionList;
	}

	public List<TExamSection> getTExamSectionList() {
		return tExamSectionList;
	}

	public void setTExamSectionList(List<TExamSection> tExamSectionList) {
		this.tExamSectionList = tExamSectionList;
	}

	public List<TSectionDailyReport> getTSectionDailyReportList() {
		return tSectionDailyReportList;
	}

	public void setTSectionDailyReportList(List<TSectionDailyReport> tSectionDailyReportList) {
		this.tSectionDailyReportList = tSectionDailyReportList;
	}

	public List<TDeliverablesSection> getTDeliverablesSectionList() {
		return tDeliverablesSectionList;
	}

	public void setTDeliverablesSectionList(List<TDeliverablesSection> tDeliverablesSectionList) {
		this.tDeliverablesSectionList = tDeliverablesSectionList;
	}

}
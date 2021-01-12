package jp.co.sss.lms.entity;

import java.sql.Timestamp;
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

import org.springframework.stereotype.Component;

/**
 * MCategoryエンティティクラス
 * @author 眞鍋 美佳
 */
@Entity
@Component
@Table(name = "m_category")
public class MCategory {

	/** カテゴリID */
	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(name = "generator", allocationSize = 1)
	private Integer categoryId;

	/** カテゴリ名 */
	@Column
	private String categoryName;

	/** 概要 */
	@Column
	private String categoryDescription;

	/** 企業アカウントID  */
	@Column
	private Integer accountId;

	/** 削除フラグ  */
	@Column
	private Short deleteFlg;

	/** 初回作成者  */
	@Column
	private Integer firstCreateUser;

	/** 初回作成日時  */
	@Column
	private Timestamp firstCreateDate;

	/** 最終更新者  */
	@Column
	private Integer lastModifiedUser;

	/** 最終更新日時 */
	@Column
	private Timestamp lastModifiedDate;

	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "course_id")
	public MCourse mCourse;

	@OneToMany(mappedBy = "mCategory")
	@OrderBy("sectionId ASC")
	private List<MSection> mSectionList;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
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

	public Timestamp getFirstCreateDate() {
		return firstCreateDate;
	}

	public void setFirstCreateDate(Timestamp firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}

	public Integer getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(Integer lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public MCourse getMCourse() {
		return mCourse;
	}

	public void setMCourse(MCourse mCourse) {
		this.mCourse = mCourse;
	}

	public List<MSection> getMSectionList() {
		return mSectionList;
	}

	public void setMSectionList(List<MSection> mSectionList) {
		this.mSectionList = mSectionList;
	}

}

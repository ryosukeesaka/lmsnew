package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.stereotype.Component;

/**
 * MCourseエンティティクラス
 *@author 眞鍋 美佳
 */
@Entity
@Component
@Table(name = "m_course")
public class MCourse {
 
	/** コースID */
	@Id
	@Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(name = "generator",allocationSize = 1)
    private Integer courseId;

	/** コース名前 */
    @Column
    private String courseName;

    /** 概要 */
    @Column
    private String courseDescription;

    /** 開校日 */
    @Column
    private Timestamp openTime;

    /** 閉校日 */
    @Column
    private Timestamp closeTime;

    /** コースタイプ */
    @Column
    private Short courseType;

    /** 企業アカウントID */
    @Column
    private Integer accountId;

    /** 削除フラグ */
    @Column
    private Short deleteFlg;

    /** 初回作成者 */
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

    /** パスワード */
    @Column
    private String password;
    
    /** 非表示フラグ */
    @Column
    private Short hiddenFlg;
    

  	@OneToMany(mappedBy = "mCourse")
      private List<TCourseUser> tCourseUserList;

    @OneToMany(mappedBy = "mCourse")
    public List<MCategory> mCategoryList;

    @OneToMany(mappedBy = "mCourse")
    @OrderBy("sectionId ASC")
    public List<MSection> mSectionList;
    
    @OneToMany(mappedBy = "mCourse")
    public List<TCourseDailyReport> tCourseDailyReport;

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

	public Timestamp getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Timestamp openTime) {
		this.openTime = openTime;
	}

	public Timestamp getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Timestamp closeTime) {
		this.closeTime = closeTime;
	}

	public Short getCourseType() {
		return courseType;
	}

	public void setCourseType(Short courseType) {
		this.courseType = courseType;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Short getHiddenFlg() {
		return hiddenFlg;
	}

	public void setHiddenFlg(Short hiddenFlg) {
		this.hiddenFlg = hiddenFlg;
	}

	public List<TCourseUser> getTCourseUserList() {
		return tCourseUserList;
	}

	public void setTCourseUserList(List<TCourseUser> tCourseUserList) {
		this.tCourseUserList = tCourseUserList;
	}

	public List<MCategory> getMCategoryList() {
		return mCategoryList;
	}

	public void setMCategoryList(List<MCategory> mCategoryList) {
		this.mCategoryList = mCategoryList;
	}

	public List<MSection> getMSectionList() {
		return mSectionList;
	}

	public void setMSectionList(List<MSection> mSectionList) {
		this.mSectionList = mSectionList;
	}

	public List<TCourseDailyReport> getTCourseDailyReport() {
		return tCourseDailyReport;
	}

	public void setTCourseDailyReport(List<TCourseDailyReport> tCourseDailyReport) {
		this.tCourseDailyReport = tCourseDailyReport;
	}

	

}


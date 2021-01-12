package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_course_user")
public class TCourseUser {
	/** コース・ユーザー紐付けID */
	@Id
    private Integer courseUserId;
    @Column(name = "course_id", insertable = false, updatable = false)
    private Integer courseId;
	/** LMSユーザID */
    @Column
    private Integer lmsUserId;
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
    @OneToOne(mappedBy = "tCourseUser")
    private MLmsUser mLmsUser;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private MCourse mCourse;
    
	public Integer getCourseUserId() {
		return courseUserId;
	}
	public void setCourseUserId(Integer courseUserId) {
		this.courseUserId = courseUserId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getLmsUserId() {
		return lmsUserId;
	}
	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
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
	public MLmsUser getMLmsUser() {
		return mLmsUser;
	}
	public void setMLmsUser(MLmsUser mLmsUser) {
		this.mLmsUser = mLmsUser;
	}
	public MCourse getMCourse() {
		return mCourse;
	}
	public void setMCourse(MCourse mCourse) {
		this.mCourse = mCourse;
	}
  
}

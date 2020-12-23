package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "m_lms_user")
public class MLmsUser {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
        name = "generator",
        allocationSize = 1)
	private Integer lmsUserId;
	@Column
	private Integer userId;
	@Column
	private String role;
    @Column
    private Short adminFlg;
    @Column
    private Integer accountId;
    @Column
    private Short deleteFlg;
    @Column
    private Integer firstCreateUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstCreateDate;
    @Column
    private Integer lastModifiedUser;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Column
    private String hopeViaTraning;
    @Column
    private Short programmingExperience;
    @OneToOne(mappedBy = "mLmsUser")
    private MUser mUser;
    @OneToOne
    @JoinColumn(name  = "lms_user_id", referencedColumnName = "lms_user_id")
    private TUserCompany tUserCompany;
    @OneToOne
    @JoinColumn(name = "lms_user_id", referencedColumnName = "lms_user_id")
    private TUserPlace tUserPlace;
    @OneToOne
    @JoinColumn(name = "lms_user_id", referencedColumnName = "lms_user_id")
    private TCourseUser tCourseUser;
    
	public Integer getLmsUserId() {
		return lmsUserId;
	}
	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Short getAdminFlg() {
		return adminFlg;
	}
	public void setAdminFlg(Short adminFlg) {
		this.adminFlg = adminFlg;
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
	public String getHopeViaTraning() {
		return hopeViaTraning;
	}
	public void setHopeViaTraning(String hopeViaTraning) {
		this.hopeViaTraning = hopeViaTraning;
	}
	public Short getProgrammingExperience() {
		return programmingExperience;
	}
	public void setProgrammingExperience(Short programmingExperience) {
		this.programmingExperience = programmingExperience;
	}
	public MUser getMUser() {
		return mUser;
	}
	public void setMUser(MUser mUser) {
		this.mUser = mUser;
	}
	public TUserCompany getTUserCompany() {
		return tUserCompany;
	}
	public void setTUserCompany(TUserCompany tUserCompany) {
		this.tUserCompany = tUserCompany;
	}
	public TUserPlace getTUserPlace() {
		return tUserPlace;
	}
	public void setTUserPlace(TUserPlace tUserPlace) {
		this.tUserPlace = tUserPlace;
	}
	public TCourseUser getTCourseUser() {
		return tCourseUser;
	}
	public void setTCourseUser(TCourseUser tCourseUser) {
		this.tCourseUser = tCourseUser;
	}
    
}

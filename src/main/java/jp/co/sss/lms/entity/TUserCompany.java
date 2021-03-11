package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "t_user_company")
public class TUserCompany {
	@Id
    private Integer userCompanyId;
    @Column
    private Integer lmsUserId;
    @Column
    private Integer companyId;
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
    @OneToOne(mappedBy = "tUserCompany", cascade=CascadeType.ALL)
    private MLmsUser mLmsUser;
    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId", insertable = false, updatable = false)
	public MCompany mCompany;
    
	public Integer getUserCompanyId() {
		return userCompanyId;
	}
	public void setUserCompanyId(Integer userCompanyId) {
		this.userCompanyId = userCompanyId;
	}
	public Integer getLmsUserId() {
		return lmsUserId;
	}
	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
	public MCompany getMCompany() {
		return mCompany;
	}
	public void setMCompany(MCompany mCompany) {
		this.mCompany = mCompany;
	}

}

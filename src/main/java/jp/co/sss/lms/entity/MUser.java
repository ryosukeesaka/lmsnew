package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "m_user")
public class MUser {
	@Id
	private Integer userId;
	@Column
	private String loginId;
    @Column
    private String password;
    @Column
    private String userName;
    @Column
    private String authority;
    @Column
    private Integer accountId;
    @Column
    private Short securityAgreeFlg;
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordChangeDate;
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
    private String kana;
    @Column
    private String mailAddress;
    @Column
    private Integer subsidyCategoryId;
    @Column
    private Short leaveFlg;
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveDate;
    @OneToOne
    @JoinColumn(name  = "user_id", referencedColumnName = "userId")
    private MLmsUser mLmsUser;
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Short getSecurityAgreeFlg() {
		return securityAgreeFlg;
	}
	public void setSecurityAgreeFlg(Short securityAgreeFlg) {
		this.securityAgreeFlg = securityAgreeFlg;
	}
	public Date getPasswordChangeDate() {
		return passwordChangeDate;
	}
	public void setPasswordChangeDate(Date passwordChangeDate) {
		this.passwordChangeDate = passwordChangeDate;
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
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public Integer getSubsidyCategoryId() {
		return subsidyCategoryId;
	}
	public void setSubsidyCategoryId(Integer subsidyCategoryId) {
		this.subsidyCategoryId = subsidyCategoryId;
	}
	public Short getLeaveFlg() {
		return leaveFlg;
	}
	public void setLeaveFlg(Short leaveFlg) {
		this.leaveFlg = leaveFlg;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public MLmsUser getMLmsUser() {
		return mLmsUser;
	}
	public void setMLmsUser(MLmsUser mLmsUser) {
		this.mLmsUser = mLmsUser;
	}
	

}

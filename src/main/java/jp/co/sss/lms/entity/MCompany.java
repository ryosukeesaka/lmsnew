package jp.co.sss.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "m_company")
public class MCompany {
    @Id
    private Integer companyId;
    @Column
    private String companyName;
    @Column
    private Integer accountId;
    @Column
    private String companyNameKana;
    @Column
    private String postNumber1;
    @Column
    private String postNumber2;
    @Column
    private String prefecture;
    @Column
    private String address;
    @Column
    private String phoneNumber1;
    @Column
    private String phoneNumber2;
    @Column
    private String phoneNumber3;
    @Column
    private String representativePost;
    @Column
    private String representativeName;
    @Column
    private Integer capital;
    @Column
    private Integer workerAmount;
    @Column
    private String subsidyPhoneNumber1;
    @Column
    private String subsidyPhoneNumber2;
    @Column
    private String subsidyPhoneNumber3;
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
    private String restStartTime;
    @Column
    private String restEndTime;
    @Column
    private String holiday;
    @Column
    private String workStartTime;
    @Column
    private String workEndTime;
    @Column
    private Short fileShareFlg;
    
    
    
    @OneToMany(mappedBy = "mCompany")
    private List<TUserCompany> tUserCompanyList;
    
    
    @OneToMany(mappedBy = "mCompany")
    private List<TCompanyCourse> tCompanyCourseList;
    
    
    
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getCompanyNameKana() {
		return companyNameKana;
	}
	public void setCompanyNameKana(String companyNameKana) {
		this.companyNameKana = companyNameKana;
	}
	public String getPostNumber1() {
		return postNumber1;
	}
	public void setPostNumber1(String postNumber1) {
		this.postNumber1 = postNumber1;
	}
	public String getPostNumber2() {
		return postNumber2;
	}
	public void setPostNumber2(String postNumber2) {
		this.postNumber2 = postNumber2;
	}
	public String getPrefecture() {
		return prefecture;
	}
	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber1() {
		return phoneNumber1;
	}
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}
	public String getPhoneNumber2() {
		return phoneNumber2;
	}
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}
	public String getPhoneNumber3() {
		return phoneNumber3;
	}
	public void setPhoneNumber3(String phoneNumber3) {
		this.phoneNumber3 = phoneNumber3;
	}
	public String getRepresentativePost() {
		return representativePost;
	}
	public void setRepresentativePost(String representativePost) {
		this.representativePost = representativePost;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	public Integer getCapital() {
		return capital;
	}
	public void setCapital(Integer capital) {
		this.capital = capital;
	}
	public Integer getWorkerAmount() {
		return workerAmount;
	}
	public void setWorkerAmount(Integer workerAmount) {
		this.workerAmount = workerAmount;
	}
	public String getSubsidyPhoneNumber1() {
		return subsidyPhoneNumber1;
	}
	public void setSubsidyPhoneNumber1(String subsidyPhoneNumber1) {
		this.subsidyPhoneNumber1 = subsidyPhoneNumber1;
	}
	public String getSubsidyPhoneNumber2() {
		return subsidyPhoneNumber2;
	}
	public void setSubsidyPhoneNumber2(String subsidyPhoneNumber2) {
		this.subsidyPhoneNumber2 = subsidyPhoneNumber2;
	}
	public String getSubsidyPhoneNumber3() {
		return subsidyPhoneNumber3;
	}
	public void setSubsidyPhoneNumber3(String subsidyPhoneNumber3) {
		this.subsidyPhoneNumber3 = subsidyPhoneNumber3;
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
	public String getRestStartTime() {
		return restStartTime;
	}
	public void setRestStartTime(String restStartTime) {
		this.restStartTime = restStartTime;
	}
	public String getRestEndTime() {
		return restEndTime;
	}
	public void setRestEndTime(String restEndTime) {
		this.restEndTime = restEndTime;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	public String getWorkStartTime() {
		return workStartTime;
	}
	public void setWorkStartTime(String workStartTime) {
		this.workStartTime = workStartTime;
	}
	public String getWorkEndTime() {
		return workEndTime;
	}
	public void setWorkEndTime(String workEndTime) {
		this.workEndTime = workEndTime;
	}
	public Short getFileShareFlg() {
		return fileShareFlg;
	}
	public void setFileShareFlg(Short fileShareFlg) {
		this.fileShareFlg = fileShareFlg;
	}
	public List<TUserCompany> getTUserCompanyList() {
		return tUserCompanyList;
	}
	public void setTUserCompanyList(List<TUserCompany> tUserCompanyList) {
		this.tUserCompanyList = tUserCompanyList;
	}
	
	public List<TCompanyCourse> gettCompanyCourseList() {
		return tCompanyCourseList;
	}
	public void settCompanyCourseList(List<TCompanyCourse> tCompanyCourseList) {
		this.tCompanyCourseList = tCompanyCourseList;
	}

	
	
}

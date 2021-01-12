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

import org.springframework.stereotype.Component;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * MDailyReportエンティティクラス
 *@author 眞鍋 美佳
 */
@Entity
@Component
@Table(name = "m_daily_report")
public class MDailyReport {

    //dailyReportId
    @Id
    @Column(name = "daily_report_id")
    private Integer dailyReportId;

    //reportName
    @Column
    private String reportName;

    //fileName
    @Column
    private String fileName;

    //sheetName
    @Column
    private String sheetName;

    //rowCompany
    @Column
    private Integer rowCompany;

    //clmCompany
    @Column
    private Integer clmCompany;

    //rowUser
    @Column
    private Integer rowUser;

    //clmUser
    @Column
    private Integer clmUser;

    //rowDate
    @Column
    private Integer rowDate;

    //clmDate
    @Column
    private Integer clmDate;

    //intelligibilityFlg
    @Column
    private Short intelligibilityFlg;

    //intelligibilityFieldNum
    @Column
    private Short intelligibilityFieldNum;

    //intelligibilityNum
    @Column
    private Short intelligibilityNum;

    //rowIntelFld
    @Column
    private Integer rowIntelFld;

    //clmIntelFld
    @Column
    private Integer clmIntelFld;

    //rowIntel
    @Column
    private Integer rowIntel;

    //clmIntel
    @Column
    private Integer clmIntel;

    //accountId
    @Column
    private Integer accountId;

    //deleteFlg
    @Column
    private Short deleteFlg;

    //firstCreateUser
    @Column
    private Integer firstCreateUser;

    //firstCreateDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstCreateDate;

    //lastModifiedUser
    @Column
    private Integer lastModifiedUser;

    //lastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //hiddenFlg
    @Column
    private Short hiddenFlg;
    
    @OneToMany(mappedBy = "mDailyReport")
    public List<MDailyReportDetail> mDailyReportDetailList;

    @OneToMany(mappedBy = "mDailyReport")
    private List<TSectionDailyReport> tSectionDailyReportList;
    
    @OneToMany(mappedBy = "mDailyReport")
    public List<TCourseDailyReport> tCourseDailyReportList;
    
    @OneToOne
    @JoinColumn(name="daily_report_id", referencedColumnName = "daily_report_id")
    public TDailyReportSubmit tDailyReportSubmit;

	public Integer getDailyReportId() {
		return dailyReportId;
	}

	public void setDailyReportId(Integer dailyReportId) {
		this.dailyReportId = dailyReportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Integer getRowCompany() {
		return rowCompany;
	}

	public void setRowCompany(Integer rowCompany) {
		this.rowCompany = rowCompany;
	}

	public Integer getClmCompany() {
		return clmCompany;
	}

	public void setClmCompany(Integer clmCompany) {
		this.clmCompany = clmCompany;
	}

	public Integer getRowUser() {
		return rowUser;
	}

	public void setRowUser(Integer rowUser) {
		this.rowUser = rowUser;
	}

	public Integer getClmUser() {
		return clmUser;
	}

	public void setClmUser(Integer clmUser) {
		this.clmUser = clmUser;
	}

	public Integer getRowDate() {
		return rowDate;
	}

	public void setRowDate(Integer rowDate) {
		this.rowDate = rowDate;
	}

	public Integer getClmDate() {
		return clmDate;
	}

	public void setClmDate(Integer clmDate) {
		this.clmDate = clmDate;
	}

	public Short getIntelligibilityFlg() {
		return intelligibilityFlg;
	}

	public void setIntelligibilityFlg(Short intelligibilityFlg) {
		this.intelligibilityFlg = intelligibilityFlg;
	}

	public Short getIntelligibilityFieldNum() {
		return intelligibilityFieldNum;
	}

	public void setIntelligibilityFieldNum(Short intelligibilityFieldNum) {
		this.intelligibilityFieldNum = intelligibilityFieldNum;
	}

	public Short getIntelligibilityNum() {
		return intelligibilityNum;
	}

	public void setIntelligibilityNum(Short intelligibilityNum) {
		this.intelligibilityNum = intelligibilityNum;
	}

	public Integer getRowIntelFld() {
		return rowIntelFld;
	}

	public void setRowIntelFld(Integer rowIntelFld) {
		this.rowIntelFld = rowIntelFld;
	}

	public Integer getClmIntelFld() {
		return clmIntelFld;
	}

	public void setClmIntelFld(Integer clmIntelFld) {
		this.clmIntelFld = clmIntelFld;
	}

	public Integer getRowIntel() {
		return rowIntel;
	}

	public void setRowIntel(Integer rowIntel) {
		this.rowIntel = rowIntel;
	}

	public Integer getClmIntel() {
		return clmIntel;
	}

	public void setClmIntel(Integer clmIntel) {
		this.clmIntel = clmIntel;
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

	public Short getHiddenFlg() {
		return hiddenFlg;
	}

	public void setHiddenFlg(Short hiddenFlg) {
		this.hiddenFlg = hiddenFlg;
	}

	public List<TSectionDailyReport> getTSectionDailyReportList() {
		return tSectionDailyReportList;
	}

	public void setTSectionDailyReportList(List<TSectionDailyReport> tSectionDailyReportList) {
		this.tSectionDailyReportList = tSectionDailyReportList;
	}

	public List<TCourseDailyReport> getTCourseDailyReportList() {
		return tCourseDailyReportList;
	}

	public void setTCourseDailyReportList(List<TCourseDailyReport> tCourseDailyReportList) {
		this.tCourseDailyReportList = tCourseDailyReportList;
	}

	public List<MDailyReportDetail> getMDailyReportDetailList() {
		return mDailyReportDetailList;
	}

	public void setMDailyReportDetailList(List<MDailyReportDetail> mDailyReportDetailList) {
		this.mDailyReportDetailList = mDailyReportDetailList;
	}

	public TDailyReportSubmit getTDailyReportSubmit() {
		return tDailyReportSubmit;
	}

	public void setTDailyReportSubmit(TDailyReportSubmit tDailyReportSubmit) {
		this.tDailyReportSubmit = tDailyReportSubmit;
	}
	
}

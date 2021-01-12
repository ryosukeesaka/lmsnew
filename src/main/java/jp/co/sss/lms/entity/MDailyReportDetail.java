package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "m_daily_report_detail")
public class MDailyReportDetail {
	
	 /** dailyReportDetailIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator",
	    allocationSize = 1)
    @Column
    private Integer dailyReportDetailId;
    
    @Column(name="daily_report_id",insertable=false,updatable=false)
    private Integer dailyReportId;
    
    /** fieldNumプロパティ */
    @Column
    private Integer fieldNum;

    /** fieldNameプロパティ */
    @Column
    private String fieldName;

    /** rowプロパティ */
    @Column
    private Integer row;

    /** clmプロパティ */
    @Column
    private Integer clm;

    /** accountIdプロパティ */
    @Column
    private Integer accountId;

    /** deleteFlgプロパティ */
    @Column
    private Short deleteFlg;

    /** firstCreateUserプロパティ */
    @Column
    private Integer firstCreateUser;

    /** firstCreateDateプロパティ */
    @Column
    private Timestamp firstCreateDate;

    /** lastModifiedUserプロパティ */
    @Column
    private Integer lastModifiedUser;

    /** lastModifiedDateプロパティ */
    @Column
    private Timestamp lastModifiedDate;

    /** requiredFlgプロパティ */
    @Column
    private Short requiredFlg;

    /** inputTypeプロパティ */
    @Column
    private Short inputType;

    /** rangeFromプロパティ */
    @Column
    private Integer rangeFrom;

    /** rangeToプロパティ */
    @Column
    private Integer rangeTo;

    @ManyToOne
    @JoinColumn(name="daily_report_id", referencedColumnName = "daily_report_id")
    public MDailyReport mDailyReport;

	public Integer getDailyReportDetailId() {
		return dailyReportDetailId;
	}

	public void setDailyReportDetailId(Integer dailyReportDetailId) {
		this.dailyReportDetailId = dailyReportDetailId;
	}
	
	public Integer getDailyReportId() {
		return dailyReportId;
	}

	public void setDailyReportId(Integer dailyReportId) {
		this.dailyReportId = dailyReportId;
	}

	public Integer getFieldNum() {
		return fieldNum;
	}

	public void setFieldNum(Integer fieldNum) {
		this.fieldNum = fieldNum;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getClm() {
		return clm;
	}

	public void setClm(Integer clm) {
		this.clm = clm;
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

	public Short getRequiredFlg() {
		return requiredFlg;
	}

	public void setRequiredFlg(Short requiredFlg) {
		this.requiredFlg = requiredFlg;
	}

	public Short getInputType() {
		return inputType;
	}

	public void setInputType(Short inputType) {
		this.inputType = inputType;
	}

	public Integer getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(Integer rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public Integer getRangeTo() {
		return rangeTo;
	}

	public void setRangeTo(Integer rangeTo) {
		this.rangeTo = rangeTo;
	}

	public MDailyReport getMDailyReport() {
		return mDailyReport;
	}

	public void setMDailyReport(MDailyReport mDailyReport) {
		this.mDailyReport = mDailyReport;
	}
    
    

}

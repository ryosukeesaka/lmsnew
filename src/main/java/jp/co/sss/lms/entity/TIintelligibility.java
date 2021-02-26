package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "t_intelligibility")
public class TIintelligibility {
	@Id
    @Column(name="intelligibility_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
        name = "generator",
        table = "id_generator",
        pkColumnName = "pk",
        valueColumnName = "value",
        pkColumnValue = "T_INTELLIGIBILITY_INTELLIGIBILITY_ID",
        allocationSize = 1)
	private Integer intelligibilityId;
	@Column
	private Integer fieldNum;
	@Column
	private String fieldName;
	@Column
	private short fieldValue;
	@Column
	private Integer accountId;
	@Column
	private short deleteFlg;
	@Column
	private Integer firstCreateUser;
	@Temporal(TemporalType.TIMESTAMP)
    private Date firstCreateDate;
	@Column
	private Integer lastModifiedUser;
	@Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
	
	@ManyToOne
	@JoinColumn(name="daily_report_submit_id", referencedColumnName = "daily_report_submit_id")
    private TDailyReportSubmit tDailyReportSubmit;

	public Integer getIntelligibilityId() {
		return intelligibilityId;
	}

	public void setIntelligibilityId(Integer intelligibilityId) {
		this.intelligibilityId = intelligibilityId;
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

	public short getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(short fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public short getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(short deleteFlg) {
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

	public TDailyReportSubmit getTDailyReportSubmit() {
		return tDailyReportSubmit;
	}

	public void setTDailyReportSubmit(TDailyReportSubmit tDailyReportSubmit) {
		this.tDailyReportSubmit = tDailyReportSubmit;
	}
	
}

package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_daily_report_fb")
public class TDailyReportFb {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(name = "generator", table = "id_generator", pkColumnName = "pk", valueColumnName = "value", pkColumnValue = "T_DAILY_REPORT_FB_DAILY_REPORT_FB_ID", allocationSize = 1)
	private Integer dailyReportFbId;
//	@Column(name = "daily_report_submit_id", insertable = false, updatable = false)
//	private Integer dailyReportSubmitId;
	@Column
	private Integer lmsUserId;
	@Column
	private String content;
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
	@JoinColumn(name = "daily_report_submit_id", referencedColumnName = "daily_report_submit_id")
	private TDailyReportSubmit tDailyReportSubmit;

	public Integer getDailyReportFbId() {
		return dailyReportFbId;
	}

	public void setDailyReportFbId(Integer dailyReportFbId) {
		this.dailyReportFbId = dailyReportFbId;
	}

//	public Integer getDailyReportSubmitId() {
//		return dailyReportSubmitId;
//	}
//
//	public void setDailyReportSubmitId(Integer dailyReportSubmitId) {
//		this.dailyReportSubmitId = dailyReportSubmitId;
//	}

	public Integer getLmsUserId() {
		return lmsUserId;
	}

	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

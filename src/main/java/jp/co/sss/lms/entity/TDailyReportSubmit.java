package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "t_daily_report_submit")
public class TDailyReportSubmit {
	
	/** dailyReportSubmitIdプロパティ */
    @Id
    @Column(name="daily_report_submit_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
        name = "generator",
        allocationSize = 1)
    private Integer dailyReportSubmitId;
    
    /** dailyReportIdプロパティ */
    @Column
    private Integer dailyReportId;

    /** dateプロパティ */
    @Column
    private Timestamp date;

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

    @ManyToOne
    @JoinColumn(name = "lms_user_id", referencedColumnName = "lmsUserId")
    private MLmsUser mLmsUser; 

    @OneToOne(mappedBy = "tDailyReportSubmit")
    private MDailyReport mDailyReport;
    
    @OneToMany(mappedBy = "tDailyReportSubmit")
	private List<TDailyReportSubmitDetail> tDailyReportSubmitDetailList;

	@OneToMany(mappedBy = "tDailyReportSubmit")
	private List<TIintelligibility> tIintelligibilityList;

	@OneToMany(mappedBy = "tDailyReportSubmit")
	private List<TDailyReportFb> tDailyReportFbList;
	
	@OneToMany(mappedBy="tDailyReportSubmit")
	private List<TIintelligibility> tIntelligibility;
	
	@OneToMany(mappedBy = "tDailyReportSubmit")
	private List<TDailyReportSubmitDetail> SubmitDetail;
	
	
	public List<TDailyReportSubmitDetail> getSubmitDetail() {
		return SubmitDetail;
	}

	public void setSubmitDetail(List<TDailyReportSubmitDetail> submitDetail) {
		SubmitDetail = submitDetail;
	}

	public Integer getDailyReportSubmitId() {
		return dailyReportSubmitId;
	}

	public void setDailyReportSubmitId(Integer dailyReportSubmitId) {
		this.dailyReportSubmitId = dailyReportSubmitId;
	}

	public Integer getDailyReportId() {
		return dailyReportId;
	}

	public void setDailyReportId(Integer dailyReportId) {
		this.dailyReportId = dailyReportId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
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

	public MLmsUser getMLmsUser() {
		return mLmsUser;
	}

	public void setMLmsUser(MLmsUser mLmsUser) {
		this.mLmsUser = mLmsUser;
	}

	public MDailyReport getMDailyReport() {
		return mDailyReport;
	}

	public void setMDailyReport(MDailyReport mDailyReport) {
		this.mDailyReport = mDailyReport;
	}

	public List<TDailyReportSubmitDetail> getTDailyReportSubmitDetailList() {
		return tDailyReportSubmitDetailList;
	}

	public void setTDailyReportSubmitDetailList(List<TDailyReportSubmitDetail> tDailyReportSubmitDetailList) {
		this.tDailyReportSubmitDetailList = tDailyReportSubmitDetailList;
	}

	public List<TIintelligibility> getTIintelligibilityList() {
		return tIintelligibilityList;
	}

	public void setTIintelligibilityList(List<TIintelligibility> tIintelligibilityList) {
		this.tIintelligibilityList = tIintelligibilityList;
	}

	public List<TDailyReportFb> getTDailyReportFbList() {
		return tDailyReportFbList;
	}

	public void setTDailyReportFbList(List<TDailyReportFb> tDailyReportFbList) {
		this.tDailyReportFbList = tDailyReportFbList;
	}

	public List<TIintelligibility> getTIntelligibility() {
		return tIntelligibility;
	}

	public void setTIntelligibility(List<TIintelligibility> tIntelligibility) {
		this.tIntelligibility = tIntelligibility;
	}
	

}

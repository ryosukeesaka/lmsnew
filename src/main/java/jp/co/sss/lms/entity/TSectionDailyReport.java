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
@Table(name = "t_section_daily_report")
public class TSectionDailyReport {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator",
	    allocationSize = 1)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer sectionDailyReportId;

    /** sectionIdプロパティ */
    @Column(name="section_id", precision = 10, nullable = true, unique = false, insertable = false, updatable = false)
    public Integer sectionId;

    /** dailyReportIdプロパティ */
    @Column(name="daily_report_id",precision = 10, nullable = true, unique = false, insertable = false, updatable = false)
    public Integer dailyReportId;

    /** accountIdプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer accountId;

    /** deleteFlgプロパティ */
    @Column(precision = 5, nullable = true, unique = false)
    public Short deleteFlg;

    /** firstCreateUserプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer firstCreateUser;

    /** firstCreateDateプロパティ */
    @Column(nullable = true, unique = false)
    public Timestamp firstCreateDate;

    /** lastModifiedUserプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer lastModifiedUser;

    /** lastModifiedDateプロパティ */
    @Column(nullable = true, unique = false)
    public Timestamp lastModifiedDate;

    @ManyToOne
    @JoinColumn(name="section_id", referencedColumnName = "section_id")
    public MSection mSection;

    @ManyToOne
    @JoinColumn(name="daily_report_id", referencedColumnName = "daily_report_id")
    public MDailyReport mDailyReport;

	public Integer getSectionDailyReportId() {
		return sectionDailyReportId;
	}

	public void setSectionDailyReportId(Integer sectionDailyReportId) {
		this.sectionDailyReportId = sectionDailyReportId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getDailyReportId() {
		return dailyReportId;
	}

	public void setDailyReportId(Integer dailyReportId) {
		this.dailyReportId = dailyReportId;
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

	public MSection getMSection() {
		return mSection;
	}

	public void setMSection(MSection mSection) {
		this.mSection = mSection;
	}

	public MDailyReport getMDailyReport() {
		return mDailyReport;
	}

	public void setMDailyReport(MDailyReport mDailyReport) {
		this.mDailyReport = mDailyReport;
	}

}

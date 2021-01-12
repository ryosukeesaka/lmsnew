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

import org.springframework.stereotype.Component;

/**
 * TCourseDailyReportエンティティクラス
 * @author 眞鍋 美佳
 */
@Entity
@Component
@Table(name = "t_course_daily_report")
public class TCourseDailyReport {


    /** コース・日報ID */
    @Id
    @Column(name = "course_daily_report_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator",
	    allocationSize = 1)
    private Integer courseDailyReportId;

    /** 企業アカウントID */
    @Column
    private Integer accountId;

    /** 削除フラグ */
    @Column
    private Short deleteFlg;

    /** 初回作成者 */
    @Column
    private Integer firstCreateUser;

    /** 初回作成日時 */
    @Column
    private Timestamp firstCreateDate;

    /** 最終更新者 */
    @Column
    private Integer lastModifiedUser;

    /** 最終更新日時 */
    @Column
    private Timestamp lastModifiedDate;
    
    @ManyToOne
    @JoinColumn(name="course_id", referencedColumnName = "course_id")
    private MCourse mCourse;

    @ManyToOne
    @JoinColumn(name="daily_report_id", referencedColumnName = "daily_report_id")
    private MDailyReport mDailyReport;

	public Integer getCourseDailyReportId() {
		return courseDailyReportId;
	}

	public void setCourseDailyReportId(Integer courseDailyReportId) {
		this.courseDailyReportId = courseDailyReportId;
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

	public MCourse getMCourse() {
		return mCourse;
	}

	public void setMCourse(MCourse mCourse) {
		this.mCourse = mCourse;
	}

	public MDailyReport getMDailyReport() {
		return mDailyReport;
	}

	public void setMDailyReport(MDailyReport mDailyReport) {
		this.mDailyReport = mDailyReport;
	}
	
}
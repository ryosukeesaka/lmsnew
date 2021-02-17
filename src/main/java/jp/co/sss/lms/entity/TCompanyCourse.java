package jp.co.sss.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *企業・コース紐付けテーブル
 * @author 高谷
 *
 */
@Entity
@Table(name = "t_company_course")
public class TCompanyCourse {
	
	/**企業・共有グループID*/
	@Id
	@Column(name="company_course_id")
	private Integer companyCourseId;

	/**削除フラグ*/
	@Column
	private Integer entryId;
	/**削除フラグ*/
	@Column
	private Short deleteFlg;
	/**初回作成者*/
	@Column
	private Integer firstCreateUser;
	/**初回作成日時*/
	@Temporal(TemporalType.TIMESTAMP)
    private Date firstCreateDate;
	/**最終更新者*/
	@Column
    private Integer lastModifiedUser;
	/**最終更新日時*/
	@Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
	/***/
	@Temporal(TemporalType.TIMESTAMP)
    private Date invoiceOutputDate;
	
	
	@ManyToOne
	@JoinColumn(name ="company_id",referencedColumnName = "companyId")
	private MCompany mCompany;
	
	@ManyToOne
	@JoinColumn(name = "course_id",referencedColumnName = "course_id")
	private MCourse mCourse;

	public Integer getCompanycourseid() {
		return companyCourseId;
	}

	public void setCompanycourseid(Integer companycourseId) {
		this.companyCourseId = companycourseId;
	}

	public Integer getEntryId() {
		return entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
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

	public Date getInvoiceOutputDate() {
		return invoiceOutputDate;
	}

	public void setInvoiceOutputDate(Date invoiceOutputDate) {
		this.invoiceOutputDate = invoiceOutputDate;
	}

	public MCompany getmCompany() {
		return mCompany;
	}

	public void setmCompany(MCompany mCompany) {
		this.mCompany = mCompany;
	}

	public MCourse getmCourse() {
		return mCourse;
	}

	public void setmCourse(MCourse mCourse) {
		this.mCourse = mCourse;
	}
	
}

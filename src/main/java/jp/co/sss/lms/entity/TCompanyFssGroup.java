package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 企業・共有グループ紐付けテーブル
 * @author 廣江 凌也
 *
 */
@Entity
@Table(name = "t_company_fss_group")
public class TCompanyFssGroup {
	
	/**企業・共有グループID*/
	@Id
	private Integer companyFssGroupId;
	/**企業ID*/
	@Column(name = "company_id", insertable=false, updatable=false)
	private Integer companyId;
	/**共有グループID*/
	@Column(name = "fss_group_id", insertable=false, updatable=false)
	private Integer fssGroupId;
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
	
	/**共有グループマスタ*/
	@ManyToOne
	@JoinColumn(name ="fss_group_id",referencedColumnName = "fssGroupId")
	private MFssGroup mFssGroup;
	
	/**企業マスタ*/
	@ManyToOne
	@JoinColumn(name = "company_id",referencedColumnName = "companyId")
	private MCompany mCompany;
	
	/**以下、getter/setter*/
	public Integer getCompanyFssGroupId() {
		return companyFssGroupId;
	}

	public void setCompanyFssGroupId(Integer companyFssGroupId) {
		this.companyFssGroupId = companyFssGroupId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getFssGroupId() {
		return fssGroupId;
	}

	public void setFssGroupId(Integer fssGroupId) {
		this.fssGroupId = fssGroupId;
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

	public MFssGroup getmFssGroup() {
		return mFssGroup;
	}

	public void setMFssGroup(MFssGroup mFssGroup) {
		this.mFssGroup = mFssGroup;
	}

	public MCompany getMCompany() {
		return mCompany;
	}

	public void setMCompany(MCompany mCompany) {
		this.mCompany = mCompany;
	}
	

}

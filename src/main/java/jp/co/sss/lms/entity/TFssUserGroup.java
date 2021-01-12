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
 * 共有ユーザ・グループ紐付けテーブル
 * @author 廣江 凌也
 *
 */
@Entity
@Table(name = "t_fss_user_group")
public class TFssUserGroup {
	
	/**共有ユーザ・グループID*/
	@Id
	private Integer fssUserGroupId;
	
	/**共有ユーザID*/
	@Column(name = "fss_user_id", insertable=false, updatable=false)
	private Integer fssUserId;
	/**共有グループID*/
	@Column(name = "fss_group_id", insertable=false, updatable=false)
	private Integer fssGroupId;
	
	/**権限*/
	@Column
	private Short auth;
	
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
	
	/**共有ユーザマスタ*/
	@ManyToOne
	@JoinColumn(name = "fss_user_id", referencedColumnName = "fssUserId")
	private MFssUser mFssUser;
	
	/**共有グループマスタ*/
	@ManyToOne
	@JoinColumn(name = "fss_group_id",referencedColumnName = "fssGroupId")
	private MFssGroup mFssGroup;
	
	/**以下、getter/setter*/
	public Integer getFssUserGroupId() {
		return fssUserGroupId;
	}

	public void setFssUserGroupId(Integer fssUserGroupId) {
		this.fssUserGroupId = fssUserGroupId;
	}

	public Integer getFssUserId() {
		return fssUserId;
	}

	public void setFssUserId(Integer fssUserId) {
		this.fssUserId = fssUserId;
	}

	public Integer getFssGroupId() {
		return fssGroupId;
	}

	public void setFssGroupId(Integer fssGroupId) {
		this.fssGroupId = fssGroupId;
	}

	public Short getAuth() {
		return auth;
	}

	public void setAuth(Short auth) {
		this.auth = auth;
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

	public MFssUser getMFssUser() {
		return mFssUser;
	}

	public void setMFssUser(MFssUser mFssUser) {
		this.mFssUser = mFssUser;
	}

	public MFssGroup getMFssGroup() {
		return mFssGroup;
	}

	public void setMFssGroup(MFssGroup mFssGroup) {
		this.mFssGroup = mFssGroup;
	}
}

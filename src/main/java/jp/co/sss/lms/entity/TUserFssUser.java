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
 * ユーザ・共有ユーザ紐付けテーブル
 * @author 廣江 凌也
 *
 */
@Entity
@Table(name = "t_user_fss_user")
public class TUserFssUser {
	
	/**ユーザ・共有ユーザID*/
	@Id
	private Integer userFssUserId;
	
	/**ユーザID*/
	@Column(name = "user_id", insertable=false, updatable=false)
	private Integer userId;
	
	/**共有ユーザID*/
	@Column(name = "fss_user_id", insertable=false, updatable=false)
	private Integer fssUserId;
	
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
	
	/**ユーザマスタ*/
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "userId")
	private MUser mUser;
	
	/**共有ユーザマスタ*/
	@ManyToOne
	@JoinColumn(name = "fss_user_id", referencedColumnName = "fssUserId")
	private MFssUser mFssUser;
	
	/**以下、getter/setter*/
	public Integer getUserFssUserId() {
		return userFssUserId;
	}

	public void setUserFssUserId(Integer userFssUserId) {
		this.userFssUserId = userFssUserId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFssUserId() {
		return fssUserId;
	}

	public void setFssUserId(Integer fssUserId) {
		this.fssUserId = fssUserId;
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

	public MUser getMUser() {
		return mUser;
	}

	public void setMUser(MUser mUser) {
		this.mUser = mUser;
	}

	public MFssUser getMFssUser() {
		return mFssUser;
	}

	public void setMFssUser(MFssUser mFssUser) {
		this.mFssUser = mFssUser;
	}

}

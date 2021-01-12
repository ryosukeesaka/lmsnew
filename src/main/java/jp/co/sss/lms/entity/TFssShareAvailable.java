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
 * 共有可能ユーザテーブル
 * @author 廣江 凌也
 *
 */
@Entity
@Table(name = "t_fss_share_available")
public class TFssShareAvailable {
	
	/**共有可能ID*/
	@Id
	private Integer fssShareAvailableId;
	
	/**共有ユーザID*/
	@Column(name = "fss_user_id", insertable=false, updatable=false)
	private Integer fssUserId;
	
	/**共有可能共有ユーザID*/
	@Column(name = "shared_fss_user_id", insertable=false, updatable=false)
	private Integer shareFssUserId;
	
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
	@JoinColumn(name = "fss_user_id",referencedColumnName = "fssUserId")
	private MFssUser mFssUser;
	
	/**共有ユーザマスタ*/
	@ManyToOne
    @JoinColumn(name = "share_fss_user_id", referencedColumnName = "fssUserId")
    public MFssUser mFssUserShareFssUser;
	
	/**以下、getter/setter*/
	public Integer getFssShareAvailableId() {
		return fssShareAvailableId;
	}

	public void setFssShareAvailableId(Integer fssShareAvailableId) {
		this.fssShareAvailableId = fssShareAvailableId;
	}

	public Integer getFssUserId() {
		return fssUserId;
	}

	public void setFssUserId(Integer fssUserId) {
		this.fssUserId = fssUserId;
	}

	public Integer getShareFssUserId() {
		return shareFssUserId;
	}

	public void setShareFssUserId(Integer shareFssUserId) {
		this.shareFssUserId = shareFssUserId;
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

	public MFssUser getMFssUserShareFssUser() {
		return mFssUserShareFssUser;
	}

	public void setMFssUserShareFssUser(MFssUser mFssUserShareFssUser) {
		this.mFssUserShareFssUser = mFssUserShareFssUser;
	}

}

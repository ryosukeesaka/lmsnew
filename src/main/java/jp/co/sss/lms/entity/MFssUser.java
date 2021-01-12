package jp.co.sss.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 共有ユーザマスタテーブル
 * @author 廣江 凌也
 *
 */
@Entity
@Table(name = "m_fss_user" )
public class MFssUser {
	
	/**共有ユーザID*/
	@Id
	private Integer fssUserId;
	/**ユーザ名*/
	@Column
	private String nickname;
	/**最大合計ファイルサイズ*/
	@Column
	private Integer maxFileAmount;
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
	
	/**共有ファイル*/
	@OneToMany(mappedBy = "mFssUserOwnerFssUser")
	private List<TFssFile> tFssFileOwnerFileList;
	
	/**共有ファイル*/
	@OneToMany(mappedBy = "mFssUserSharedFssUser")
	private List<TFssFile> tFssFileSharedFileList;
	
	/**ユーザ・共有ユーザ*/
	@OneToMany(mappedBy = "mFssUser")
	private List<TUserFssUser> tUserFssUserList;
	
	/**共有ユーザ・グループ*/
	@OneToMany(mappedBy = "mFssUser")
	private List<TFssUserGroup> tFssUserGroupList;
	
	/**共有可能ユーザ*/
	@OneToMany(mappedBy = "mFssUser")
	private List<TFssShareAvailable> tFssShareAvailableList;
	
	/**共有可能ユーザ*/
	@OneToMany(mappedBy = "mFssUserShareFssUser")
    public List<TFssShareAvailable> tFssShareAvailableSharedList;
	
	/**以下、getter/setter*/
	public Integer getFssUserId() {
		return fssUserId;
	}

	public void setFssUserId(Integer fssUserId) {
		this.fssUserId = fssUserId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getMaxFileAmount() {
		return maxFileAmount;
	}

	public void setMaxFileAmount(Integer maxFileAmount) {
		this.maxFileAmount = maxFileAmount;
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

	public List<TFssFile> getTFssFileOwnerFileList() {
		return tFssFileOwnerFileList;
	}

	public void setTFssFileOwnerFileList(List<TFssFile> tFssFileOwnerFileList) {
		this.tFssFileOwnerFileList = tFssFileOwnerFileList;
	}

	public List<TFssFile> getTFssFileSharedFileList() {
		return tFssFileSharedFileList;
	}

	public void setTFssFileSharedFileList(List<TFssFile> tFssFileSharedFileList) {
		this.tFssFileSharedFileList = tFssFileSharedFileList;
	}

	public List<TUserFssUser> getTUserFssUserList() {
		return tUserFssUserList;
	}

	public void setTUserFssUserList(List<TUserFssUser> tUserFssUserList) {
		this.tUserFssUserList = tUserFssUserList;
	}

	public List<TFssUserGroup> getTFssUserGroupList() {
		return tFssUserGroupList;
	}

	public void setTFssUserGroupList(List<TFssUserGroup> tFssUserGroupList) {
		this.tFssUserGroupList = tFssUserGroupList;
	}

	public List<TFssShareAvailable> getTFssShareAvailableList() {
		return tFssShareAvailableList;
	}

	public void setTFssShareAvailableList(List<TFssShareAvailable> tFssShareAvailableList) {
		this.tFssShareAvailableList = tFssShareAvailableList;
	}

	public List<TFssShareAvailable> getTFssShareAvailableSharedList() {
		return tFssShareAvailableSharedList;
	}

	public void setTFssShareAvailableSharedList(List<TFssShareAvailable> tFssShareAvailableSharedList) {
		this.tFssShareAvailableSharedList = tFssShareAvailableSharedList;
	}
}

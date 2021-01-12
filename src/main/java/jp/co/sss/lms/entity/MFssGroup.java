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
 * 共有グループマスタテーブル
 * @author 廣江 凌也
 *
 */
@Entity
@Table(name = "m_fss_group")
public class MFssGroup {
	
	/**共有グループID*/
	@Id
	private Integer fssGroupId;
	/**グループ名*/
	@Column
	private String groupName;
	/**説明*/
	@Column
	private String description;
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
	
	/**共有ユーザ・グループ*/
	@OneToMany(mappedBy = "mFssGroup")
	private List<TFssUserGroup> tFssUserGroupList;
	
	/**企業・共有グループ*/
	@OneToMany(mappedBy = "mFssGroup")
	private List<TCompanyFssGroup> tCompanyFssGroupList;
	
	/**以下、getter/setter*/
	public Integer getFssGroupId() {
		return fssGroupId;
	}

	public void setFssGroupId(Integer fssGroupId) {
		this.fssGroupId = fssGroupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<TFssUserGroup> getTFssUserGroupList() {
		return tFssUserGroupList;
	}

	public void setTFssUserGroupList(List<TFssUserGroup> tFssUserGroupList) {
		this.tFssUserGroupList = tFssUserGroupList;
	}

	public List<TCompanyFssGroup> getTCompanyFssGroupList() {
		return tCompanyFssGroupList;
	}

	public void setTCompanyFssGroupList(List<TCompanyFssGroup> tCompanyFssGroupList) {
		this.tCompanyFssGroupList = tCompanyFssGroupList;
	}
}

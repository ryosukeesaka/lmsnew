package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * TTemporaryPassStorageエンティティ
 * 
 * @author endo
 *
 */
@Entity
@Table(name = "t_temporary_pass_storage")

public class TTemporaryPassStorage {

	/**
	 * 変更情報ID
	 *
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
		    name = "generator",
		    table = "id_generator",
		    pkColumnName = "pk",
		    valueColumnName = "value",
		    pkColumnValue = "T_TEMPORARY_PASS_STORAGE_ID",
		    allocationSize = 1)
	private Integer temporaryPassStorageId;
	/**
	 * ユーザーID
	 */
	@Column
	private Integer userId;
	/**
	 * 削除フラグ
	 */
	@Column
	private Integer deleteFlg;
	/**
	 * 変更キー
	 */
	@Column
	private String changeKey;
	/**
	 * 期限
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeLimit;
	/**
	 * 初回作成者
	 */
	@Column
	private Integer firstCreateUser;
	/**
	 * 初回作成日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstCreateDate;
	/**
	 * 最終更新者
	 */
	@Column
	private Integer lastModifiedUser;
	/**
	 * 最終更新日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	public Integer getTemporaryPassStorageId() {
		return temporaryPassStorageId;
	}
	public void setTemporaryPassStorageId(Integer temporaryPassStorageId) {
		this.temporaryPassStorageId = temporaryPassStorageId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public String getChangeKey() {
		return changeKey;
	}
	public void setChangeKey(String changeKey) {
		this.changeKey = changeKey;
	}
	public Date getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Date timeLimit) {
		this.timeLimit = timeLimit;
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

}

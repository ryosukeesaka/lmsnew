package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 面談記録テーブル
 * @author 横山
 *
 */

@Entity
@Table(name="t_meeting")

public class TMeeting {

	//面談ID
	@Id
	private Integer meetingId;
	//ユーザーID
	@Column
	private Integer lmsUserId;
	//アカウントID
	@Column
	private Integer accountId;
	//削除フラグ
	@Column
	private Integer deleteFlg;
	//作成者
	@Column
	private Integer firstCreateUser;
	//作成日
	@Temporal(TemporalType.TIMESTAMP)
    private Date firstCreateDate;
	//最終更新者
	@Column
    private Integer lastModifiedUser;
	//最終更新日時
	@Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
	public Integer getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}
	public Integer getLmsUserId() {
		return lmsUserId;
	}
	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(Integer deleteFlg) {
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
	
}

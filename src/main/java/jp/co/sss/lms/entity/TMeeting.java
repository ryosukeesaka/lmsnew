package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TMeetingエンティティクラス
 *@author キョーソーリン
 */

@Entity
@Table(name = "t_meeting")
public class TMeeting {
	//面談情報ID
	@Id
	@Column(name = "meeting_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
		    name = "generator", 
		    table = "id_generator",
		    pkColumnName = "pk",
		    valueColumnName = "value",
		    pkColumnValue = "T_MEETING_MEETING_ID",
		    allocationSize = 1
		    )
	private Integer meetingId;
	//ユーザーId
	@Column
	private Integer lmsUserId;
	//アカウントId
	@Column
	private Integer accountId;
	//削除フラグ
	@Column
	private Short deleteFlg;
	//作成者
	@Column
	private Integer firstCreateUser;
	//作成日
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstCreateDate;
	@PrePersist
	private void onCreate() {
		firstCreateDate = new Date();
	}
	//最終編集者
	@Column
	private Integer lastModifiedUser;
	//最終編集日
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
//	@ManyToOne
//  @JoinColumn(name = "lms_user_id", referencedColumnName = "lmsUserId")
//  public MLmsUser mLmsUser;

//  @OneToMany(mappedBy = "tMeeting")
//  public List<TMeetingDetail> tMeetingDetailList;	
	
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
	
}

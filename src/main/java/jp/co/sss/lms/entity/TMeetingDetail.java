package jp.co.sss.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * TMeetingDetailエンティティクラス
 *@author キョーソーリン
 */

@Entity
@Table(name = "t_meeting_detail")
public class TMeetingDetail {
	@Id
	@Column(name = "meeting_detail_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
		    name = "generator", 
		    table = "id_generator",
		    pkColumnName = "pk",
		    valueColumnName = "value",
		    pkColumnValue = "T_MEETING_DETAIL_MEETING_DETAIL_ID",
		    allocationSize = 1
		    )
	private Integer meetingDetailId;
	//面談情報ID
	@Column
	private Integer meetingId;
	//質問
	@Column
	private String question;
	//回答
	@Column
	private String answer;
	//フォロー
	@Column
	private String follow;
	//質問タイプ
	@Column
	private short questionType;
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
//	@JoinColumn(name = "meeting_id", referencedColumnName = "meeting_id")
//	public TMeeting tMeeting;
	
	public Integer getMeetingDetailId() {
		return meetingDetailId;
	}
	public void setMeetingDetailId(Integer meetingDetailId) {
		this.meetingDetailId = meetingDetailId;
	}
	public Integer getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getFollow() {
		return follow;
	}
	public void setFollow(String follow) {
		this.follow = follow;
	}
	public short getQuestionType() {
		return questionType;
	}
	public void setQuestionType(short s) {
		this.questionType = s;
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

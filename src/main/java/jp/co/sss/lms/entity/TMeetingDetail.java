package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 面談詳細テーブル
 * @author 横山
 *
 */
@Entity
@Table(name="t_meeting_detail")
public class TMeetingDetail {

	//面談詳細ID
	@Id
	private Integer meetingDetailId;
	//面談ID
	@Column
	private Integer meetingId;
	//質問
	@Column
	private String question;
	//回答
	@Column
	private String answer;
	//質問タイプ
	@Column
	private Integer questionType;
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
	public Integer getQuestionType() {
		return questionType;
	}
	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
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

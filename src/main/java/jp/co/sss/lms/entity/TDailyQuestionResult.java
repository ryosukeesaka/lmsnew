package jp.co.sss.lms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 日次テーブル用エンティティ
 * @author 垣花 武留
 *
 */
@Entity
@Table(name = "t_daily_question_result")
public class TDailyQuestionResult {
	
	private static final long serialVersionUID = 1L;

    /** 日次問題ID */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
        name = "generator",
        table = "id_generator",
        pkColumnName = "pk",
        valueColumnName = "value",
        pkColumnValue = "T_DAILY_QUESTION_RESULT_DAILY_QUESTION_RESULT_ID",
        allocationSize = 1)
    @Column(precision = 10, nullable = false, unique = true)
    private Integer dailyQuestionResultId;

    /** 回答 */
    @Column(precision = 5, nullable = true, unique = false)
    private Short reply;

    /** 回答日時 */
    @Column(nullable = true, unique = false)
    private Timestamp replyDate;

    /** 企業アカウントID */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer accountId;

    /** 削除フラグ */
    @Column(precision = 5, nullable = true, unique = false)
    private Short deleteFlg;

    /** 初回作成者 */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer firstCreateUser;

    /** 初回作成日時 */
    @Column(nullable = true, unique = false)
    private Timestamp firstCreateDate;

    /** 最終更新者 */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer lastModifiedUser;

    /** 最終更新日時 */
    @Column(nullable = true, unique = false)
    private Timestamp lastModifiedDate;
    
    /** 試験問題マスタ(外部参照) */
    @ManyToOne
    @JoinColumn(name="question_id", referencedColumnName = "questionId")
    private MQuestion mQuestion;

    /** LMSユーザマスタ(外部参照)*/
    @ManyToOne
    @JoinColumn(name="lms_user_id", referencedColumnName = "lmsUserId")
    private MLmsUser mLmsUser;

	public Integer getDailyQuestionResultId() {
		return dailyQuestionResultId;
	}

	public void setDailyQuestionResultId(Integer dailyQuestionResultId) {
		this.dailyQuestionResultId = dailyQuestionResultId;
	}

	
	public Short getReply() {
		return reply;
	}

	public void setReply(Short reply) {
		this.reply = reply;
	}

	public Timestamp getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
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

	public Timestamp getFirstCreateDate() {
		return firstCreateDate;
	}

	public void setFirstCreateDate(Timestamp firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}

	public Integer getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(Integer lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public MQuestion getMQuestion() {
		return mQuestion;
	}

	public void setMQuestion(MQuestion mQuestion) {
		this.mQuestion = mQuestion;
	}

	public MLmsUser getMLmsUser() {
		return mLmsUser;
	}

	public void setMLmsUser(MLmsUser mLmsUser) {
		this.mLmsUser = mLmsUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

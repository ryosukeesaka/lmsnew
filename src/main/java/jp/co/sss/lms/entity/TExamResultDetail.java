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
 * 試験結果詳細エンティティ
 * @author 垣花 武留
 */
@Entity
@Table(name="t_exam_result_detail")
public class TExamResultDetail {
	
	/** 試験結果詳細ID */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator",
	    table = "id_generator",
	    pkColumnName = "pk",
	    valueColumnName = "value",
	    pkColumnValue = "T_EXAM_RESULT_DETAIL_EXAM_RESULT_DETAIL_ID",
	    allocationSize = 1
	    )
    @Column(precision = 10, nullable = false, unique = true)
    private Integer examResultDetailId;

    /** LMSユーザID */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer lmsUserId;

    /** 問題ID */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer questionId;

    /** 回答 */
    @Column(precision = 5, nullable = true, unique = false)
    private Short reply;

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
    
    /** 試験結果(外部参照) */
    @ManyToOne
    @JoinColumn(name="exam_result_id", referencedColumnName = "examResultId")
    private TExamResult tExamResult;

	public Integer getExamResultDetailId() {
		return examResultDetailId;
	}

	public void setExamResultDetailId(Integer examResultDetailId) {
		this.examResultDetailId = examResultDetailId;
	}

	public Integer getLmsUserId() {
		return lmsUserId;
	}

	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Short getReply() {
		return reply;
	}

	public void setReply(Short reply) {
		this.reply = reply;
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

	public TExamResult getTExamResult() {
		return tExamResult;
	}

	public void setTExamResult(TExamResult tExamResult) {
		this.tExamResult = tExamResult;
	}
}

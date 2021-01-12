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
 * TDeliverablesResultエンティティクラス
 * @author 眞鍋 美佳
 */
@Entity
@Table (name = "t_deliverables_result")
public class TDeliverablesResult {



	/** 成果物結果Id */
    @Id
    @Column (name = "deliverables_result_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator", 
	    table = "id_generator",
	    pkColumnName = "pk",
	    valueColumnName = "value",
	    pkColumnValue = "T_DELIVERABLES_RESULT_DELIVERABLES_RESULT_ID",
	    allocationSize = 1
    )
    private Integer deliverablesResultId;


    /** 採点 */
    @Column
    private Integer score;

    /** フィードバック */
    @Column
    private String feedback;

    /** ファイルパス */
    @Column
    private String filePath;

    /** ファイルサイズ */
    @Column
    private Long fileSize;

    /** 提出時間 */
    @Column
    private Timestamp submissionTime;

    /** アカウントID */
    @Column
    private Integer accountId;

    /** 削除フラグ */
    @Column
    private Short deleteFlg;

    /** 初回作成者 */
    @Column
    private Integer firstCreateUser;

    /** 初回作成日 */
    @Column
    private Timestamp firstCreateDate;

    /** 最終更新者 */
    @Column
    private Integer lastModifiedUser;

    /** 最終更新日 */
    @Column
    private Timestamp lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "deliverables_section_id", referencedColumnName = "deliverables_section_id")
    private TDeliverablesSection tDeliverablesSection;

    @ManyToOne
    @JoinColumn(name = "lms_user_id", referencedColumnName = "lmsUserId")
    private MLmsUser mLmsUser;

	public Integer getDeliverablesResultId() {
		return deliverablesResultId;
	}

	public void setDeliverablesResultId(Integer deliverablesResultId) {
		this.deliverablesResultId = deliverablesResultId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Timestamp getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(Timestamp submissionTime) {
		this.submissionTime = submissionTime;
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

	public TDeliverablesSection gettDeliverablesSection() {
		return tDeliverablesSection;
	}

	public void settDeliverablesSection(TDeliverablesSection tDeliverablesSection) {
		this.tDeliverablesSection = tDeliverablesSection;
	}

	public MLmsUser getMLmsUser() {
		return mLmsUser;
	}

	public void setMLmsUser(MLmsUser mLmsUser) {
		this.mLmsUser = mLmsUser;
	}
}
package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 試験結果エンティティ
 * @author 垣花 武留
 */
@Entity
@Table(name = "t_exam_result")
public class TExamResult {
	
	 /** 試験結果ID */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator", 
	    table = "id_generator",
	    pkColumnName = "pk",
	    valueColumnName = "value",
	    pkColumnValue = "T_EXAM_RESULT_EXAM_RESULT_ID",
	    allocationSize = 1
	    )
    @Column(precision = 10, nullable = false, unique = true)
    private Integer examResultId;

    /** 試験・セクション紐付けID */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer examSectionId;
    
    /** 得点 */
    @Column(precision = 5, nullable = true, unique = false)
    private Short score;

    /** 所要時間 */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer time;

    /** 評点フラグ */
    @Column(precision = 5, nullable = true, unique = false)
    private Short markFlg;

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
    
    /** LMSユーザーマスタ(外部参照) */
    @ManyToOne
    @JoinColumn(name="lms_user_id", referencedColumnName = "lmsUserId")
    private MLmsUser mLmsUser;
    
    /** 試験・セクション紐付けエンティティ */
    @OneToOne(mappedBy = "tExamResult")
    public TExamSection tExamSection;
    
    /** 試験結果詳細リスト */
    @OneToMany(mappedBy = "tExamResult")
    public List<TExamResultDetail> tExamResultDetailList;

	public Integer getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(Integer examResultId) {
		this.examResultId = examResultId;
	}

	public Integer getExamSectionId() {
		return examSectionId;
	}

	public void setExamSectionId(Integer examSectionId) {
		this.examSectionId = examSectionId;
	}

	public Short getScore() {
		return score;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Short getMarkFlg() {
		return markFlg;
	}

	public void setMarkFlg(Short markFlg) {
		this.markFlg = markFlg;
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

	public MLmsUser getMLmsUser() {
		return mLmsUser;
	}

	public void setMLmsUser(MLmsUser mLmsUser) {
		this.mLmsUser = mLmsUser;
	}

	public TExamSection getTExamSection() {
    	return tExamSection;
	}

	public void setTExamSection(TExamSection tExamSection) {
		this.tExamSection = tExamSection;
	}

	public List<TExamResultDetail> getTExamResultDetailList() {
		return tExamResultDetailList;
	}

	public void setTExamResultDetailList(List<TExamResultDetail> tExamResultDetailList) {
		this.tExamResultDetailList = tExamResultDetailList;
	}

	
}

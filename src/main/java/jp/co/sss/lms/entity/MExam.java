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
 * 試験マスタエンティティ
 * @author 垣花 武留
 */
@Entity
@Table(name = "m_exam")
public class MExam {
	/** 試験ID */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
		name = "generator",
		table = "id_generator",
		pkColumnName = "pk",
		valueColumnName = "value",
		pkColumnValue = "M_EXAM_EXAM_ID",
		allocationSize = 1
		)
	@Column(precision = 10, nullable = false, unique = true)
	private Integer examId;
	
	/** 試験名 */
	@Column(length = 20, nullable = true, unique = true)
	private String examName;
	
	/** 概要 */
	@Column(length = 100, nullable = true, unique = false)
	private String examDescription;
	
	/** 制限時間 */
	@Column(precision = 10, nullable = true, unique = false)
	private Integer limitTime;
	
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
	
	/** 非表示フラグ */
	@Column(precision = 5, nullable = true, unique = false)
	private Short hiddenFlg;
	
	/**  */
	@OneToOne
	@JoinColumn(name="exam_id", referencedColumnName ="examId")
	private TExamSection tExamSection;
	
	/** 試験問題リスト */
	@OneToMany(mappedBy = "mExam")
    private List<MQuestion> mQuestionList;
	
	/** ジャンルプロパティ(外部参照) */
	@ManyToOne
	@JoinColumn(name = "genre_id", referencedColumnName = "genreId")
    private MGenre mGenre;

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamDescription() {
		return examDescription;
	}

	public void setExamDescription(String examDescription) {
		this.examDescription = examDescription;
	}

	public Integer getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Integer limitTime) {
		this.limitTime = limitTime;
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

	public Short getHiddenFlg() {
		return hiddenFlg;
	}

	public void setHiddenFlg(Short hiddenFlg) {
		this.hiddenFlg = hiddenFlg;
	}

	public TExamSection getTExamSection() {
		return tExamSection;
	}

	public void setTExamSection(TExamSection tExamSection) {
		this.tExamSection = tExamSection;
	}

	public List<MQuestion> getMQuestionList() {
		return mQuestionList;
	}

	public void setMQuestionList(List<MQuestion> mQuestionList) {
		this.mQuestionList = mQuestionList;
	}
	
}

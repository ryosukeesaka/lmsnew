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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 試験問題マスタエンティティ
 * @author 垣花 武留
 */
@Entity
@Table(name="m_question")
public class MQuestion {
	
	/** 問題ID */ 
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator",
	    table = "id_generator",
	    pkColumnName = "pk",
	    valueColumnName = "value",
	    pkColumnValue = "M_QUESTION_QUESTION_ID",
	    allocationSize = 1
	    )
	@Column(precision = 10, nullable = false, unique = true)
	private Integer questionId;
	
	/** 問題 */
    @Column(length = 10000, nullable = true, unique = false)
	private String question;
	
    /** 点数 */
    @Column(precision = 5, nullable = true, unique = false)
	private Short grade;
	
    /** 正当 */
    @Column(precision = 5, nullable = true, unique = false)
	private Short answerNum;
	
    /** 選択肢1 */
    @Column(name = "choice_1", length = 10000, nullable = true, unique = false)
	private String choice1;
	
    /** 選択肢2 */
    @Column(name = "choice_2", length = 10000, nullable = true, unique = false)
	private String choice2;
	
    /** 選択肢3 */
    @Column(name = "choice_3", length = 10000, nullable = true, unique = false)
	private String choice3;
	
    /** 選択肢4 */
    @Column(name = "choice_4", length = 10000, nullable = true, unique = false)
	private String choice4;
	
    /** 解説 */
    @Column(length = 1000, nullable = true, unique = false)
	private String explain;
	
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
    
    /** 試験マスタ(外部参照) */
    @ManyToOne
    @JoinColumn(name="examId", referencedColumnName = "examId")
    public MExam mExam;
    
    /** ジャンル詳細マスタ(外部参照) */
    @ManyToOne
    @JoinColumn(name="genre_detail_id", referencedColumnName = "genreDetailId")
    public MGenreDetail mGenreDetail;

    @OneToMany(mappedBy="mQuestion")
    public List<TDailyQuestionResult> tDailyQuestionResultList;

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Short getGrade() {
		return grade;
	}

	public void setGrade(Short grade) {
		this.grade = grade;
	}

	public Short getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(Short answerNum) {
		this.answerNum = answerNum;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
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

	public MExam getmExam() {
		return mExam;
	}

	public void setmExam(MExam mExam) {
		this.mExam = mExam;
	}

	public MGenreDetail getmGenreDetail() {
		return mGenreDetail;
	}

	public void setmGenreDetail(MGenreDetail mGenreDetail) {
		this.mGenreDetail = mGenreDetail;
	}

	public List<TDailyQuestionResult> getTDailyQuestionResultList() {
		return tDailyQuestionResultList;
	}

	public void setTDailyQuestionResultList(List<TDailyQuestionResult> tDailyQuestionResultList) {
		this.tDailyQuestionResultList = tDailyQuestionResultList;
	}
    
}

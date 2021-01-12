package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * よくある質問エンティティクラス
 * 
 * @author 菅原 俊大
 */
@Entity
@Table(name = "m_frequently_asked_question")
public class MFrequentlyAskedQuestion {

	/** 質問ID */
	@Id
	private Integer frequentlyAskedQuestionId;

	/** 質問カテゴリ(外部参照) */
	@ManyToOne
	@JoinColumn(name = "frequently_asked_question_category_id", referencedColumnName = "frequentlyAskedQuestionCategoryId")
	private MFrequentlyAskedQuestionCategory mFrequentlyAskedQuestionCategory;

	/** 質問内容 */
	@Column
	private String question;

	/** 回答内容 */
	@Column
	private String answer;

	/** 削除フラグ */
	@Column
	private Short deleteFlg;

	/** 初回作成者 */
	@Column
	private Integer firstCreateUser;

	/** 初回作成日時 */
	@Column
	private Date firstCreateDate;

	/** 最終更新者 */
	@Column
	private Integer lastModifiedUser;

	/** 最終更新日時 */
	@Column
	private Date lastModifiedDate;

	public Integer getFrequentlyAskedQuestionId() {
		return frequentlyAskedQuestionId;
	}

	public void setFrequentlyAskedQuestionId(Integer frequentlyAskedQuestionId) {
		this.frequentlyAskedQuestionId = frequentlyAskedQuestionId;
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

package jp.co.sss.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * よくある質問カテゴリエンティティクラス
 * 
 * @author 菅原 俊大
 */
@Entity
@Table(name = "m_frequently_asked_question_category")
public class MFrequentlyAskedQuestionCategory {

	/** 質問カテゴリID */
	@Id
	private Integer frequentlyAskedQuestionCategoryId;

	/** カテゴリ */
	@Column
	private String frequentlyAskedQuestionCategoryName;

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

	/** 質問リスト */
	@OneToMany(mappedBy = "mFrequentlyAskedQuestionCategory")
	private List<MFrequentlyAskedQuestion> mFrequentlyAskedQuestionList;

	public Integer getFrequentlyAskedQuestionCategoryId() {
		return frequentlyAskedQuestionCategoryId;
	}

	public void setFrequentlyAskedQuestionCategoryId(Integer frequentlyAskedQuestionCategoryId) {
		this.frequentlyAskedQuestionCategoryId = frequentlyAskedQuestionCategoryId;
	}

	public String getFrequentlyAskedQuestionCategoryName() {
		return frequentlyAskedQuestionCategoryName;
	}

	public void setFrequentlyAskedQuestionCategoryName(String frequentlyAskedQuestionCategoryName) {
		this.frequentlyAskedQuestionCategoryName = frequentlyAskedQuestionCategoryName;
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

	public void setmFrequentlyAskedQuestionList(List<MFrequentlyAskedQuestion> mFrequentlyAskedQuestionList) {
		this.mFrequentlyAskedQuestionList = mFrequentlyAskedQuestionList;
	}
	
	public List<MFrequentlyAskedQuestion> getMFrequentlyAskedQuestionList() {
		return mFrequentlyAskedQuestionList;
	}

	public void setMFrequentlyAskedQuestionList(List<MFrequentlyAskedQuestion> mFrequentlyAskedQuestionList) {
		this.mFrequentlyAskedQuestionList = mFrequentlyAskedQuestionList;
	}

}

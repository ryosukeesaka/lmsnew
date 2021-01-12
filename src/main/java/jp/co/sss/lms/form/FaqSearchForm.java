package jp.co.sss.lms.form;

/**
 * よくある質問入力フォーム
 * 
 * @author 菅原 俊大
 */
public class FaqSearchForm {

	/** キーワード */
	private String keyword;
	/** 質問カテゴリID */
	private String frequentlyAskedQuestionCategoryId;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getFrequentlyAskedQuestionCategoryId() {
		return frequentlyAskedQuestionCategoryId;
	}

	public void setFrequentlyAskedQuestionCategoryId(String frequentlyAskedQuestionCategoryId) {
		this.frequentlyAskedQuestionCategoryId = frequentlyAskedQuestionCategoryId;
	}

}

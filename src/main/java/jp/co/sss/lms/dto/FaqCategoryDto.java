package jp.co.sss.lms.dto;

/**
 * よくある質問カテゴリDTOクラス
 * 
 * @author 菅原 俊大
 */
public class FaqCategoryDto {

	/** 質問カテゴリID */
	private Integer frequentlyAskedQuestionCategoryId;

	/** 質問カテゴリ名 */
	private String frequentlyAskedQuestionCategoryName;

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

}

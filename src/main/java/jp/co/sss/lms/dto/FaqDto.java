package jp.co.sss.lms.dto;

/**
 * よくある質問DTOクラス
 * 
 * @author 菅原 俊大
 */
public class FaqDto {

	/** FAQID */
	private Integer frequentlyAskedQuestionId;

	/** 質問内容 */
	private String question;

	/** 回答内容 */
	private String answer;

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

}

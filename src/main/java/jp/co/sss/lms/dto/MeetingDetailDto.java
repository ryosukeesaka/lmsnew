package jp.co.sss.lms.dto;

/**
 * MeetingDetailDto
 * 
 * 面談DTOクラス
 * 
 * @author 横山
 */

public class MeetingDetailDto {

    private String question;
    private String answer;
    private String follow;
    private Short questionType;
    
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
	
	public String getFollow() {
		return follow;
	}
	
	public void setFollow(String follow) {
		this.follow = follow;
	}
	
	public Short getQuestionType() {
		return questionType;
	}
	
	public void setQuestionType(Short questionType) {
		this.questionType = questionType;
	}

}

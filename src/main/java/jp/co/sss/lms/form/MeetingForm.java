package jp.co.sss.lms.form;

import org.springframework.stereotype.Component;

/**
 * MeetingForm
 * 面談画面のフォーム
 * 
 * @author 横山
 *
 */
@Component

public class MeetingForm {
	
	//面談ID
	private String meetingId;
	//ユーザーID
    private String lmsUserId;
    //質問
    private String question;
    //回答
    private String answer;
    //フォロー
    private String follow;
    public String getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	public String getLmsUserId() {
		return lmsUserId;
	}
	public void setLmsUserId(String lmsUserId) {
		this.lmsUserId = lmsUserId;
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
	public String getFollow() {
		return follow;
	}
	public void setFollow(String follow) {
		this.follow = follow;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	private String questionType;

}

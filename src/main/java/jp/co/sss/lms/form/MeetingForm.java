package jp.co.sss.lms.form;

import jp.co.sss.lms.util.Constants;

public class MeetingForm {
	
	private Integer meetingId;
    private Integer lmsUserId;
    private String[] question = new String[Constants.FIELD_NUM_MEETING_TQ + Constants.FIELD_NUM_MEETING_HU];
    private String[] answer = new String[Constants.FIELD_NUM_MEETING_TQ + Constants.FIELD_NUM_MEETING_HU];
    private String[] follow = new String[Constants.FIELD_NUM_MEETING_TQ + Constants.FIELD_NUM_MEETING_HU];
    private String[] questionType = new String[Constants.FIELD_NUM_MEETING_TQ + Constants.FIELD_NUM_MEETING_HU];

	public Integer getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}
	public Integer getLmsUserId() {
		return lmsUserId;
	}
	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}
	public String[] getQuestion() {
		return question;
	}
	public void setQuestion(String[] question) {
		this.question = question;
	}
	public String[] getAnswer() {
		return answer;
	}
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}
	public String[] getFollow() {
		return follow;
	}
	public void setFollow(String[] follow) {
		this.follow = follow;
	}
	public String[] getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String[] questionType) {
		this.questionType = questionType;
	}

}

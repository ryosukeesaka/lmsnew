package jp.co.sss.lms.form;

import java.util.List;

public class QuestionForm {
	
	private Integer questionId;
	private String question;
	private String genreDetailName;
	private List<String> answerList;
	private short answerNum;
	private String explain;

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

	public String getGenreDetailName() {
		return genreDetailName;
	}

	public void setGenreDetailName(String genreDetailName) {
		this.genreDetailName = genreDetailName;
	}

	public List<String> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<String> answerList) {
		this.answerList = answerList;
	}

	public short getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(short answerNum) {
		this.answerNum = answerNum;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}

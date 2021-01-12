package jp.co.sss.lms.dto;

import java.util.List;

/**
 * QuestionDtoクラス
 * ControllerからViewへ試験問題情報を渡す。
 * @author 垣花 武留
 *
 */
public class ExamServiceQuestionDto {
	
	/** 問題ID */
	private Integer questionId;
	
	/** 問題 */
	private String question;
	
	/** ジャンル詳細 */
	private String genreDetailName;
	
	/** 選択肢リスト */
	private List<String> answerList;
	
	/** 正答 */
	private Short answerNum;
	
	/** 回答 */
	private Short reply;
	
	/** 解説 */
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

	public Short getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(Short answerNum) {
		this.answerNum = answerNum;
	}

	public Short getReply() {
		return reply;
	}

	public void setReply(Short reply) {
		this.reply = reply;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

}

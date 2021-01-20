package jp.co.sss.lms.form;

import java.util.List;

/**
 * 試験問題フォーム
 * 試験問題の情報を取得し、値を保持します。
 * 
 * @author m-uno
 *
 */
public class ExamPlayForm {
	
	/**LMSユーザID*/
	private Integer lmsUserId;
	/**アカウントID*/
	private Integer accountId;
	/**試験ID*/
	private Integer examId;
	/**セクションID*/
	private Integer sectionId;
	/**試験・セクション紐付けID*/
	private Integer examSectionId;
	/**試験結果ID*/
	private Integer examResultId;
	/**得点*/
	private Short score;
	/**回答*/
	private Integer[] answer;
	/**所有時間*/
	private Integer time;
	/**試験問題*/
	private List<QuestionForm> questionList;

	public Integer getLmsUserId() {
		return lmsUserId;
	}

	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getExamSectionId() {
		return examSectionId;
	}

	public void setExamSectionId(Integer examSectionId) {
		this.examSectionId = examSectionId;
	}

	public Integer getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(Integer examResultId) {
		this.examResultId = examResultId;
	}

	public Short getScore() {
		return score;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	public Integer[] getAnswer() {
		return answer;
	}

	public void setAnswer(Integer[] answer) {
		this.answer = answer;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public List<QuestionForm> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionForm> questionList) {
		this.questionList = questionList;
	}

}

package jp.co.sss.lms.form;

import java.sql.Date;
import java.sql.Time;

/**
 * 試験情報取得フォーム
 * 試験情報を取得し、保持します。
 * 
 * @author m-uno
 *
 */
public class ExamForm {
	
	/**試験問題数*/
	private Integer numOfQuestion = 1;
	/**試験ID*/
	private Integer examId;
	/**試験名*/
	private String examName;
	/**試験概要*/
	private String examDescription;
	/**制限時間*/
	private Time limitTime;
	/**ジャンルID*/
	private Integer genreId;
	/**過去の受講生検索期間*/
	private Date pastTimeLabel;
	/**過去の受講生検索フラグ*/
	private Short pastFlg;

	public Integer getNumOfQuestion() {
		return numOfQuestion;
	}

	public void setNumOfQuestion(Integer numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamDescription() {
		return examDescription;
	}

	public void setExamDescription(String examDescription) {
		this.examDescription = examDescription;
	}

	public Time getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Time limitTime) {
		this.limitTime = limitTime;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public Date getPastTimeLabel() {
		return pastTimeLabel;
	}

	public void setPastTimeLabel(Date pastTimeLabel) {
		this.pastTimeLabel = pastTimeLabel;
	}

	public Short getPastFlg() {
		return pastFlg;
	}

	public void setPastFlg(Short pastFlg) {
		this.pastFlg = pastFlg;
	}

	
}

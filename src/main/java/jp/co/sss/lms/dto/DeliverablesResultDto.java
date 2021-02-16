package jp.co.sss.lms.dto;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@RequestScope
public class DeliverablesResultDto {

	// 成果物名
	private String deliverablesName;
	// 日付
	private Date date;
	// 採点
	private int score;
	
	private short scoreFlg;
	// フィードバック
	private short feedbackFlg;

	private String feedback;
	
	private String content;
	
	public String getDeliverablesName() {
		return deliverablesName;
	}

	public void setDeliverablesName(String deliverablesName) {
		this.deliverablesName = deliverablesName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public short getFeedbackFlg() {
		return feedbackFlg;
	}

	public void setFeedbackFlg(short feedbackFlg) {
		this.feedbackFlg = feedbackFlg;
	}

	public short getScoreFlg() {
		return scoreFlg;
	}

	public void setScoreFlg(short scoreflg) {
		this.scoreFlg = scoreflg;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}








}

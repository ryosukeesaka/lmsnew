package jp.co.sss.lms.dto;

import java.sql.Timestamp;

/**
 * ExamResultDtoクラス
 * ControllerからViewへ試験結果を渡す。
 * @author 垣花 武留
 *
 */
public class ExamServiceExamResultDto {
	
	/** 試験結果ID */
	private Integer examResultId;
	
	/** LMSユーザID */
	private Integer lmsUserId;
	
	/** 点数 */
	private Double score;
	
	/** 回答 */
	private Short reply;
	
	/** 実施日時 */
	private Timestamp date;
	
	/** 試験Dto */
	private ExamServiceExamDto examDto;

	
	public Integer getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(Integer examResultId) {
		this.examResultId = examResultId;
	}

	public Integer getLmsUserId() {
		return lmsUserId;
	}

	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Short getReply() {
		return reply;
	}

	public void setReply(Short reply) {
		this.reply = reply;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public ExamServiceExamDto getExamDto() {
		return examDto;
	}

	public void setExamDto(ExamServiceExamDto examDto) {
		this.examDto = examDto;
	}
}

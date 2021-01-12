package jp.co.sss.lms.dto;

import java.util.List;

/**
 * ExamDtoクラス
 * ControllerからViewへ試験情報を渡す。
 * @author 垣花 武留
 *
 */
public class ExamServiceExamDto {
	
	/** 試験ID */
	private Integer examId;
	
	/** コースID */
	private Integer courseId;
	
	/** セクションID */
	private Integer sectionId;
	
	/** 試験・セクション紐付けID */
	private Integer examSectionId;
	
	/** 試験名 */
	private String examName;
	
	/** 制限時間 */
	private Integer limitTime;
	
	/** 問題数 */
	private Integer numOfQuestion;
	
	/** 問題リスト */
	private List<ExamServiceQuestionDto> questionDtoList;
	
	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Integer getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Integer limitTime) {
		this.limitTime = limitTime;
	}

	public Integer getNumOfQuestion() {
		return numOfQuestion;
	}

	public void setNumOfQuestion(Integer numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
	}

	public List<ExamServiceQuestionDto> getQuestionDtoList() {
		return questionDtoList;
	}

	public void setQuestionDtoList(List<ExamServiceQuestionDto> questionDtoList) {
		this.questionDtoList = questionDtoList;
	}

}

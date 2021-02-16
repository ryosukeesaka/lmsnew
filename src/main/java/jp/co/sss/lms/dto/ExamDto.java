package jp.co.sss.lms.dto;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.sss.lms.entity.MQuestion;
import jp.co.sss.lms.entity.TExamResult;

/**
 * ユーザー詳細の試験DTOのクラス
 * 
 * @author ヒサオカ
 */
public class ExamDto {

    /** 試験・セクション紐づけID */
    public Integer examSectionId;

    /** 試験ID */
    public Integer examId;

    /** 試験名 */
    public String examName;

    /** 公開フラグ */
    public boolean publicFlg = false;
    
    /** 点数 */
    public Short score;
    
    /** 初回作成日時 */
    public Date firstCreateDate;	
    
    /** 試験結果ID */
    public Integer examResultId;
    

    /** 制限時間 */
    public Integer limitTime;

    /** 試験カテゴリID */
    public Integer genreId;

    /** 試験カテゴリ名 */
    public String genreName;

    /** 公開日時 */
    public Timestamp publicDate;

    /** 問題数 */
    public Integer numOfQuestion;

    /** 平均点数 */
    public Double avgScore;


    /** セクションID(パンくず用に追加) */
    public Integer sectionId;
    /** コースID(パンくず用に追加) */
    public Integer courseId;
    
    
    
	public Integer getExamSectionId() {
		return examSectionId;
	}
	public void setExamSectionId(Integer examSectionId) {
		this.examSectionId = examSectionId;
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
	public Integer getLimitTime() {
		return limitTime;
	}
	public void setLimitTime(Integer limitTime) {
		this.limitTime = limitTime;
	}
	public Integer getGenreId() {
		return genreId;
	}
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public Timestamp getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(Timestamp publicDate) {
		this.publicDate = publicDate;
	}
	public Integer getNumOfQuestion() {
		return numOfQuestion;
	}
	public void setNumOfQuestion(Integer numOfQuestion) {
		this.numOfQuestion = numOfQuestion;
	}
	public Double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}
	public boolean isPublicFlg() {
		return publicFlg;
	}
	public void setPublicFlg(boolean publicFlg) {
		this.publicFlg = publicFlg;
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Short getScore() {
		return score;
	}
	public void setScore(Short score) {
		this.score = score;
	}
	public Date getFirstCreateDate() {
		return firstCreateDate;
	}
	public void setFirstCreateDate(Date firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}
	public Integer getExamResultId() {
		return examResultId;
	}
	public void setExamResultId(Integer examResultId) {
		this.examResultId = examResultId;
	}

}

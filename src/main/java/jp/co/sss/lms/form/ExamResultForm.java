package jp.co.sss.lms.form;

import java.sql.Date;

/**
 * 試験結果フォーム
 * 試験結果の情報を取得し、値を保持します。
 * @author m-uno
 *
 */
public class ExamResultForm {

	/**試験ID*/
	private Integer examId;
	/**企業ID*/
	private Integer companyId;
	/**研修会場ID*/
	private Integer placeId;
	/**コースID*/
	private Integer courseId;
	/**過去の受講生検索期間*/
	private Date pastTimelabel;
	/**過去の受講生検索フラグ*/
	private Short pastFlg;

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Date getPastTimelabel() {
		return pastTimelabel;
	}

	public void setPastTimelabel(Date pastTimelabel) {
		this.pastTimelabel = pastTimelabel;
	}

	public Short getPastFlg() {
		return pastFlg;
	}

	public void setPastFlg(Short pastFlg) {
		this.pastFlg = pastFlg;
	}
}

package jp.co.sss.lms.form;

/**
 * 試験結果詳細フォーム
 * 試験結果の詳細情報を取得し、値を保持します。
 * @author m-uno
 *
 */
public class ExamResultDetailForm {
	
	/**試験ID*/
	private Integer examId;
	/**試験・セクションID*/
	private Integer examSectionId;
	/**LMSユーザーID*/
	private Integer lmsUserId;
	/**アカウントID*/
	private Integer accountId;

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Integer getExamSectionId() {
		return examSectionId;
	}

	public void setExamSectionId(Integer examSectionId) {
		this.examSectionId = examSectionId;
	}

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
	
}

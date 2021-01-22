package jp.co.sss.lms.form;

/**
 * セクションフォーム
 * @author 橋爪 優哉
 *
 */
public class SectionForm {

	/** セクションID */
	private String sectionId;
	/** 企業アカウントID */
	private Integer accountId;
	/** LMSユーザID */
	private Integer lmsUserId;
	/** ユーザID */
	private Integer userId;
	
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getLmsUserId() {
		return lmsUserId;
	}
	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}

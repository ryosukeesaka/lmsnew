package jp.co.sss.lms.form;

public class ReportForm {
	private Integer dailyReportId;
	private Integer formSectionId;
	private Integer formCourseId;
	private String date;
	private Integer lmsUserId;
	private Integer sectionId;
	private Integer courseId;
	private Integer dailyReportSubmitDetailFirstId;
	private Integer intelligibilityFirstId;
	private Integer dailyReportSubmitId;
	private String role;
	private Integer accountId;
	
	private String[] contents; //★
	private String[] intelligentFieldName;
	private Integer[] intelligentFieldValue;
	private Integer[] rangeTo;
	private Integer[] rangeFrom;
	
	public Integer getDailyReportId() {
		return dailyReportId;
	}
	public void setDailyReportId(Integer dailyReportId) {
		this.dailyReportId = dailyReportId;
	}
	public Integer getFormSectionId() {
		return formSectionId;
	}
	public void setFormSectionId(Integer formSectionId) {
		this.formSectionId = formSectionId;
	}
	public Integer getFormCourseId() {
		return formCourseId;
	}
	public void setFormCourseId(Integer formCourseId) {
		this.formCourseId = formCourseId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getLmsUserId() {
		return lmsUserId;
	}
	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
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
	public Integer getDailyReportSubmitDetailFirstId() {
		return dailyReportSubmitDetailFirstId;
	}
	public void setDailyReportSubmitDetailFirstId(Integer dailyReportSubmitDetailFirstId) {
		this.dailyReportSubmitDetailFirstId = dailyReportSubmitDetailFirstId;
	}
	public Integer getIntelligibilityFirstId() {
		return intelligibilityFirstId;
	}
	public void setIntelligibilityFirstId(Integer intelligibilityFirstId) {
		this.intelligibilityFirstId = intelligibilityFirstId;
	}
	public Integer getDailyReportSubmitId() {
		return dailyReportSubmitId;
	}
	public void setDailyReportSubmitId(Integer dailyReportSubmitId) {
		this.dailyReportSubmitId = dailyReportSubmitId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String[] getContents() {
		return contents;
	}
	public void setContents(String[] contents) {
		this.contents = contents;
	}
	public String[] getIntelligentFieldName() {
		return intelligentFieldName;
	}
	public void setIntelligentFieldName(String[] intelligentFieldName) {
		this.intelligentFieldName = intelligentFieldName;
	}
	public Integer[] getIntelligentFieldValue() {
		return intelligentFieldValue;
	}
	public void setIntelligentFieldValue(Integer[] intelligentFieldValue) {
		this.intelligentFieldValue = intelligentFieldValue;
	}
	public Integer[] getRangeTo() {
		return rangeTo;
	}
	public void setRangeTo(Integer[] rangeTo) {
		this.rangeTo = rangeTo;
	}
	public Integer[] getRangeFrom() {
		return rangeFrom;
	}
	public void setRangeFrom(Integer[] rangeFrom) {
		this.rangeFrom = rangeFrom;
	}
	
}

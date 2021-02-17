package jp.co.sss.lms.dto;


/**
 * コース情報サービス セクションDTO
 * 
 * @author 高谷
 * 
 */

public class CourseUserServiceDto {
	/** LMSユーザーID */
	private Integer lmsUserId;
	/** ユーザーID */
	private Integer userId;
	/** ユーザー名 */
    private String userName;
	/** ロール */
	private String role;
	/** ログインID */
	private String loginId;
	/** 研修を通じてどのようになってほしいか */
	private String hopeViaTraning;
	/**プログラム経験 */
	private Short programmingExperience;
	/** 企業名 */
	private String companyName;
	/** コースID */
	private Integer courseId;
	/** コース名 */
	private String courseName;
	/** 会場名 */
	private String placeName;
	/** 備考 */
	private String placeNote;
	/** 会場詳細 */
	private String placeDescription;
	
	//企業ID
	private Integer companyId;
	
	/** アカウントid */
	private Integer accountId;
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getHopeViaTraning() {
		return hopeViaTraning;
	}
	public void setHopeViaTraning(String hopeViaTraning) {
		this.hopeViaTraning = hopeViaTraning;
	}
	public Short getProgrammingExperience() {
		return programmingExperience;
	}
	public void setProgrammingExperience(Short programmingExperience) {
		this.programmingExperience = programmingExperience;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceNote() {
		return placeNote;
	}
	public void setPlaceNote(String placeNote) {
		this.placeNote = placeNote;
	}
	public String getPlaceDescription() {
		return placeDescription;
	}
	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
}

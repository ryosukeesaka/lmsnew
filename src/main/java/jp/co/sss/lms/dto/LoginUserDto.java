package jp.co.sss.lms.dto;

import java.io.Serializable;
import java.security.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginUserDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 企業アカウントID
	private Integer accountId;
	// ユーザーID
	private Integer userId;
	// LMSユーザーID
	private Integer lmsUserId;
	// ユーザー名
	private String userName;
	// ロール
	private String role;
	// 企業ID
	private Integer companyId;
	// 企業名
	private String companyName;
	// 会場ID
	private Integer placeId;
	// 会場名
	private String placeName;
	// コースID
	private Integer courseId;
	// セキュリティ同意フラグ
	private Short securityAgreeFlg;
	// パスワード変更日付
	private Timestamp passwordChangeDate;
	// サポート表示
	private Short supportAvailable;
	// メールアドレス
	private String mailAddress;
	// 途中退校フラグ
	private Integer leaveFlg;
	// 途中退校日
	private Timestamp leaveDate;
    // ファイル共有フラグ
	private Short fileShareFlg;
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLmsUserId() {
		return lmsUserId;
	}
	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getPlaceId() {
		return placeId;
	}
	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Short getSecurityAgreeFlg() {
		return securityAgreeFlg;
	}
	public void setSecurityAgreeFlg(Short securityAgreeFlg) {
		this.securityAgreeFlg = securityAgreeFlg;
	}
	public Timestamp getPasswordChangeDate() {
		return passwordChangeDate;
	}
	public void setPasswordChangeDate(Timestamp passwordChangeDate) {
		this.passwordChangeDate = passwordChangeDate;
	}
	public Short getSupportAvailable() {
		return supportAvailable;
	}
	public void setSupportAvailable(Short supportAvailable) {
		this.supportAvailable = supportAvailable;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public Integer getLeaveFlg() {
		return leaveFlg;
	}
	public void setLeaveFlg(Integer leaveFlg) {
		this.leaveFlg = leaveFlg;
	}
	public Timestamp getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Timestamp leaveDate) {
		this.leaveDate = leaveDate;
	}
	public Short getFileShareFlg() {
		return fileShareFlg;
	}
	public void setFileShareFlg(Short fileShareFlg) {
		this.fileShareFlg = fileShareFlg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

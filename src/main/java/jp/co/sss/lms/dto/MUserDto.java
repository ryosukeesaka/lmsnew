package jp.co.sss.lms.dto;

import java.sql.Timestamp;

import jp.co.sss.lms.dto.LmsUserDtoBase;

public class MUserDto  extends LmsUserDtoBase {

    private static final long serialVersionUID = 1L;


    /** ユーザID */
    public Integer userId;

    /** ログインID */
    public String loginId;

    /** パスワード */
    public String password;

    /** ユーザー名 */
    public String userName;

    /** 権限 */
    public String authority;
    
    /** ロール */
    public String role;
    /** 企業アカウントID */
    public Integer accountId;

    /** セキュリティ同意フラグ */
    public Integer securityAgreeFlg;

    /** パスワード変更日付 */
    public Timestamp passwordChangeDate;

    /** 削除フラグ */
    public Integer deleteFlg;

    /** 初回作成者 */
    public Integer firstCreateUser;

    /** 初回作成日時 */
    public Timestamp firstCreateDate;

    /** 最終更新者 */
    public Integer lastModifiedUser;

    /** 最終更新日時 */
    public Timestamp lastModifiedDate;

    /** よみがな */
    public String kana;

    /** メールアドレス */
    public String mailAddress;

    /** 助成金カテゴリID */
    public Integer subsidyCategoryId;

    /** 途中退校フラグ */
    public Integer leaveFlg;

    /** 途中退校日 */
    public Timestamp leaveDate;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}


	public void setSecurityAgreeFlg(Integer securityAgreeFlg) {
		this.securityAgreeFlg = securityAgreeFlg;
	}

	public Timestamp getPasswordChangeDate() {
		return passwordChangeDate;
	}

	public void setPasswordChangeDate(Timestamp passwordChangeDate) {
		this.passwordChangeDate = passwordChangeDate;
	}

	public Integer getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public Integer getFirstCreateUser() {
		return firstCreateUser;
	}

	public void setFirstCreateUser(Integer firstCreateUser) {
		this.firstCreateUser = firstCreateUser;
	}

	public Timestamp getFirstCreateDate() {
		return firstCreateDate;
	}

	public void setFirstCreateDate(Timestamp firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}

	public Integer getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(Integer lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public Integer getSubsidyCategoryId() {
		return subsidyCategoryId;
	}

	public void setSubsidyCategoryId(Integer subsidyCategoryId) {
		this.subsidyCategoryId = subsidyCategoryId;
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


}

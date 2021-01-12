package jp.co.sss.lms.form;

/**
 * ログインフォーム
 * @author kato
 */
public class LoginForm {

	private String loginId;
	private String password;
	private String currentPassword;
	private String passwordConfirm;
	
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

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public LoginForm(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

}

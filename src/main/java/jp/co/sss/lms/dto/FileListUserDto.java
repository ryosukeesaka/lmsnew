package jp.co.sss.lms.dto;

/**
 * ファイルリストユーザーDTO
 * @author 平賀 知誉
 *
 */
public class FileListUserDto {
	
//	private static final long serialVersionUID = 605337909951950820L;

	/**	共有ユーザーID */
    private Integer fssUserId;

    /** ログインユーザーチェック */
    private boolean isLoginUser;

    /** ユーザー名 */
    private String userName;

    /**
     * 共有ユーザーIDのgetterメソッド
     * @return fssUserId
     */
	public Integer getFssUserId() {
		return fssUserId;
	}

	/**
	 * 共有ユーザーIDのsetterメソッド
	 * @param fssUserId
	 */
	public void setFssUserId(Integer fssUserId) {
		this.fssUserId = fssUserId;
	}

	/**
	 * ログインユーザーチェックのgetterメソッド
	 * @return isLoginUser
	 */
	public boolean isLoginUser() {
		return isLoginUser;
	}

	/**
	 * ログインユーザーチェックのsetterメソッド
	 * @param isLoginUser
	 */
	public void setLoginUser(boolean isLoginUser) {
		this.isLoginUser = isLoginUser;
	}

	/**
	 * ユーザー名のgetterメソッド
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ユーザー名のsetterメソッド
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}

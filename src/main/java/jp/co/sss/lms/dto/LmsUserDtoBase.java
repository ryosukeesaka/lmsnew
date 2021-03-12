package jp.co.sss.lms.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class LmsUserDtoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/** アカウントID */
	public Integer accountId;

	/** ユーザーID */
	public Integer userId;

	/** LMSユーザーID */
	public Integer lmsUserId;

	/** ユーザー名 */
	public String userName;

	/** ロール */
	public String role;

	/** 管理者フラグ */
	public String adminFlg;

	/** 企業ID */
	public Integer companyId;

	/** 会場ID */
	public Integer placeId;

	/** コースID */
	public Integer courseId;

	/** セキュリティ同意フラグ */
	public Short securityAgreeFlg;

	/** パスワード変更日付 */
	public Timestamp passwordChangeDate;

	/** パスワードNG回数 */
	public Integer passwordNgCount;

	/** パスワードNG日付 */
	public String passwordNgDate;

	/** サポート要否 */
	public Short supportAvailable;

	/** メールアドレス */
	public String mailAddress;

	/** 途中退校フラグ */
	public Integer leaveFlg;

	/** 途中退校日 */
	public Timestamp leaveDate;

    /** ファイル共有フラグ */
    public Short fileShareFlg;

	/**
	 * アカウントIDを取得します。
	 *
	 * @return アカウントID
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * ユーザーIDを取得します。
	 *
	 * @return ユーザーID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * LMSユーザーIDを取得します。
	 *
	 * @return LMSユーザーID
	 */
	public Integer getLmsUserId() {
		return lmsUserId;
	}

	/**
	 * ユーザー名を取得します。
	 *
	 * @return ユーザー名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ロールを取得します。
	 *
	 * @return ロール
	 */
	public String getRole() {
		return role;
	}

	/**
	 * 管理者フラグを取得します。
	 *
	 * @return 管理者フラグ
	 */
	public String getAdminFlg() {
		return adminFlg;
	}

	/**
	 * 企業IDを取得します。
	 *
	 * @return 企業ID
	 */
	public Integer getCompanyId() {
		return companyId;
	}

	/**
	 * 会場IDを取得します。
	 *
	 * @return 会場ID
	 */
	public Integer getPlaceId() {
		return placeId;
	}

	/**
	 * コースIDを取得します。
	 *
	 * @return コースID
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * セキュリティ同意フラグを取得します。
	 *
	 * @return セキュリティ同意フラグ
	 */
	public Short getSecurityAgreeFlg() {
		return securityAgreeFlg;
	}

	/**
	 * パスワード変更日付を取得します。
	 *
	 * @return パスワード変更日付
	 */
	public Timestamp getPasswordChangeDate() {
		return passwordChangeDate;
	}

	/**
	 * パスワードNG回数を取得します。
	 *
	 * @return パスワードNG回数
	 */
	public Integer getPasswordNgCount() {
		return passwordNgCount;
	}

	/**
	 * パスワードNG日付を取得します。
	 *
	 * @return パスワードNG日付
	 */
	public String getPasswordNgDate() {
		return passwordNgDate;
	}

	/**
	 * サポート要否を取得します。
	 *
	 * @return サポート要否
	 */
	public Short getSupportAvailable() {
		return supportAvailable;
	}

	/**
	 * メールアドレスを取得します。
	 *
	 * @return メールアドレス
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * 途中退校フラグを取得します。
	 *
	 * @return 途中退校フラグ
	 */
	public Integer getLeaveFlg() {
		return leaveFlg;
	}

	/**
	 * 途中退校日を取得します。
	 *
	 * @return 途中退校日
	 */
	public Timestamp getLeaveDate() {
		return leaveDate;
	}

    /** ファイル共有フラグ */
    public Short getFileShareFlg(){
        return fileShareFlg;
    }
}

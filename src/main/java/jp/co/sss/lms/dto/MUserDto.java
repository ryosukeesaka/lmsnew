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

}

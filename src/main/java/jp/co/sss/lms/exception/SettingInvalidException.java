package jp.co.sss.lms.exception;

import jp.co.sss.lms.util.SettingUtil;

/**
 * 設定ファイル（setting.properties）に誤りがある場合の例外
 * 
 * @author 東　茉奈
 *
 */
public class SettingInvalidException extends RuntimeException {
	/** */
    private static final long serialVersionUID = -5411962151020657179L;

    private static final String COMMON_MESSAGE = "設定ファイル（setting.properties）に誤りがあります。";

    /**
     * 設定ファイルに誤りがある場合の例外
     */
    public SettingInvalidException() {
        super(COMMON_MESSAGE);
    }

    /**
     * 設定ファイルに誤りがある場合の例外
     * @param key 設定ファイルのキー
     */
    public SettingInvalidException(String key) {
        super(COMMON_MESSAGE + "[" + key + "=" + SettingUtil.getProperty(key) + "]");
    }

    /**
     * 設定ファイルに誤りがある場合の例外
     * @param key 設定ファイルのキー
     * @param message メッセージ
     */
    public SettingInvalidException(String key, String message) {
        super(COMMON_MESSAGE + message + "[" + key + "=" + SettingUtil.getProperty(key) + "]");
    }
}

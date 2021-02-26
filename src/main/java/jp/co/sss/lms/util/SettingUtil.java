package jp.co.sss.lms.util;

import jp.co.sss.lms.exception.SettingInvalidException;

import org.springframework.core.env.Environment;

/**
 * 設定ファイル(setting.properties)のユーティリティ
 * 
 * @author 田中 和希
 *
 */
public class SettingUtil {
	private static final String SETTING_FILE_NAME = "setting.properties";

    /**
     * 設定ファイル(setting.properties)の値を取得する。
     * @param key
     * @return
     */
    public static String getProperty(String key) {
    	Environment env = null ;
    	String filename = "";
    	filename += SETTING_FILE_NAME + getProperty(key);
    	
    	@SuppressWarnings("null")
		String setting = env.getProperty(filename);
        //String setting = ResourceUtil.getProperties(SETTING_FILE_NAME).getProperty(key);
        if (setting == null) {
            throw new SettingInvalidException(key, "キーが存在しません。");
        }
        return setting;
    }
}

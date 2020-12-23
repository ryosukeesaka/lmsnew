package jp.co.sss.lms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
	
	private int STRETCH_COUNT = 10;
	
	public String getSaltedAndStrechedPassword(String password, String userId) {
        return getStretchedPassword(getSaltedPassword(password, userId), userId);
    }
	/**
     * salt＋ハッシュ化したパスワードを取得
     */
    private String getSaltedPassword(String password, String userId) {
        String salt = getSha256(userId);
        return getSha256(salt + password);
    }
    
    /**
     * salt + ストレッチングしたパスワードを取得(推奨)
     */
    private String getStretchedPassword(String password, String userId) {
        String salt = getSha256(userId);
        String hash = "";

        for (int i = 0; i < STRETCH_COUNT; i++) {
            hash = getSha256(hash + salt + password);
        }

        return hash;
    }
    
	/**
     * 文字列から SHA256 のハッシュ値を取得
     */
    private String getSha256(String target) {
        MessageDigest md = null;
        StringBuffer buf = new StringBuffer();
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(target.getBytes());
            byte[] digest = md.digest();

            for (int i = 0; i < digest.length; i++) {
                buf.append(String.format("%02x", digest[i]));
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return buf.toString();
    }
}

package jp.co.sss.lms.dto;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * ファイル共有ユーザーDTO
 * @author 平賀 知誉
 *
 */
@Component
public class ShareUserDto {
	
	/** ファイル共有ユーザーID */
    private String fssUserId;

    /** ファイル共有グループ名 */
    private String fssGroupName;

    /** ユーザー名 */
    private String userName;

    
    /**
     * ファイル共有ユーザーIDのgetterメソッド
     * @return fssUserId
     */
	public String getFssUserId() {
		return fssUserId;
	}

	
	/**
	 * ファイル共有ユーザーIDのsetterメソッド
	 * @param fssUserId
	 */
	public void setFssUserId(String fssUserId) {
		this.fssUserId = fssUserId;
	}

	/**
	 * ファイル共有グループ名のgetterメソッド
	 * @return fssGroupName
	 */
	public String getFssGroupName() {
		return fssGroupName;
	}

	/**
	 * ファイル共有グループ名のsetterメソッド
	 * @param fssGroupName
	 */
	public void setFssGroupName(String fssGroupName) {
		this.fssGroupName = fssGroupName;
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
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ShareUserDto)) {
            return false;
        }

        ShareUserDto dto = (ShareUserDto)obj;
        boolean isSameFssUserId = StringUtils.pathEquals(this.fssUserId, dto.fssUserId);
        boolean isSameFssGroupName = StringUtils.pathEquals(this.fssGroupName, dto.fssGroupName);
        boolean isSameUserName = StringUtils.pathEquals(this.userName, dto.userName);

        return isSameFssUserId && isSameFssGroupName && isSameUserName;
    }

}

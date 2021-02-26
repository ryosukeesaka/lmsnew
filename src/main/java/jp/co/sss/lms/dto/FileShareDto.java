package jp.co.sss.lms.dto;

/**
 * ファイル共有DTO
 * 
 * @author 平賀 知誉
 *
 */
public class FileShareDto {

	/** ファイル共有ID */
	private Integer fileId;

	/** ファイル名 */
	private String fileName;

	/** 変更日時 */
	private String modifiedDate;

	/** ファイルタイプ */
	private String fileType;

	/** ファイルサイズ */
	private String fileSize;

	/** 所有ユーザー */
	private String owner;

	/** 共有元ユーザー(所有者) */
	private String sharePerson;

	/** 共有ユーザー */
	private String sharedPerson;

	/**
	 * ファイル共有IDのgetterメソッド
	 * 
	 * @return fileId
	 */
	public Integer getFileId() {
		return fileId;
	}

	/**
	 * ファイル共有IDのsetterメソッド
	 * 
	 * @param fileId
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	/**
	 * ファイル名のgetterメソッド
	 * 
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * ファイル名のsetterメソッド
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 変更日時のgetterメソッド
	 * 
	 * @return modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * 変更日時のsetterメソッド
	 * 
	 * @param modifiedDate
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * ファイルタイプのgetterメソッド
	 * 
	 * @return fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * ファイルタイプのsetterメソッド
	 * 
	 * @param fileType
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * ファイルサイズのgetterメソッド
	 * 
	 * @return fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}

	/**
	 * ファイルサイズのsetterメソッド
	 * 
	 * @param fileSize
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * 所有ユーザーのgetterメソッド
	 * 
	 * @return owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * 所有ユーザーのsetterメソッド
	 * 
	 * @param owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * 共有元ユーザーのgetterメソッド
	 * 
	 * @return sharePerson
	 */
	public String getSharePerson() {
		return sharePerson;
	}

	/**
	 * 共有元ユーザーのsetterメソッド
	 * 
	 * @param sharePerson
	 */
	public void setSharePerson(String sharePerson) {
		this.sharePerson = sharePerson;
	}

	/**
	 * 共有ユーザーのgetterメソッド
	 * 
	 * @return sharedPerson
	 */
	public String getSharedPerson() {
		return sharedPerson;
	}

	/**
	 * 共有ユーザーのsetterメソッド
	 * 
	 * @param sharedPerson
	 */
	public void setSharedPerson(String sharedPerson) {
		this.sharedPerson = sharedPerson;
	}

}
package jp.co.sss.lms.dto;

/**
 * ファイルDTO
 * @author 平賀 知誉
 *
 */
public class FileDto {
	
	/** ファイルID */
	private Integer fileId;
    
	/** ファイル名 */
	private String fileName;
    
	/** ファイルパス */
	private String filePath;
    
	/** ファイルサイズ */
	private String fileSize;
	
	
	/**
	 * ファイルIDのgetterメソッド
	 * @return fileId
	 */
	public Integer getFileId() {
		return fileId;
	}
	
	/**
	 * ファイルIDのsetterメソッド
	 * @param fileId
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
	/**
	 * ファイル名のgetterメソッド
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * ファイル名のsetterメソッド
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * ファイルパスのgetterメソッド
	 * @return filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * ファイルパスのsetterメソッド
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * ファイルサイズのgetterメソッド
	 * @return fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}
	
	/**
	 * ファイルサイズのsetterメソッド
	 * @param fileSize
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

}

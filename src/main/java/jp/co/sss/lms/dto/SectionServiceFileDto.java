package jp.co.sss.lms.dto;

/**
 * @author 永井佳奈
 *
 */
public class SectionServiceFileDto {
	
	/** ファイルID */
	private String fileId;
	/** ファイル名 */
	private String fileName;
	/** ファイルパス */
	private String filePath;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}

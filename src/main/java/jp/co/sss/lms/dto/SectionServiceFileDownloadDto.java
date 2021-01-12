package jp.co.sss.lms.dto;

import java.util.HashMap;
import java.util.Map;

/**
* セクションサービスファイルダウンロードDTO
* @author 眞鍋 美佳
*/
public class SectionServiceFileDownloadDto {
	/** ファイルIDマップ */
	private Map<String, Integer> fileIdMap = new HashMap<>();

	public Map<String, Integer> getFileIdMap() {
		return fileIdMap;
	}

	public void setFileIdMap(Map<String, Integer> fileIdMap) {
		this.fileIdMap = fileIdMap;
	}
}

package jp.co.sss.lms.form;

import org.springframework.web.multipart.MultipartFile;


public class FileUploadForm {

    /** アップロードファイル */
    private MultipartFile uploadFile;

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}
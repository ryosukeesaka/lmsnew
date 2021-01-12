package jp.co.sss.lms.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * 成果物アップロードフォーム
 * 
 * @author 山脇教由樹
 */
public class DeliverablesForm {

	/** セクションID */
	private Integer sectionId;
	/** 成果物・セクションID */
	private Integer deliverablesSectionId;
	/** アップロードファイル */
	private MultipartFile uploadFile;

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getDeliverablesSectionId() {
		return deliverablesSectionId;
	}

	public void setDeliverablesSectionId(Integer deliverablesSectionId) {
		this.deliverablesSectionId = deliverablesSectionId;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
}

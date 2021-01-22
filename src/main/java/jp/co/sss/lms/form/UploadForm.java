package jp.co.sss.lms.form;

/**
 * Frontから送信された成果物登録に必要となるパラメータを格納するフォーム 
 * @author 山脇教由樹
 *
 */
public class UploadForm {
	/** セクションID */
	private String sectionId;
	/** 成果物・セクションID */
	private String deliverablesSectionId;
	
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getDeliverablesSectionId() {
		return deliverablesSectionId;
	}
	public void setDeliverablesSectionId(String deliverablesSectionId) {
		this.deliverablesSectionId = deliverablesSectionId;
	}
}

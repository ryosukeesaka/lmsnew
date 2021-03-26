package jp.co.sss.lms.dto;
import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.sss.lms.util.TrainingTime;


/**
 * 企業情報編集画面用DTOクラス
 * 
 * @author Shin
 */
@Component
public class SubsidyCompanyUpdateDto {
	
	/** 契約同意フラグ */
	private Short consentFlg;

	/** 企業ID */
	private Integer companyId;

	/** 企業名 */
	private String companyName;

	/** 企業名（カナ） **/
	private String companyNameKana;

	/** 郵便番号1 **/
	private String postNumber1;

	/** 郵便番号2 **/
	private String postNumber2;

	/** 労働局都道府県 **/
	private String prefecture;

	/** 企業所在地 **/
	private String address;

	/** 電話番号1 **/
	private String phoneNumber1;

	/** 電話番号2 **/
	private String phoneNumber2;

    /** 電話番号3 **/
	private String phoneNumber3;

	/** 代表者役職名 **/
	private String representativePost;

	/** 代表者氏名 **/
	private String representativeName;

	/** 資本額 **/
	private Integer capital;

	/** 社員数 **/
	private Integer workerAmount;

	/** 始業時間 **/
	private String workStartTime;

	/** 終業時間 **/
	private String workEndTime;
	
	/** 休憩開始時間 **/
	private String restStartTime;

    /** 休憩終了時間 **/
	private String restEndTime;

    /** 休日 **/
	private String holiday;

	/** 申請担当者番号1 **/
	private String subsidyPhoneNumber1;

	/** 申請担当者番号2 **/
	private String subsidyPhoneNumber2;

	/** 申請担当者番号3 **/
	private String subsidyPhoneNumber3;
	
	public Short getConsentFlg() {
		return consentFlg;
	}

	public void setConsentFlg(Short consentFlg) {
		this.consentFlg = consentFlg;
	}
	
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNameKana() {
		return companyNameKana;
	}

	public void setCompanyNameKana(String companyNameKana) {
		this.companyNameKana = companyNameKana;
	}

	public String getPostNumber1() {
		return postNumber1;
	}

	public void setPostNumber1(String postNumber1) {
		this.postNumber1 = postNumber1;
	}

	public String getPostNumber2() {
		return postNumber2;
	}

	public void setPostNumber2(String postNumber2) {
		this.postNumber2 = postNumber2;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getPhoneNumber3() {
		return phoneNumber3;
	}

	public void setPhoneNumber3(String phoneNumber3) {
		this.phoneNumber3 = phoneNumber3;
	}

	public String getRepresentativePost() {
		return representativePost;
	}

	public void setRepresentativePost(String representativePost) {
		this.representativePost = representativePost;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public Integer getCapital() {
		return capital;
	}

	public void setCapital(Integer capital) {
		this.capital = capital;
	}

	public Integer getWorkerAmount() {
		return workerAmount;
	}

	public void setWorkerAmount(Integer workerAmount) {
		this.workerAmount = workerAmount;
	}

	public String getWorkStartTime() {
		return workStartTime;
	}

	public void setWorkStartTime(String workStartTime) {
		this.workStartTime = workStartTime;
	}

	public String getWorkEndTime() {
		return workEndTime;
	}

	public void setWorkEndTime(String workEndTime) {
		this.workEndTime = workEndTime;
	}

	public String getRestStartTime() {
		return restStartTime;
	}

	public void setRestStartTime(String restStartTime) {
		this.restStartTime = restStartTime;
	}

	public String getRestEndTime() {
		return restEndTime;
	}

	public void setRestEndTime(String restEndTime) {
		this.restEndTime = restEndTime;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getSubsidyPhoneNumber1() {
		return subsidyPhoneNumber1;
	}

	public void setSubsidyPhoneNumber1(String subsidyPhoneNumber1) {
		this.subsidyPhoneNumber1 = subsidyPhoneNumber1;
	}

	public String getSubsidyPhoneNumber2() {
		return subsidyPhoneNumber2;
	}

	public void setSubsidyPhoneNumber2(String subsidyPhoneNumber2) {
		this.subsidyPhoneNumber2 = subsidyPhoneNumber2;
	}

	public String getSubsidyPhoneNumber3() {
		return subsidyPhoneNumber3;
	}

	public void setSubsidyPhoneNumber3(String subsidyPhoneNumber3) {
		this.subsidyPhoneNumber3 = subsidyPhoneNumber3;
	}
	
	/** CompanyCourseId **/
	private Integer companyCourseId;
	
	/** AgreementConsentFlg **/
	private Boolean agreementConsentFlg;

	public Integer getCompanyCourseId() {
		return companyCourseId;
	}

	public void setCompanyCourseId(Integer companyCourseId) {
		this.companyCourseId = companyCourseId;
	}

	public Boolean getAgreementConsentFlg() {
		return agreementConsentFlg;
	}

	public void setAgreementConsentFlg(Boolean agreementConsentFlg) {
		this.agreementConsentFlg = agreementConsentFlg;
	}


}



	
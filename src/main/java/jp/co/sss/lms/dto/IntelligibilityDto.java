package jp.co.sss.lms.dto;

import java.io.Serializable;

public class IntelligibilityDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//理解度ID
	private Integer intelligibilityId;
	//日報提出ID
	private Integer dailyReportSubmitId;
	//項目番号
	private Integer fieldNum;
	//項目名
	private String fieldName;
	//値
	private Short fieldValue;

	public Integer getIntelligibilityId() {
		return intelligibilityId;
	}

	public void setIntelligibilityId(Integer intelligibilityId) {
		this.intelligibilityId = intelligibilityId;
	}

	public Integer getDailyReportSubmitId() {
		return dailyReportSubmitId;
	}

	public void setDailyReportSubmitId(Integer dailyReportSubmitId) {
		this.dailyReportSubmitId = dailyReportSubmitId;
	}

	public Integer getFieldNum() {
		return fieldNum;
	}

	public void setFieldNum(Integer fieldNum) {
		this.fieldNum = fieldNum;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Short getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Short fieldValue) {
		this.fieldValue = fieldValue;
	}

}

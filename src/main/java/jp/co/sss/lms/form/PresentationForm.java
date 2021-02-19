package jp.co.sss.lms.form;

public class PresentationForm {

	/** 企業ID */
	private Integer companyId;

	/** 会場ID */
	private Integer placeId;

	/** ロール */
	private String role;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

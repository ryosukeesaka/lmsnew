package jp.co.sss.lms.dto;

import java.io.Serializable;

/**
 * 成果報告会対象会場DTO
 * 
 * @author Takayuki Nomoto
 */
public class PresentationPlaceDto implements Serializable {
	
	/** */
	private static final long serialVersionUID = -1727870799872014754L;

	/** 成果報告会対象会場ID */
	private Integer presentationPlaceId;

	/** チーム確定フラグ */
	private Short confirmFlg;

	/** 成果報告会スケジュールDTO */
	private PresentationScheduleDto presentationScheduleDto;

	/** 会場DTO */
	private PlaceDto placeDto;

	/** 予約状況 */
	private boolean isReserve;

	/** 企業名 */
	private String companyName;

	/** 公開フラグ */
	private Short publishedFlg;

	/** 企業住所 */
	private String companyAddress;

	public Integer getPresentationPlaceId() {
		return presentationPlaceId;
	}

	public void setPresentationPlaceId(Integer presentationPlaceId) {
		this.presentationPlaceId = presentationPlaceId;
	}

	public Short getConfirmFlg() {
		return confirmFlg;
	}

	public void setConfirmFlg(Short confirmFlg) {
		this.confirmFlg = confirmFlg;
	}

	public PresentationScheduleDto getPresentationScheduleDto() {
		return presentationScheduleDto;
	}

	public void setPresentationScheduleDto(PresentationScheduleDto presentationScheduleDto) {
		this.presentationScheduleDto = presentationScheduleDto;
	}

	public PlaceDto getPlaceDto() {
		return placeDto;
	}

	public void setPlaceDto(PlaceDto placeDto) {
		this.placeDto = placeDto;
	}

	public boolean isReserve() {
		return isReserve;
	}

	public void setReserve(boolean isReserve) {
		this.isReserve = isReserve;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Short getPublishedFlg() {
		return publishedFlg;
	}

	public void setPublishedFlg(Short publishedFlg) {
		this.publishedFlg = publishedFlg;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

}

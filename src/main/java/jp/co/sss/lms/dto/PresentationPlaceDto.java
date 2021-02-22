package jp.co.sss.lms.dto;

import java.io.Serializable;
//import java.util.List;

public class PresentationPlaceDto implements Serializable {
	
	/** */
	private static final long serialVersionUID = -1727870799872014754L;

	/** 成果報告会会場ID */
	private Integer presentationPlaceId;

	/** チーム確定フラグ */
	private Short confirmFlg;

	/** 成果報告会スケジュールDTO */
	private PresentationScheduleDto presentationScheduleDto;

	/** 会場DTO */
	private PlaceDto placeDto;

//	/** 対象受講生DTOリスト */
//	private List<LmsUserDto> studentLmsUserDtoList;
//
//	/** 成果報告会チームDTOリスト */
//	private List<PresentationTeamDto> presentationTeamDtoList;

//	/** コース名 */
//	private CourseDto courseDto;

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

//	public List<LmsUserDto> getStudentLmsUserDtoList() {
//		return studentLmsUserDtoList;
//	}
//
//	public void setStudentLmsUserDtoList(List<LmsUserDto> studentLmsUserDtoList) {
//		this.studentLmsUserDtoList = studentLmsUserDtoList;
//	}
//
//	public List<PresentationTeamDto> getPresentationTeamDtoList() {
//		return presentationTeamDtoList;
//	}
//
//	public void setPresentationTeamDtoList(List<PresentationTeamDto> presentationTeamDtoList) {
//		this.presentationTeamDtoList = presentationTeamDtoList;
//	}
//
//	public CourseDto getCourseDto() {
//		return courseDto;
//	}
//
//	public void setCourseDto(CourseDto courseDto) {
//		this.courseDto = courseDto;
//	}

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

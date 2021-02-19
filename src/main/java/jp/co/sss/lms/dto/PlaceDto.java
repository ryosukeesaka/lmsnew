package jp.co.sss.lms.dto;

import java.io.Serializable;
//import java.util.List;

public class PlaceDto implements Serializable {
	
	/** */
	private static final long serialVersionUID = 1L;

	/** 会場ID */
	private Integer placeId;

	/** 会場名 */
	private String placeName;

	/** 会場詳細 */
	private String placeDescription;

	/** サポートセンター表示 */
	private Short supportAvailable;

	/** 収容人数 */
	private Integer seatingCapacity;

	/** 備考 */
	private String placeNote;

//	/** 会場アサインリスト */
//	private List<AssignDto> assignDtoList;
//
//	/** 会場アサインリスト */
//	private List<CourseDto> courseDtoList;

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceDescription() {
		return placeDescription;
	}

	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}

	public Short getSupportAvailable() {
		return supportAvailable;
	}

	public void setSupportAvailable(Short supportAvailable) {
		this.supportAvailable = supportAvailable;
	}

	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getPlaceNote() {
		return placeNote;
	}

	public void setPlaceNote(String placeNote) {
		this.placeNote = placeNote;
	}

//	public List<AssignDto> getAssignDtoList() {
//		return assignDtoList;
//	}
//
//	public void setAssignDtoList(List<AssignDto> assignDtoList) {
//		this.assignDtoList = assignDtoList;
//	}
//
//	public List<CourseDto> getCourseDtoList() {
//		return courseDtoList;
//	}
//
//	public void setCourseDtoList(List<CourseDto> courseDtoList) {
//		this.courseDtoList = courseDtoList;
//	}

}

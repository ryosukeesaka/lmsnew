package jp.co.sss.lms.dto;

import java.util.List;

/**
 * 評価レポート採点一覧画面の検索条件表示のためのDTO
 * 
 * @author 東　茉奈
 *
 */
public class EvReportFilteringDto {
	
	/** 会場DTO（講師権限の場合） */
	private PlaceDto placeDto;
	
	/** 会場DToリスト（管理者権限の場合） */
	private List<PlaceDto> placeDtoList;
	
	/** 評価レポートDTOリスト */
	private List<EvReportDto> evReportDtoList;

	public PlaceDto getPlaceDto() {
		return placeDto;
	}

	public void setPlaceDto(PlaceDto placeDto) {
		this.placeDto = placeDto;
	}

	public List<PlaceDto> getPlaceDtoList() {
		return placeDtoList;
	}

	public void setPlaceDtoList(List<PlaceDto> placeDtoList) {
		this.placeDtoList = placeDtoList;
	}

	public List<EvReportDto> getEvReportDtoList() {
		return evReportDtoList;
	}

	public void setEvReportDtoList(List<EvReportDto> evReportDtoList) {
		this.evReportDtoList = evReportDtoList;
	}
	
}

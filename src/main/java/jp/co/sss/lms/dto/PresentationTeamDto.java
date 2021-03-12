package jp.co.sss.lms.dto;

import java.io.Serializable;
import java.util.List;


public class PresentationTeamDto implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/** 成果報告会チームID */
	public Integer presentationTeamId;
	/** 成果報告会チーム名 */
	public String presentationTeamName;
	/** 成果報告会スケジュール詳細DTO */
	public PresentationScheduleDetailDto presentationScheduleDetailDto;
	/** ユーザーDTO（チームメンバー） */
	public List<LmsUserDto> lmsUserDtoList;

	public Integer presentationPlaceId;

	public Integer presentationScheduleDetailId;

	public PresentationPlaceDto presentationPlaceDto;

	public List<PresentationCompanyDto> presentationCompanyDtoList;

	/**
	 * 成果報告会チームIDを取得します。
	 * @return 成果報告会チームID
	 */
	public Integer getPresentationTeamId() {
	    return presentationTeamId;
	}

	/**
	 * 成果報告会チーム名を取得します。
	 * @return 成果報告会チーム名
	 */
	public String getPresentationTeamName() {
	    return presentationTeamName;
	}

	/**
	 * 成果報告会スケジュール詳細DTOを取得します。
	 * @return 成果報告会スケジュール詳細DTO
	 */
	public PresentationScheduleDetailDto getPresentationScheduleDetailDto() {
	    return presentationScheduleDetailDto;
	}

	/**
	 * ユーザーDTO（チームメンバー）を取得します。
	 * @return ユーザーDTO（チームメンバー）
	 */
	public List<LmsUserDto> getLmsUserDtoList() {
	    return lmsUserDtoList;
	}

	/**
	 * presentationPlaceIdを取得します。
	 * @return presentationPlaceId
	 */
	public Integer getPresentationPlaceId() {
	    return presentationPlaceId;
	}

	/**
	 * presentationScheduleDetailIdを取得します。
	 * @return presentationScheduleDetailId
	 */
	public Integer getPresentationScheduleDetailId() {
	    return presentationScheduleDetailId;
	}

	/**
	 * presentationPlaceDtoを取得します。
	 * @return presentationPlaceDto
	 */
	public PresentationPlaceDto getPresentationPlaceDto() {
	    return presentationPlaceDto;
	}

	/**
	 * presentationCompanyDtoListを取得します。
	 * @return presentationCompanyDtoList
	 */
	public List<PresentationCompanyDto> getPresentationCompanyDtoList() {
	    return presentationCompanyDtoList;
	}

}

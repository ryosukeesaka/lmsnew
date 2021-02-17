package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.co.sss.lms.dto.MeetingScheduleDto;
import jp.co.sss.lms.entity.TMeetingPlace;
import jp.co.sss.lms.repository.TScheduleMeetingReserveRepository;

/**
 * クラス名 ScheduleMeetingReserveService
 * 
 * クラス概要 面談スケジュール予約サービス
 * 
 * @author 江坂 亮典
 */

@Service
public class ScheduleMeetingReserveService {

	@Autowired
	TScheduleMeetingReserveRepository scheduleMeetingReserveRepository;

	/**
	 * 関数概要 会場情報の取得
	 * 
	 * @param placeId 会場ID
	 * @return meetingScheduleDtoList 面談・会場情報DTOリスト
	 */
	public List<MeetingScheduleDto> getMeetingPlaceListByPlaceId(Integer placeId){

		// MeetingScheduleDtoListの作成
		List<MeetingScheduleDto> meetingScheduleDtoList = new ArrayList<MeetingScheduleDto>();

		// リポジトリで検索結果を取得
		List<TMeetingPlace> tMeetingPlaceList = scheduleMeetingReserveRepository.findByPlaceId(placeId);

		for (TMeetingPlace tMeetingPlace : tMeetingPlaceList) {

			// MeetingScheduleDtoの作成
			MeetingScheduleDto meetingScheduleDto = new MeetingScheduleDto();

			// MMeetingScheduleEntityとMPlaceをMeetingScheduleDtoに詰め替える
			meetingScheduleDto.setEditLimit(tMeetingPlace.getmMeetingSchedule().getEditLimit());
			meetingScheduleDto.setMeetingOpenDate(tMeetingPlace.getmMeetingSchedule() .getMeetingOpenDate());
			meetingScheduleDto.setMeetingCloseDate(tMeetingPlace.getmMeetingSchedule().getMeetingCloseDate());
			meetingScheduleDto.setPurpose(tMeetingPlace.getmMeetingSchedule().getPurpose());
			meetingScheduleDto.setPlaceName(tMeetingPlace.getmPlace().getPlaceName());
			meetingScheduleDto.setMeetingPlaceId(tMeetingPlace.getmPlace().getPlaceId());
			
			meetingScheduleDto.setEditLimit(tMeetingPlace.getmMeetingSchedule().getEditLimit());
			
			meetingScheduleDtoList.add(meetingScheduleDto);
		}
		return meetingScheduleDtoList;	
	}
}

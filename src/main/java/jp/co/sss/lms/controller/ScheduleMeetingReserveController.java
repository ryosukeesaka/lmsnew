package jp.co.sss.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jp.co.sss.lms.dto.MeetingScheduleDto;
import jp.co.sss.lms.service.ScheduleMeetingReserveService;
import jp.co.sss.lms.util.Constants;

/**
 * ScheduleMeetingReserveController
 * 
 *@author 江坂 亮典
 */

@RestController
@RequestMapping("/schedule")
public class ScheduleMeetingReserveController {

	@Autowired
	ScheduleMeetingReserveService scheduleMeetingReserveService;

	/**
	 * 関数概要 会場情報の取得
	 * 
	 * @param role 権限、placeId 会場ID
	 * @return 面談・会場情報DTO
	 */
	@GetMapping("/meeting/reserve/list")
	public ResponseEntity<List<MeetingScheduleDto>> detail(@RequestParam("role") String role,@RequestParam("placeId") Integer placeId) {

		// 面談・会場情報DTOの作成
		List<MeetingScheduleDto> meetingScheduleDto = new ArrayList<MeetingScheduleDto>();
		HttpStatus httpStatus = HttpStatus.OK;

		//ログインユーザーが講師権限だった場合	
		if(Constants.CODE_VAL_ROLL_TEACHER.equals(role)) {

			//面談会場リストの取得
			meetingScheduleDto = scheduleMeetingReserveService.getMeetingPlaceListByPlaceId(placeId);
		}
		return new ResponseEntity<List<MeetingScheduleDto>>(meetingScheduleDto, httpStatus);
	}
}

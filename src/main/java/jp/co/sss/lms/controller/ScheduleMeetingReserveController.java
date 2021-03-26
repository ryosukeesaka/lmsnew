package jp.co.sss.lms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.form.ScheduleMeetingReserveRegistForm;
import jp.co.sss.lms.dto.AmountInformationDto;
import jp.co.sss.lms.dto.MeetingPlaceDto;
import jp.co.sss.lms.dto.MeetingScheduleDto;
import jp.co.sss.lms.service.ScheduleMeetingReserveService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.MessageUtil;

/**
 * ScheduleMeetingReserveController
 * 
 * @author 江坂 亮典
 */

@RestController
@RequestMapping("/schedule")
public class ScheduleMeetingReserveController {

	@Autowired
	ScheduleMeetingReserveService scheduleMeetingReserveService;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	MessageSource messagesource;

	/**
	 * 関数概要 会場情報の取得
	 * 
	 * @param role 権限、placeId 会場ID
	 * @return 面談・会場情報DTO
	 */

	@GetMapping("/meeting/reserve/list")
	public ResponseEntity<List<MeetingScheduleDto>> detail(@RequestParam("role") String role,
			@RequestParam("placeId") Integer placeId) {

		// 面談・会場情報DTOの作成
		List<MeetingScheduleDto> meetingScheduleDto = new ArrayList<MeetingScheduleDto>();
		HttpStatus httpStatus = HttpStatus.OK;

		if (Constants.CODE_VAL_ROLL_COMPANY.equals(role)) {
			meetingScheduleDto = scheduleMeetingReserveService.getMeetingPlaceListByPlaceId(placeId);

		}

		// ログインユーザーが講師権限だった場合
		else if (Constants.CODE_VAL_ROLL_TEACHER.equals(role)) {

			// 面談会場リストの取得
			meetingScheduleDto = scheduleMeetingReserveService.getMeetingPlaceListByPlaceId(placeId);
		}
		return new ResponseEntity<List<MeetingScheduleDto>>(meetingScheduleDto, httpStatus);
	}
	
	//詳細ページ用
	@RequestMapping(path = "/meeting/reserve/detail", method = RequestMethod.POST)

	public ResponseEntity<Map<String, Object>> detail(@RequestParam("meetingPlaceId") String meetingPlaceId) {

		// MeetingPlaceDtoインスタンス作成
		MeetingPlaceDto meetingPlaceDto = new MeetingPlaceDto();

		// 面談登録画面で必要なデータをmeetingPlaceDtoに格納
		meetingPlaceDto = scheduleMeetingReserveService.getMeetingPlaceDtoByMeetingPlaceId(meetingPlaceId);

		// List型のAmountInformationDtoインスタンスを作成
		List<AmountInformationDto> amountInformationDtoList = new ArrayList<AmountInformationDto>();
		amountInformationDtoList = scheduleMeetingReserveService.getAmountInformationByMeetingPlaceId(meetingPlaceId);

		Map<String, Object> map = new HashMap<>();
		map.put("meetingPlaceDto", meetingPlaceDto);
		map.put("amountInformationDtoList", amountInformationDtoList);

		return new ResponseEntity<>(map, HttpStatus.OK);

	}
	
	//面談予約登録画面用コントローラー
	@GetMapping("/meeting/reserve/regist")
	public ResponseEntity<Map<String, Object>> regist(@RequestParam("meetingPlaceId") String meetingPlaceId) {

		// MeetingPlaceDtoインスタンス作成
		MeetingPlaceDto meetingPlaceDto = new MeetingPlaceDto();

		// 面談登録画面で必要なデータをmeetingPlaceIdで検索し、面談会場の情報を取得、meetingPlaceDtoに格納
		meetingPlaceDto = scheduleMeetingReserveService.getMeetingPlaceDtoByMeetingPlaceId(meetingPlaceId);

		// List型のAmountInformationDtoインスタンスを作成(それぞれの時間帯の人数や企業要望を複数取得するのでListで取得)
		List<AmountInformationDto> amountInformationDtoList = new ArrayList<AmountInformationDto>();
		
		//日時一覧以降に表示するデータを取得amountInformationDtoListに格納
		amountInformationDtoList = scheduleMeetingReserveService.getAmountInformationByMeetingPlaceId(meetingPlaceId);
		
		//MeetingPlaceDtoとamountInformationDtoListの2つを返すためmapに2にdtoを格納
		Map<String, Object> map = new HashMap<>();
		map.put("meetingPlaceDto", meetingPlaceDto);
		map.put("amountInformationDtoList", amountInformationDtoList);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	//登録画面で予約するボタンを押下後の処理
	@RequestMapping(path = "/meeting/reserve/regist/complete", method = RequestMethod.POST)
	
	
	public ResponseEntity<String> complete(@RequestBody ScheduleMeetingReserveRegistForm form,
			@RequestParam("role") String role, @RequestParam("companyId") String companyId) {
		
		//企業権限の場合に中の処理を実行
		if (Constants.CODE_VAL_ROLL_COMPANY.equals(role)) {
			scheduleMeetingReserveService.reserveByCompany(form, companyId);

		}
		//登録完了後、完了のメッセージをフロントで表示するため、メッセージをmessages.propertiesから取得
		String message1 = messagesource.getMessage("schedule.meeting.reserve", null, null);
		
		//
		String[] message = { message1 };
		// message.propertiesから取得したメッセージとPROP_KEY_REGIST_COMPLETEのメッセージを組み合わせStringで返す
		String registCompleteMessage = new StringBuffer(
				messageUtil.getMessage(Constants.PROP_KEY_REGIST_COMPLETE, message)).toString();

		return new ResponseEntity<>(registCompleteMessage, HttpStatus.OK);

	}

}

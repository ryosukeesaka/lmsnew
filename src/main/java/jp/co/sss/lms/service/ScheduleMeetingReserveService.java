package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.AmountInformationDto;
import jp.co.sss.lms.dto.MeetingPlaceDto;
import jp.co.sss.lms.dto.MeetingScheduleDto;
import jp.co.sss.lms.entity.MMeetingScheduleDetail;
import jp.co.sss.lms.entity.TMeetingCompany;
import jp.co.sss.lms.entity.TMeetingPlace;
import jp.co.sss.lms.form.ScheduleMeetingReserveRegistForm;
import jp.co.sss.lms.repository.TScheduleMeetingReserveRepository;

import jp.co.sss.lms.repository.TMeetingCompanyRepository;

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

	@Autowired
	TMeetingCompanyRepository tMeetingCompanyRepository;

	/**
	 * 関数概要 会場情報の取得
	 *
	 * @param placeId 会場ID
	 * @return meetingScheduleDtoList 面談・会場情報DTOリスト
	 */
	public List<MeetingScheduleDto> getMeetingPlaceListByPlaceId(Integer placeId) {

		// MeetingScheduleDtoListの作成
		List<MeetingScheduleDto> meetingScheduleDtoList = new ArrayList<MeetingScheduleDto>();

		// リポジトリで検索結果を取得
		List<TMeetingPlace> tMeetingPlaceList = scheduleMeetingReserveRepository.findByPlaceId(placeId);

		for (TMeetingPlace tMeetingPlace : tMeetingPlaceList) {

			// MeetingScheduleDtoの作成
			MeetingScheduleDto meetingScheduleDto = new MeetingScheduleDto();

			// MMeetingScheduleEntityとMPlaceをMeetingScheduleDtoに詰め替える
			meetingScheduleDto.setEditLimit(tMeetingPlace.getmMeetingSchedule().getEditLimit());
			meetingScheduleDto.setMeetingOpenDate(tMeetingPlace.getmMeetingSchedule().getMeetingOpenDate());
			meetingScheduleDto.setMeetingCloseDate(tMeetingPlace.getmMeetingSchedule().getMeetingCloseDate());
			meetingScheduleDto.setPurpose(tMeetingPlace.getmMeetingSchedule().getPurpose());
			meetingScheduleDto.setPlaceName(tMeetingPlace.getmPlace().getPlaceName());
			meetingScheduleDto.setMeetingPlaceId(tMeetingPlace.getMeetingPlaceId());

			meetingScheduleDto.setEditLimit(tMeetingPlace.getmMeetingSchedule().getEditLimit());

			meetingScheduleDtoList.add(meetingScheduleDto);
		}
		return meetingScheduleDtoList;
	}

	/**
	 * メソッド名 getAmountInformationByMeetingPlaceId * メソッド概要 登録画面表示に必要なデータの取得
	 *
	 * @author 久保修平
	 */
	public List<AmountInformationDto> getAmountInformationByMeetingPlaceId(String meetingPlaceId) {

		List<AmountInformationDto> amountInformationDtoList = new ArrayList<AmountInformationDto>();

		// meetingaplaceIdでデータ取得
		TMeetingPlace tMeetingPlace = scheduleMeetingReserveRepository
				.findAllBymeetingPlaceId(Integer.parseInt(meetingPlaceId));

		// amountInformationDtoの中にmeetingScheduleDetailテーブルの情報を格納
		for (MMeetingScheduleDetail mMeetingScheduleDetail : tMeetingPlace.getmMeetingSchedule()
				.getmMeetingScheduleDetailList()) {

			AmountInformationDto amountInformationDto = new AmountInformationDto();

			amountInformationDto.meetingScheduleDetailId = mMeetingScheduleDetail.getMeetingScheduleDetailId();
			amountInformationDto.meetingDate = mMeetingScheduleDetail.getMeetingDate();
			amountInformationDto.meetingTime = mMeetingScheduleDetail.getMeetingTime();

			// 予約データがある場合はこの処理が動く？
			for (TMeetingCompany tMeetingCompany : mMeetingScheduleDetail.gettMeetingCompanyList()) {

				if (tMeetingCompany.gettMeetingPlace().getMeetingPlaceId() == Integer.parseInt(meetingPlaceId)) {
					amountInformationDto.meetingCompanyId = tMeetingCompany.getMeetingCompanyId();
					amountInformationDto.companyId = tMeetingCompany.getmCompany().getCompanyId();
					amountInformationDto.companyName = tMeetingCompany.getmCompany().getCompanyName();
					amountInformationDto.joinAmount = tMeetingCompany.getJoinAmount();
					amountInformationDto.companyRequest = tMeetingCompany.getCompanyRequest();
					amountInformationDto.companyAddress = tMeetingCompany.getmCompany().getAddress();

				}
			}

			amountInformationDtoList.add(amountInformationDto);
		}

		return amountInformationDtoList;
	}

	public MeetingPlaceDto getMeetingPlaceDtoByMeetingPlaceId(String meetingPlaceId) {

		// meetingaplaceIdでデータ取得
		TMeetingPlace tMeetingPlace = scheduleMeetingReserveRepository
				.findAllBymeetingPlaceId(Integer.parseInt(meetingPlaceId));

		// DTOインスタンスを作成し、必要な値をSET
		MeetingPlaceDto meetingPlaceDto = new MeetingPlaceDto();

		meetingPlaceDto.setEditLimit(tMeetingPlace.getmMeetingSchedule().getEditLimit());
		meetingPlaceDto.setMeetingOpenDate(tMeetingPlace.getmMeetingSchedule().getMeetingOpenDate());
		meetingPlaceDto.setMeetingCloseDate(tMeetingPlace.getmMeetingSchedule().getMeetingCloseDate());
		meetingPlaceDto.setPurpose(tMeetingPlace.getmMeetingSchedule().getPurpose());
		meetingPlaceDto.setPlaceName(tMeetingPlace.getmPlace().getPlaceName());
		meetingPlaceDto.setPlaceDescription(tMeetingPlace.getmPlace().getPlaceDescription());
		meetingPlaceDto.setPlaceNote(tMeetingPlace.getMeetingPlaceNote());

		return meetingPlaceDto;
	}

	public void reserveByCompany(ScheduleMeetingReserveRegistForm scheduleMeetingReserveRegistForm, String companyId) {

		int reserveIndex = Integer.parseInt(scheduleMeetingReserveRegistForm.getReserveIndex());

		// ◆面談対象企業登録
		TMeetingCompany tMeetingCompany = tMeetingCompanyRepository.findByCompanyIdAndMeetingPlaceId(
				Integer.parseInt(companyId), Integer.parseInt(scheduleMeetingReserveRegistForm.getMeetingPlaceId()));

		// 参加人数
		tMeetingCompany.setJoinAmount(Integer.parseInt(scheduleMeetingReserveRegistForm.getJoinAmount()[reserveIndex]));

		// 面談詳細のID
		tMeetingCompany.getmMeetingScheduleDetail().setMeetingScheduleDetailId(
				Integer.parseInt(scheduleMeetingReserveRegistForm.getMeetingScheduleDetailId()[reserveIndex]));

		// 企業の要望
		tMeetingCompany.setCompanyRequest(scheduleMeetingReserveRegistForm.getCompanyRequest()[reserveIndex]);

		// tMeetingにセットした値を登録
		tMeetingCompanyRepository.save(tMeetingCompany);

		// meetingPlaceIdで取得した情報を格納
		TMeetingPlace tMeetingPlace = scheduleMeetingReserveRepository
				.findAllBymeetingPlaceId(Integer.parseInt(scheduleMeetingReserveRegistForm.getMeetingPlaceId()));

		// PlaceNoteを更新？
		tMeetingPlace.setMeetingPlaceNote(scheduleMeetingReserveRegistForm.getMeetingPlaceNote());

		scheduleMeetingReserveRepository.save(tMeetingPlace);

	}
}

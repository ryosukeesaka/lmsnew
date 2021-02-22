package jp.co.sss.lms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.PlaceDto;
import jp.co.sss.lms.dto.PresentationPlaceDto;
import jp.co.sss.lms.dto.PresentationScheduleDto;
import jp.co.sss.lms.entity.MPresentationSchedule;
import jp.co.sss.lms.entity.MPresentationTeam;
import jp.co.sss.lms.entity.TPresentationCompany;
import jp.co.sss.lms.entity.TPresentationPlace;
import jp.co.sss.lms.form.PresentationForm;
import jp.co.sss.lms.repository.TPresentationPlaceRepository;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.DateUtil;

@Service
public class SchedulePresentationService {

	@Autowired
	DateUtil dateUtil;

	@Autowired
	TPresentationPlaceRepository tPresentationPlaceRepository;

	/**
	 * 成果報告会情報のDTO作成
	 * 
	 * @param tPresentationPlace 成果報告会対象会場ENTITY
	 * @return PresentationPlaceDto 成果報告会情報DTO
	 */
	private PresentationPlaceDto createPresentationPlaceDto(TPresentationPlace tPresentationPlace) {
		PresentationPlaceDto presentationPlaceDto = new PresentationPlaceDto();
		BeanUtils.copyProperties(tPresentationPlace, presentationPlaceDto);

		// ◆会場
		if (tPresentationPlace.getMPlace() != null) {
			PlaceDto placeDto = new PlaceDto();
			BeanUtils.copyProperties(tPresentationPlace.getMPlace(), placeDto);
			presentationPlaceDto.setPlaceDto(placeDto);
		}

		// ◆成果報告会スケジュール
		if (tPresentationPlace.getMPresentationSchedule() != null) {
			MPresentationSchedule mPresentationSchedule = tPresentationPlace.getMPresentationSchedule();
			PresentationScheduleDto presentationScheduleDto = new PresentationScheduleDto();
			BeanUtils.copyProperties(mPresentationSchedule, presentationScheduleDto);

			Timestamp editLimit = new Timestamp(mPresentationSchedule.getEditLimit().getTime());
			Date now = dateUtil.getDateWithoutTime(new Date());
			presentationScheduleDto.setEditLimitPast((now.getTime() - editLimit.getTime()) > 0);
			presentationPlaceDto.setPresentationScheduleDto(presentationScheduleDto);

			// ◆成果報告会スケジュール詳細 ★未編集
//			if (mPresentationSchedule.mPresentationScheduleDetailList != null && !mPresentationSchedule.mPresentationScheduleDetailList.isEmpty()) {
//				presentationPlaceDto.presentationScheduleDto.presentationScheduleDetailDtoList = new ArrayList<>();
//				for (MPresentationScheduleDetail mPresentationScheduleDetail : mPresentationSchedule.mPresentationScheduleDetailList) {
//					PresentationScheduleDetailDto presentationScheduleDetailDto = Beans.createAndCopy(PresentationScheduleDetailDto.class, mPresentationScheduleDetail).execute();
//					presentationPlaceDto.presentationScheduleDto.presentationScheduleDetailDtoList.add(presentationScheduleDetailDto);
//				}
//			}

//			// ◆受講中の受講生 ★未編集
//			List<MLmsUser> mLmsUserList = mLmsUserService.findStudentByPlaceId(tPresentationPlace.placeId, mPresentationSchedule.presentationDate);
//			presentationPlaceDto.studentLmsUserDtoList = new ArrayList<>();
//			for (MLmsUser mLmsUser : mLmsUserList) {
//				LmsUserDto lmsUserDto = Beans.createAndCopy(LmsUserDto.class, mLmsUser).execute();
//				Beans.copy(mLmsUser.mUser, mLmsUser);
//				presentationPlaceDto.studentLmsUserDtoList.add(lmsUserDto);
//			}
		}
		return presentationPlaceDto;
	}

	/**
	 * 成果報告会情報取得
	 * 
	 * @param presentationForm 成果報告会一覧FORM
	 * @return List<PresentationPlaceDto> 成果報告会情報DTOリスト
	 */
	public List<PresentationPlaceDto> getPresentationPlaceDtoList(PresentationForm presentationForm) {

		List<PresentationPlaceDto> presentationPlaceDtoList = new ArrayList<>();
		PresentationPlaceDto presentationPlaceDto = new PresentationPlaceDto();

		// 現在の日時を取得
		Date date = dateUtil.getDateWithoutTime(new Date());
		Timestamp time = new Timestamp(date.getTime());
		List<TPresentationPlace> tPresentationPlaceList = null;

		// 企業担当者・育成担当者は自社の成果報告会情報のみ確認可能
		if (Constants.CODE_VAL_ROLL_COMPANY.equals(presentationForm.getRole()) ||
			Constants.CODE_VAL_ROLL_TRAINING.equals(presentationForm.getRole())) {
			tPresentationPlaceList = tPresentationPlaceRepository.findByCompanyIdAndPresentationDate(presentationForm.getCompanyId(), time);

		// 講師の場合は担当会場のスケジュールのみ参照可能
		} else if (Constants.CODE_VAL_ROLL_TEACHER.equals(presentationForm.getRole())) {
			tPresentationPlaceList = tPresentationPlaceRepository.findByPlaceIdAndPresentationDate(presentationForm.getPlaceId(), time);

		// その他
		} else {
			tPresentationPlaceList = tPresentationPlaceRepository.findByPresentationDate(time);
		}

		for(TPresentationPlace tPresentationPlace : tPresentationPlaceList) {
			if (Constants.CODE_VAL_ROLL_ADMIN.equals(presentationForm.getRole()) ||
				(tPresentationPlace.getPublishedFlg() != null && tPresentationPlace.getPublishedFlg() == Constants.PUBLISHED_FLG_TRUE)){
				presentationPlaceDto = createPresentationPlaceDto(tPresentationPlace);

				//予約状況
				for(MPresentationTeam mPresentationTeam : tPresentationPlace.getMPresentationTeamList()) {
					for(TPresentationCompany tPresentationCompany : mPresentationTeam.getTPresentationCompanyList()) {
						boolean isReserve = tPresentationCompany.getJoinAbleFlg() != null;
						presentationPlaceDto.setReserve(isReserve);
					}
				}

				presentationPlaceDtoList.add(presentationPlaceDto);
			}
		}

		return presentationPlaceDtoList;
	}


}

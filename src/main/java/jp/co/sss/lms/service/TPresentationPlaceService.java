package jp.co.sss.lms.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.CompanyDto;
import jp.co.sss.lms.dto.LmsUserDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.dto.PlaceDto;
import jp.co.sss.lms.dto.PresentationPlaceDto;
import jp.co.sss.lms.dto.PresentationScheduleDetailDto;
import jp.co.sss.lms.dto.PresentationScheduleDto;
import jp.co.sss.lms.dto.PresentationTeamDto;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.MPresentationSchedule;
import jp.co.sss.lms.entity.MPresentationScheduleDetail;
import jp.co.sss.lms.entity.MPresentationTeam;
import jp.co.sss.lms.entity.TPresentationCompany;
import jp.co.sss.lms.entity.TPresentationPlace;
import jp.co.sss.lms.entity.TUserPresentationTeam;
import jp.co.sss.lms.repository.TPresentationPlaceRepository;
import jp.co.sss.lms.repository.MLmsUserRepository;
import jp.co.sss.lms.util.DateUtil;
/**
 * チーム編成一覧サービス
 * 
 * @author Yamaki　Kakeru
 */
@Service
public class TPresentationPlaceService {
	//@Autowired
	//private TPresentationPlace tPresentationPlace;
	@Autowired
	private TPresentationPlaceRepository tPresentationPlaceRipository;
	//@Autowired
	//private PlaceDto placeDto;
	@Autowired
	private MLmsUserRepository mLmsUserRepository;
	
	public List<PresentationPlaceDto> getPresentationPlaceDtoList(Integer loginPlaceId) {
        List<PresentationPlaceDto> presentationPlaceDtoList = new ArrayList<>();
        //日時取得
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        System.out.println(strDate);
        String[] parsePatterns = {"yyyy-MM-dd hh:mm:ss"};
        Date parseDate = DateUtils.parseDate(strDate, parsePatterns); 	//12時間遅れた時間がnowに入っている
																		//例:2021-02-26 16:00:00なら2021-02-26 04:00:00がnowに入る
        Timestamp now = new Timestamp(parseDate.getTime());	//3時間遅れた時間がnowに入っている
        													//例:2021-02-26 16:00:00なら2021-02-26 13:00:00がnowに入る
        
        List<TPresentationPlace> tPresentationPlaceList = tPresentationPlaceRipository	//tPresentationPlaceListに値が入ってない
                .findByPlaceIdAndPresentationDate(loginPlaceId, now);					//DBに入れてるデータが足りない？

        for (TPresentationPlace tPresentationPlace : tPresentationPlaceList) {
            presentationPlaceDtoList.add(this.entity2Dto(tPresentationPlace));
        }
        return presentationPlaceDtoList;
    }
	
	
	private PresentationPlaceDto entity2Dto(
            TPresentationPlace tPresentationPlace) {
        
		//日時取得
		Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        Date now = DateUtils.parseDate(strDate);
        
        //PresentationPlaceDto presentationPlaceDto = BeanUtils.copyProperties(tPresentationPlace
        //        PresentationPlaceDto.class);

        //合ってるかわからない
        PresentationPlaceDto presentationPlaceDto = new PresentationPlaceDto();
        BeanUtils.copyProperties(tPresentationPlace, PresentationPlaceDto.class);
        //Class<PresentationPlaceDto> presentationPlaceDto = PresentationPlaceDto.class;
        
        // ◆会場
        if (tPresentationPlace.getMPlace() != null) {
        	PlaceDto placeDto = new PlaceDto();
            BeanUtils.copyProperties(tPresentationPlace.getMPlace(), PlaceDto.class);
            //合ってるかわからない
            presentationPlaceDto.setPlaceDto(placeDto);
        }

        // ◆成果報告会スケジュール
        if (tPresentationPlace.mPresentationSchedule != null) {
            MPresentationSchedule mPresentationSchedule = tPresentationPlace.mPresentationSchedule;
            BeanUtils.copyProperties(mPresentationSchedule, PresentationScheduleDto.class);

            Date editLimit = mPresentationSchedule.editLimit;//Date型で返ってくる
            presentationPlaceDto.presentationScheduleDto.editLimitPast = (now
                    .getTime() > editLimit.getTime());

            // ◆成果報告会スケジュール詳細
            if (mPresentationSchedule.mPresentationScheduleDetailList != null
                    && !mPresentationSchedule.mPresentationScheduleDetailList
                            .isEmpty()) {
                presentationPlaceDto.presentationScheduleDto.presentationScheduleDetailDtoList = new ArrayList<>();
                for (MPresentationScheduleDetail mPresentationScheduleDetail : mPresentationSchedule.mPresentationScheduleDetailList) {
                	//合ってるかわからない
                	PresentationScheduleDetailDto presentationScheduleDetailDto = new PresentationScheduleDetailDto();
                	BeanUtils.copyProperties(mPresentationScheduleDetail, PresentationScheduleDetailDto.class);
                    presentationPlaceDto.presentationScheduleDto.presentationScheduleDetailDtoList.add(presentationScheduleDetailDto);
                }
            }

            // ◆受講中の受講生
            List<MLmsUser> mLmsUserList = mLmsUserRepository.findStudentByPlaceId(
                    tPresentationPlace.getMPlace().getPlaceId(),
                    mPresentationSchedule.presentationDate);
            presentationPlaceDto.studentLmsUserDtoList = new ArrayList<>();
            
            for (MLmsUser mLmsUser : mLmsUserList) {
            	LmsUserDto lmsUserDto = new LmsUserDto();
            	//合ってるかわからない
                BeanUtils.copyProperties(mLmsUser, LmsUserDto.class);
                //copy->copyProperties
                BeanUtils.copyProperties(mLmsUser.mUser, lmsUserDto);
                BeanUtils.copyProperties(mLmsUser.tUserCompany.mCompany, CompanyDto.class);
                presentationPlaceDto.studentLmsUserDtoList.add(lmsUserDto);
            }
        }

        // ◆成果報告会チーム
        if (tPresentationPlace.mPresentationTeamList != null
                && !tPresentationPlace.mPresentationTeamList.isEmpty()) {
            presentationPlaceDto.presentationTeamDtoList = new ArrayList<>();
            PresentationTeamDto presentationTeamDto = new PresentationTeamDto();
            for (MPresentationTeam mPresentationTeam : tPresentationPlace.mPresentationTeamList) {
            	//合ってるかわからない
                BeanUtils.copyProperties(mPresentationTeam, PresentationTeamDto.class);

                if (mPresentationTeam.tPresentationCompanyList != null) {
                    for (TPresentationCompany tPresentationCompany : mPresentationTeam.tPresentationCompanyList) {
                        if (tPresentationCompany.joinAbleFlg != null) {
                            presentationPlaceDto.isReserve = true;
                        }
                    }
                }

                // ◆チームメンバー
                presentationTeamDto.lmsUserDtoList = new ArrayList<>();
                if (mPresentationTeam.tUserPresentationTeamList != null) {
                    for (TUserPresentationTeam tUserPresentationTeam : mPresentationTeam.tUserPresentationTeamList) {
                        if (tUserPresentationTeam.mLmsUser != null) {
                            if (tUserPresentationTeam.mLmsUser.deleteFlg == 0) {
                            	//合ってるかわからない
                            	LmsUserDto lmsUserDto = new LmsUserDto();
                                BeanUtils.copyProperties(tUserPresentationTeam.mLmsUser, LmsUserDto.class);
                                //copy->copyProperties
                                BeanUtils.copyProperties(tUserPresentationTeam.mLmsUser.mUser, lmsUserDto);
                                //合ってるかわからない
                                CompanyDto companyDto = new CompanyDto();
                                BeanUtils.copyProperties(tUserPresentationTeam.mLmsUser.tUserCompany.mCompany, CompanyDto.class);
                                lmsUserDto.companyDto = companyDto;
                                presentationTeamDto.lmsUserDtoList.add(lmsUserDto);
                            }
                        }
                    }

                    presentationPlaceDto.presentationTeamDtoList
                            .add(presentationTeamDto);
                }
            }

        }
        return presentationPlaceDto;
    }
}
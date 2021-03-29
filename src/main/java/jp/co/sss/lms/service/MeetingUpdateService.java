package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.repository.MFssUserRepository;
//import jp.co.sss.lms.repository.TMeetingRepository;
import jp.co.sss.lms.dto.MeetingDto;
import jp.co.sss.lms.entity.MFssUser;
import jp.co.sss.lms.entity.TFssFile;
import jp.co.sss.lms.entity.TMeeting;
import jp.co.sss.lms.entity.TMeetingDetail;
import jp.co.sss.lms.form.MeetingForm;

@Service
public class MeetingUpdateService {
	
	@Autowired
	MFssUserRepository mFssUserRepository;
	
//	@Autowired
//	TMeetingRepository tMeetingRepository;
	
	/**
	 * ユーザーIDから共有ユーザーIDを取得し、セッションに設定する
	 * 
	 * @param UserId
	 * @return Integer
	 */
	public Integer getFssUserId(Integer UserId) {
		MFssUser mFssUser = (MFssUser) mFssUserRepository.findByUserId(UserId);
		return (Integer) mFssUser.getFssUserId();
	}

	/**
	 * 面談記録フォーム呼び出し
	 */
//	public static TMeeting setValForm(MeetingForm form) {
//		
////		TMeeting tMeeting = TMeetingRepository.getMeetingById(Integer.parseInt(form.getMeetingId()));
////		
////        if (tMeeting.tMeetingDetailList != null) {
////            for (int i = 0; i < tMeeting.tMeetingDetailList.size(); i++) {
////                TMeetingDetail tMeetingDetail = tMeeting.tMeetingDetailList.get(i);
////                form.setQuestion[i]     = tMeetingDetail.question;
////                form.answer[i]       = tMeetingDetail.answer;
////                form.follow[i]       = tMeetingDetail.follow;
////                form.questionType[i] = tMeetingDetail.questionType.toString();
////            }
////        }
//	}
	
	/**
	 * 面談記録を取得する
	 * 
	 * @param lmsUserId
	 * @return List<MeetiongDto>
	 */

//	public MeetingDto getMeetingDto(Integer meetingId) {
//		
//	}
}

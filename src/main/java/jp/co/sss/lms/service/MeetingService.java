package jp.co.sss.lms.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.TMeeting;
import jp.co.sss.lms.entity.TMeetingDetail;
import jp.co.sss.lms.form.MeetingForm;
import jp.co.sss.lms.repository.MLmsUserRepository;
import jp.co.sss.lms.repository.TMeetingDetailRepository;
import jp.co.sss.lms.repository.TMeetingRepository;

/**
 * MeetingServiceクラス
 *@author キョーソーリン
 */

@Service
public class MeetingService {
	
	@Autowired
	TMeetingRepository tMeetingRepository;
	
	@Autowired
	TMeetingDetailRepository tMeetingDetailRepository;
	
	@Autowired
	MLmsUserRepository mLmsUserRepository;
			
	public void regist(MeetingForm form,Integer lmsUserId) {
		TMeeting tMeeting = new TMeeting();
		//フォームの情報をtMeetingにセットする
		BeanUtils.copyProperties(form, tMeeting);
		//ログインユーザー情報を取得する
		MLmsUser mLmsUser = mLmsUserRepository.getUserDetailBasicInfo(lmsUserId);
		tMeeting.setLmsUserId(form.getLmsUserId());
        tMeeting.setAccountId(mLmsUser.getAccountId());   
        tMeeting.setFirstCreateUser(mLmsUser.getAccountId());   
        tMeeting.setDeleteFlg(mLmsUser.getDeleteFlg());   
        tMeetingRepository.save(tMeeting);
        registDeatil(form, tMeeting);
    }
	
	private void registDeatil(MeetingForm form, TMeeting tMeeting) {
        for (int i = 0; i < form.getQuestion().length; i++) {    
        	TMeetingDetail tMeetingDetail = new TMeetingDetail();
        	//tMeetingDetailにtMeeting情報をセットする
        	BeanUtils.copyProperties(tMeeting,tMeetingDetail);
        	//面談詳細情報．問題　＝　入力された問題
            tMeetingDetail.setQuestion(form.getQuestion()[i]);
            //面談詳細情報．回答　＝　入力された回答
            tMeetingDetail.setAnswer(form.getAnswer()[i]);
            //面談詳細情報．フォロー　＝　入力されたフォロー
            tMeetingDetail.setFollow(form.getFollow()[i]);
            //問題タイプがnullではない、かつ空ではないならば下記のように情報をセットする
            if (form.getQuestionType()[i] != null && !form.getQuestionType()[i].isEmpty()) {
                tMeetingDetail.setQuestionType(Short
                        .parseShort(form.getQuestionType()[i]));
            }
            tMeetingDetail.setAccountId(tMeeting.getAccountId());
            
            //TMeetingDetailテーブルにデータを保存
            tMeetingDetailRepository.save(tMeetingDetail);
        }
    }
	
}

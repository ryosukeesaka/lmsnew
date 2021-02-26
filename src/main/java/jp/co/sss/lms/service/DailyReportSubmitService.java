package jp.co.sss.lms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.DailyReportDto;
import jp.co.sss.lms.dto.DailyReportFbDto;
import jp.co.sss.lms.dto.ExamDto;
import jp.co.sss.lms.entity.TDailyReportFb;
import jp.co.sss.lms.entity.TDailyReportSubmit;
import jp.co.sss.lms.repository.TDailyReportFbRepository;
import jp.co.sss.lms.repository.TDailyReportSubmitRepository;

@Service
public class DailyReportSubmitService {
	@Autowired
	TDailyReportSubmitRepository repository;
	@Autowired
	TDailyReportFbRepository fbRepository;
	
//	public TDailyReportSubmit getTDailyReportSubmit(Integer dailyReportId, Integer lmsUserId, Timestamp date, Integer accountId) {
//		//変更加えてもOK
//		return repository.findByLmsUserIdAndDate(lmsUserId, date, dailyReportId);
//	}
	
	public List<DailyReportDto> getDailyReportDto(Integer lmsUserId) {

		
		// 試験情報を取得
		List<TDailyReportSubmit> tDailyReportSubmitList = repository.findByLmsUserId(lmsUserId);

		List<DailyReportDto> dailyReportDto = new ArrayList<DailyReportDto>();
		
		// 試験Entityリストを試験DTOリストへ詰め替え
		for (TDailyReportSubmit tDailyReportSubmit : tDailyReportSubmitList) {
			DailyReportDto dailyReportDtoSingle = new DailyReportDto();
			List<TDailyReportFb> tDailyReportFbList = fbRepository.findByDailyReportFbIdANDDeleteFlg(tDailyReportSubmit.getDailyReportSubmitId(), tDailyReportSubmit.getDeleteFlg());
			
			int count = 0;
			Date lastFeedbackDate = null;
			
			
			BeanUtils.copyProperties(tDailyReportSubmit,dailyReportDtoSingle);
			BeanUtils.copyProperties(tDailyReportSubmit.getMDailyReport(),dailyReportDtoSingle);
			BeanUtils.copyProperties(tDailyReportSubmit.getMLmsUser(),dailyReportDtoSingle);
	
			for (TDailyReportFb tDailyReportFb : tDailyReportFbList) {
				count++;
				
				lastFeedbackDate = tDailyReportFb.getLastModifiedDate();
				dailyReportDtoSingle.setLastFeedbackDate(lastFeedbackDate);
			}
			dailyReportDtoSingle.setFbCount(count);
			
			dailyReportDto.add(dailyReportDtoSingle);
		}
		
		return dailyReportDto;
	}
}

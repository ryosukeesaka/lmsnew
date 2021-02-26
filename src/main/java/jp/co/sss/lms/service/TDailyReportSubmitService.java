package jp.co.sss.lms.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.entity.TDailyReportSubmit;
import jp.co.sss.lms.repository.TDailyReportSubmitRepository;

@Service
public class TDailyReportSubmitService {
	@Autowired
	 TDailyReportSubmitRepository tDailyReportSubmitRepository;

	public TDailyReportSubmit findByUserAndDate(Integer loginLmsUserId, Timestamp date, Integer dailyReportId) {
		return tDailyReportSubmitRepository.findByLmsUserIdAndDate(loginLmsUserId,date,dailyReportId);
				
	}
	
	// TODO:2021/02/26 久岡 ユーザー詳細のDLボタン実装途中
//	public TDailyReportSubmit findBySubmitId(Integer dailyReportSubmitId) {
//		return tDailyReportSubmitRepository.findBySubmitId(dailyReportSubmitId);
//	}

}

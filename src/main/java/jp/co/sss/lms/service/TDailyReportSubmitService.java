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

}

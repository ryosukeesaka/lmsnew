package jp.co.sss.lms.service;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.entity.TDailyReportSubmit;
import jp.co.sss.lms.repository.TDailyReportSubmitRepository;

@Service
public class DailyReportSubmitService {
	@Autowired
	TDailyReportSubmitRepository repository;
	
	public TDailyReportSubmit getTDailyReportSubmit(Integer dailyReportId, Integer lmsUserId, Timestamp date, Integer accountId) {
		return repository.findByLmsUserIdAndDate(lmsUserId, date, dailyReportId);
	}
}

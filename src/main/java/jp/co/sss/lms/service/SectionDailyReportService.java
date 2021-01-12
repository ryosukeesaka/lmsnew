package jp.co.sss.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.entity.TSectionDailyReport;
import jp.co.sss.lms.repository.TSectionDailyReportRepository;

@Service
public class SectionDailyReportService {
	@Autowired
	TSectionDailyReportRepository repository;

	public TSectionDailyReport getTSectionDailyReport(Integer sectionId, Integer dailyReportId, Integer accountId) {
		return repository.findBysectionIdANDdailyReportIdANDaccountId(sectionId, dailyReportId, accountId);
	}
}

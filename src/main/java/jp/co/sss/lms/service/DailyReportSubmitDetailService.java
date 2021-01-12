package jp.co.sss.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.entity.TDailyReportSubmitDetail;
import jp.co.sss.lms.repository.TDailyReportSubmitDetailRepository;

@Service
public class DailyReportSubmitDetailService {
	@Autowired
	TDailyReportSubmitDetailRepository repository;
	
	public TDailyReportSubmitDetail getTDailyReportSubmitDetail(Integer dailyReportSubmitId, Integer accountId) {
		return repository.findBydailyReportSubmitIdANDaccountd(dailyReportSubmitId, accountId);
	}
}

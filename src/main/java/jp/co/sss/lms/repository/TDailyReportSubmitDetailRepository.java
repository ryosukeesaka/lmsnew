package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TDailyReportSubmitDetail;

@Repository
public interface TDailyReportSubmitDetailRepository extends JpaRepository<TDailyReportSubmitDetail, Integer>{
	@Query("SELECT d FROM TDailyReportSubmitDetail d "
			+ "WHERE d.dailyReportSubmitId = :daily_report_submit_id "
			+ "AND d.accountId = :account_id "
			+ "AND d.deleteFlg = 0")
	TDailyReportSubmitDetail findBydailyReportSubmitIdANDaccountd(
			@Param("daily_report_submit_id") Integer dailyReportSubmitId,
			@Param("account_id") Integer accountId);
}



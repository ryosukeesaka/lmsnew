package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MDailyReport;

@Repository
public interface MDailyReportRepository extends JpaRepository<MDailyReport, Integer>{

	@Query("select T1 from MDailyReport T1 "
			+ "inner join TDailyReportSubmit T2 " 
			+ "inner join MDailyReportDetail T3 " 
			+ "where ((T2.dailyReportSubmitId = :dailyReportSubmitId) "
			+ "and (T1.deleteFlg = 0) "
			+ "and (T2.deleteFlg = 0) "
			+ "and (T3.deleteFlg = 0))")
	MDailyReport findByMDailyReport(@Param ("dailyReportSubmitId") Integer dailyReportSubmitId);
}

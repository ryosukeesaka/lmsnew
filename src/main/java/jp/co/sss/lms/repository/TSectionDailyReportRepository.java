package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TSectionDailyReport;

@Repository
public interface TSectionDailyReportRepository extends JpaRepository<TSectionDailyReport, Integer>{
	@Query("SELECT sdr FROM TSectionDailyReport sdr "
			+ "INNER JOIN MDailyReport dr ON sdr.dailyReportId = dr.dailyReportId "
			+ "INNER JOIN MDailyReportDetail drd ON drd.dailyReportId = dr.dailyReportId "
			+ "WHERE sdr.sectionId = :section_id "
			+ "AND sdr.dailyReportId = :daily_report_id "
			+ "AND sdr.accountId = :account_id "
			+ "AND sdr.deleteFlg = 0")
	TSectionDailyReport findBysectionIdANDdailyReportIdANDaccountId(
			@Param("section_id") Integer sectionId,
			@Param("daily_report_id") Integer dailyReportId,
			@Param("account_id") Integer accountId);
	
	@Query(value="SELECT * FROM t_section_daily_report T1 " + 
			"INNER JOIN m_daily_report T2 "+
			"ON T1.daily_report_id = T2.daily_report_id " +
			"INNER JOIN m_daily_report_detail T3 " +
			"ON T2.daily_report_id = T3.daily_report_id " +
			"WHERE T1.section_id = :sectionId " +
			"AND T1.delete_flg = :deleteFlg " +
			"ORDER BY T3.field_num ASC",nativeQuery = true)
	TSectionDailyReport findBySectionId(@Param("sectionId")Integer sectionId, @Param("deleteFlg")short deleteFlg);
}

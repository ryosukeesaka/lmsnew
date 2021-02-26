package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MDailyReportDetail;

@Repository
public interface MDailyReportDetailRepository extends JpaRepository<MDailyReportDetail,Integer>{
	
	/**
	 *日報詳細情報を取得
	 *
	 * @param dailyReportId
	 * @return List<MDailyReportDetail>
	 */
	@Query(value="SELECT * FROM m_daily_report_detail Detail WHERE Detail.daily_report_id = :dailyReportId",nativeQuery = true)
	List<MDailyReportDetail> findByDailyReportId(@Param("dailyReportId")Integer dailyReportId);

//	@Query(value="SELECT * FROM m_daily_report_detail Detail WHERE Detail.daily_report_submit_id = :dailyReportSubmitId",nativeQuery = true)
//	List<MDailyReportDetail> findByDailyReportSubmitId(@Param("dailyReportSubmitId")Integer dailyReportSubmitId);
}

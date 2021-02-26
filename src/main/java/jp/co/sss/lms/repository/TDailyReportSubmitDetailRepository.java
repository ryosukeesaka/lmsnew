package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TDailyReportSubmitDetail;

@Repository
public interface TDailyReportSubmitDetailRepository extends JpaRepository<TDailyReportSubmitDetail, Integer>{
	
	/**
	 * 日報提出詳細情報の登録件数を取得
	 * 
	 * @param dailyReportSubmitDetailFirstId
	 * @param deleteFlg
	 * @return count
	 */
	@Query(value=" SELECT count(*) FROM t_daily_report_submit_detail Detail WHERE Detail.daily_report_submit_detail_id = :dailyReportSubmitDetailFirstId AND Detail.delete_flg = :deleteFlg",nativeQuery = true)
	Integer CountfindBydailyReportSubmitDetailFirstId(@Param("dailyReportSubmitDetailFirstId")Integer dailyReportSubmitDetailFirstId,@Param("deleteFlg")short dbFlgFalse);

	/**
	 * 日報提出詳細情報を取得
	 * 
	 * @param dailyReportSubmitId
	 * @return List<TDailyReportSubmitDetail>
	 */
	@Query(value="SELECT * FROM t_daily_report_submit_detail T1 WHERE T1.daily_report_submit_id = :dailyReportSubmitId",nativeQuery=true)
	List<TDailyReportSubmitDetail> findByDailyReportSubmitId(@Param("dailyReportSubmitId")Integer dailyReportSubmitId);
}



package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.lms.entity.TIintelligibility;

public interface TIntelligibilityRepository extends JpaRepository<TIintelligibility, Integer>{
	
	/**
	 * 学習理解度情報の登録件数を取得
	 * 
	 * @param intelligibilityFirstId
	 * @param deleteFlg
	 */
	
	@Query(value="SELECT count(*) FROM t_intelligibility T1" + 
			" WHERE T1.intelligibility_id = :intelligentFirstId" + 
			" AND T1.delete_flg= :deleteFlg",nativeQuery=true)
	Integer findById(@Param("intelligentFirstId") Integer intelligibilityFirstId, @Param("deleteFlg") short dbDeleteFlg);
	
	/**
	 * 学習理解度情報を取得
	 * 
	 * @param dailyReportSubmitId
	 * @return List<TIintelligibility>
	 * */
	@Query(value="SELECT * FROM  t_intelligibility T1 WHERE T1.daily_report_submit_id = :dailyReportSubmitId",nativeQuery=true)
	List<TIintelligibility> findByDailyReportSubmitId(@Param("dailyReportSubmitId")Integer dailyReportSubmitId);
}

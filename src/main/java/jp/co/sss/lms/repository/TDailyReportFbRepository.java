package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.lms.entity.TDailyReportFb;

public interface TDailyReportFbRepository extends JpaRepository<TDailyReportFb, Integer> {

	@Query("SELECT f FROM TDailyReportFb f "
			+ "INNER JOIN f.tDailyReportSubmit s "
			+ "WHERE s.dailyReportSubmitId = :dailyReportSubmitId "
			+ "AND f.deleteFlg = :deleteFlg "
			+ "ORDER BY f.firstCreateDate ASC")
	List<TDailyReportFb> findByDailyReportFbIdANDDeleteFlg(
			@Param("dailyReportSubmitId") Integer dailyReportSubmitId,
			@Param("deleteFlg") short deleteFlg);

}
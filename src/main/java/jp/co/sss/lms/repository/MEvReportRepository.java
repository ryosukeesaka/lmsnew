package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MEvReport;

/**
 * 評価レポート情報リポジトリ
 * 
 * @author 東　茉奈
 *
 */
@Repository
public interface MEvReportRepository extends JpaRepository<MEvReport, Integer> {

	/**
	 * 企業アカウントID、会場IDで取得
	 * 
	 * @param accountId
	 * @param placeId
	 * @return List<MEvReport>
	 */
	@Query("SELECT t1 FROM MEvReport t1"
			+ " LEFT OUTER JOIN MEvReportDetail t2"
			+ " ON t1.evReportId = t2.evReportId"
			+ " LEFT OUTER JOIN TEvCourse t3"
			+ " ON t1.evReportId = t3.evReportId"
			+ " LEFT OUTER JOIN MCourse t4"
			+ " ON t3.courseId = t4.courseId"
			+ " LEFT OUTER JOIN TCourseUser t5"
			+ " ON t4.courseId = t5.courseId"
			+ " LEFT OUTER JOIN MLmsUser t6"
			+ " ON t5.lmsUserId = t6.lmsUserId"
			+ " LEFT OUTER JOIN TUserPlace t7"
			+ " ON t6.lmsUserId = t7.lmsUserId"
			+ " WHERE t1.accountId = :accountId"
			+ " AND t1.hiddenFlg = 0"
			+ " AND t1.deleteFlg = 0"
			+ " AND t2.deleteFlg = 0"
			+ " AND t3.deleteFlg = 0"
			+ " AND t4.deleteFlg = 0"
			+ " AND t5.deleteFlg = 0"
			+ " AND t6.deleteFlg = 0"
			+ " AND t7.deleteFlg = 0"
			+ " AND t7.placeId = :placeId"
			+ " ORDER BY t1.evReportId ASC")
	List<MEvReport> findPlaceId(@Param("accountId") Integer accountId, @Param("placeId") Integer placeId);
}

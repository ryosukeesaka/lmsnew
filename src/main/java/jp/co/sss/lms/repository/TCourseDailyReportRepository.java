package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TCourseDailyReport;

@Repository
public interface TCourseDailyReportRepository extends JpaRepository<TCourseDailyReport,Integer> {
	
	@Query("SELECT "
			+ " T1 "
			+ " FROM"
			+ "		TCourseDailyReport	T1"
			+ " INNER JOIN T1.mDailyReport T2"
			+ " INNER JOIN T1.mCourse T3"
			+ " WHERE T3.courseId = :courseId"
			+ " AND T1.deleteFlg = :deleteFlg")
	public TCourseDailyReport findByCourseId(@Param("courseId")Integer courseId,@Param("deleteFlg")short deleteFlg); 
}

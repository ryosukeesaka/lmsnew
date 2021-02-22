package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.lms.entity.TCompanyCourse;
import jp.co.sss.lms.entity.TCourseDailyReport;


/**
 * 企業・コース紐付けテーブル
 * 
 * @author 高谷
 */

public interface TCompanyCourseRepository extends JpaRepository<TCompanyCourse, Integer> {

	@Query("SELECT "
			+ " T1 "
			+ " FROM"
			+ "		TCompanyCourse	T1"
			+ " INNER JOIN T1.mCompany T2"
			+ " INNER JOIN T1.mCourse T3"
			+ " WHERE T2.companyId = :companyId")
	public List<TCompanyCourse> findByCompanyId(@Param("companyId")Integer companyId);
	
}

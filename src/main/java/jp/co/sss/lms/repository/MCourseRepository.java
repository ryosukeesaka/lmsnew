package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MCourse;

/**
 * コース情報リポジトリ
 *　@author 橋爪 優哉
 */
@Repository
public interface MCourseRepository extends JpaRepository<MCourse, Integer> {
	
	//コース関連情報取得
	@Query("SELECT mc FROM  MCourse mc	" + 
			" INNER JOIN mc.mSectionList ms " + 
			"INNER JOIN mc.mCategoryList " + 
			"WHERE mc.courseId = :courseId "+
			"ORDER BY ms.sectionId ASC")
	public MCourse getCourseDetail(@Param("courseId") Integer courseId);
	
	public MCourse findByCourseId(Integer CourseId);
	
	public List<MCourse>findByAccountId(Integer AccountId);
	
	@Query("SELECT T1 FROM MCourse T1"
		  +" INNER JOIN T1.mCategoryList T2"
		  +" INNER JOIN T1.tCourseUserList T3"
		  +" INNER JOIN T3.mLmsUser T4"
		  +" INNER JOIN T4.tUserCompany T5"
		  +" INNER JOIN T5.mCompany T6"
		  +" WHERE T6.companyId = :companyId"
		  +" AND T1.deleteFlg = 0"
		  +" ORDER BY T1.courseName")
	public List<MCourse>findByCompanyId(@Param("companyId")Integer companyId);
}

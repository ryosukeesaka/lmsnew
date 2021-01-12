package jp.co.sss.lms.repository;

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
	
	//コース情報取得
	public MCourse findByCourseId(Integer courseId);

}

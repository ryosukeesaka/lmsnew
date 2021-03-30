package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MDeliverables;

/**
 * MDeliverablesリポジトリ
 *@author 眞鍋 美佳
 */
@Repository
public interface MDeliverablesRepository extends JpaRepository<MDeliverables, Integer>{
	
	/**
	 * 成果物マスタエンティティ取得処理
	 * 
	 * @author m-uno
	 * 
	 * @param placeId lmsユーザーID
	 * @return MDeliverables　成果物マスタエンティティ
	 */
	@Query("SELECT T1 FROM MDeliverables T1 "
			+ "INNER JOIN T1.tDeliverablesSectionList T2 "
			+ "INNER JOIN T2.mSection T3 "
			+ "INNER JOIN T3.mCourse T4 "
			+ "INNER JOIN T4.tConpanyCourseList T5 "
			+ "INNER JOIN T5.tPlaceAssignList T6 "
			+ "INNER JOIN T6.mPlace T7 "
			+ "INNER JOIN T7.tUserPlaceList T8 "
			+ "INNER JOIN T8.mLmsUser T9 "
			+ "WHERE T9.lmsUserId = :lmsUserId "
			+ "AND T1.hiddenFlg = 0 "
			+ "AND T1.deleteFlg = 0 "
			+ "AND T2.deleteFlg = 0 "
			+ "AND T3.deleteFlg = 0 "
			+ "AND T4.deleteFlg = 0 "
			+ "AND T5.deleteFlg = 0 "
			+ "AND T6.deleteFlg = 0 "
			+ "AND T7.deleteFlg = 0 "
			+ "AND T8.deleteFlg = 0 "
			+ "AND T9.deleteFlg = 0 "
			+ "ORDER BY T1.deliverablesName ASC,T1.note ASC"
			)
	List<MDeliverables> findByLmsUserId(@Param("lmsUserId") Integer lmsUserId);
}

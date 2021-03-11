package jp.co.sss.lms.repository;

import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.lms.entity.TPresentationCompany;
import jp.co.sss.lms.entity.TPresentationPlace;

/**
 * 成果報告会リポジトリ
 * 
 * @author Yuna Kato
 */

public interface TPresentationPlaceRepository extends JpaRepository<TPresentationPlace, Integer>{

	@Query("SELECT T1 FROM TPresentationPlace T1 "
			+ "LEFT OUTER JOIN T1.mPresentationTeamList T2 "
			+ "LEFT OUTER JOIN T2.tPresentationCompanyList T3 "
			+ "LEFT OUTER JOIN T1.mPlace T4 "
			+ "LEFT OUTER JOIN T1.mPresentationSchedule T5 "
			+ "WHERE T4.placeId = :placeId "
			+ "AND T5.presentationDate >= :time "
			+ "AND T1.deleteFlg = 0 "
			+ "AND T2.deleteFlg = 0 "
			+ "AND T3.deleteFlg = 0 "
			+ "AND T4.deleteFlg = 0 "
			+ "AND T5.deleteFlg = 0 "
			+ "ORDER BY T1.presentationPlaceId ")
	List<TPresentationPlace> findByPlaceIdAndPresentationDate(@Param("placeId") Integer placeId, @Param("time")Timestamp time);

	@Query("SELECT T1 FROM TPresentationPlace T1 "
			+ "LEFT OUTER JOIN T1.mPresentationTeamList T2 "
			+ "LEFT OUTER JOIN T2.tPresentationCompanyList T3 "
			+ "LEFT OUTER JOIN T1.mPlace T4 "
			+ "LEFT OUTER JOIN T1.mPresentationSchedule T5 "
			+ "WHERE T3.mCompany.companyId = :companyId "
			+ "AND T5.presentationDate >= :time "
			+ "AND T1.deleteFlg = 0 "
			+ "AND T2.deleteFlg = 0 "
			+ "AND T3.deleteFlg = 0 "
			+ "AND T4.deleteFlg = 0 "
			+ "AND T5.deleteFlg = 0 "
			+ "ORDER BY T1.presentationPlaceId ")
	List<TPresentationPlace> findByCompanyIdAndPresentationDate(@Param("companyId") Integer companyId, @Param("time")Timestamp time);

	@Query("SELECT T1 FROM TPresentationPlace T1 "
			+ "LEFT OUTER JOIN T1.mPresentationTeamList T2 "
			+ "LEFT OUTER JOIN T2.tPresentationCompanyList T3 "
			+ "LEFT OUTER JOIN T1.mPlace T4 "
			+ "LEFT OUTER JOIN T1.mPresentationSchedule T5 "
			+ "WHERE T5.presentationDate >= :time "
			+ "AND T1.deleteFlg = 0 "
			+ "AND T2.deleteFlg = 0 "
			+ "AND T3.deleteFlg = 0 "
			+ "AND T4.deleteFlg = 0 "
			+ "AND T5.deleteFlg = 0 "
			+ "ORDER BY T1.presentationPlaceId ")
	List<TPresentationPlace> findByPresentationDate(@Param("time")Timestamp time);

	@Query("SELECT T1 FROM TPresentationPlace T1 "
			+ "LEFT OUTER JOIN T1.mPresentationTeamList T2 "
			+ "LEFT OUTER JOIN T1.mPlace T3 "
			+ "LEFT OUTER JOIN T1.mPresentationSchedule T4 "
			+ "WHERE T1.deleteFlg = 0 "
			+ "AND T2.deleteFlg = 0 "
			+ "AND T3.deleteFlg = 0 "
			+ "AND T4.deleteFlg = 0 "
			+ "ORDER BY T1.presentationPlaceId ASC ")
	List<TPresentationPlace> findAllOrderByPresentationPlaceId();
	
	//LIMITでエラーが出る
//	@Query("SELECT T1 FROM TPresentationPlace T1 "
//			+ "INNER JOIN T1.mPresentationSchedule T2 "
//			+ "INNER JOIN T2.mPresentationScheduleDetailList T3 "
//			+ "LEFT OUTER JOIN T1.mPresentationTeam T4 "
//			+ "LEFT OUTER JOIN T4.tUserPresentationTeamList T5 "
//			+ "LEFT OUTER JOIN T5.mLmsUser T6 "
//			+ "LEFT OUTER JOIN T6.mUser T7 "
//			+ "LEFT OUTER JOIN T6.tUserCompany T8 "
//			+ "LEFT OUTER JOIN T4.tPresentationCompany T9 "
//			+ "INNER JOIN T1.mPlace T10 "
//			+ "WHERE T1.presentationPlaceId = :presentationPlaceId "
//			+ "AND T1.deleteFlg = 0 "
//			+ "AND T2.deleteFlg = 0 "
//			+ "AND T3.deleteFlg = 0 "
//			+ "AND T4.deleteFlg = 0 "
//			+ "AND T5.deleteFlg = 0 "
//			+ "AND T6.deleteFlg = 0 "
//			+ "AND T7.deleteFlg = 0 "
//			+ "AND T8.deleteFlg = 0 "
//			+ "AND T9.deleteFlg = 0 "
//			+ "AND T10.deleteFlg = 0 "
//			+ "ORDER BY T4.presentationTeamId ASC, T5.userPresentationTeamId ASC "
//			+ "LIMIT 1 ")
//	TPresentationPlace findByIdWithRelation(@Param("presentationPlace") Integer presentationPlaceId, Pageable pageable);
	
	@Query("SELECT T1 FROM TPresentationPlace T1 "
			+ "LEFT OUTER JOIN T1.mPresentationTeamList T2 "
			+ "LEFT OUTER JOIN T1.mPlace T3 "
			+ "LEFT OUTER JOIN T1.mPresentationSchedule T4 "
			+ "WHERE T1.mPresentationSchedule = :presentationScheduleId "
			+ "AND T1.deleteFlg = 0 "
			+ "AND T2.deleteFlg = 0 "
			+ "AND T3.deleteFlg = 0 "
			+ "AND T4.deleteFlg = 0 ")
	List<TPresentationPlace> findByScheduleId (@Param("presentationScheduleId") Integer presentationScheduleId);
	
	@Query("SELECT T1 FROM TPresentationPlace T1 "
			+ "LEFT OUTER JOIN T1.mPresentationTeamList T2 "
			+ "LEFT OUTER JOIN T2.tPresentationCompanyList T3 "
			+ "LEFT OUTER JOIN T3.mCompany T4 "
			+ "INNER JOIN T1.mPlace T5 "
			+ "LEFT OUTER JOIN T1.mPresentationSchedule T6 "
			+ "WHERE T6.presentationDate >= :time "
			+ "AND T3.mCompany = :tPresentationCompany "
			+ "AND T1.deleteFlg = 0 "
			+ "AND T2.deleteFlg = 0 "
			+ "AND T3.deleteFlg = 0 "
			+ "AND T4.deleteFlg = 0 "
			+ "AND T5.deleteFlg = 0 "
			+ "AND T6.deleteFlg = 0 "
			)
	List<TPresentationPlace> findOfferdIn(@Param("time") Timestamp time, @Param("tPresentationCompany") TPresentationCompany tPresentationCompany);
}

package jp.co.sss.lms.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

}

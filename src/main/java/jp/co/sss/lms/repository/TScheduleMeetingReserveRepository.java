package jp.co.sss.lms.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jp.co.sss.lms.entity.TMeetingPlace;

/**
 * TScheduleMeetingReserveRepositoryクラス
 *@author 江坂 亮典
 */

@Repository
public interface TScheduleMeetingReserveRepository extends JpaRepository<TMeetingPlace, Integer>{
	
	@Query("SELECT t1 FROM TMeetingPlace t1 "
			+ "LEFT OUTER JOIN MMeetingSchedule t2 "
			+ "ON t1.deleteFlg = t2.deleteFlg "
			+ "LEFT OUTER JOIN MPlace t3 "
			+ "ON t2.deleteFlg = t3.deleteFlg "
			+ "WHERE t3.placeId = :placeId ")
	public List<TMeetingPlace> findByPlaceId(@Param("placeId") Integer placeId);
	
	
	

}

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
			+ "LEFT JOIN t1.mMeetingSchedule t2 "
			+ "LEFT JOIN t1.mPlace t3 "
			+ "WHERE t3.placeId = :placeId "
			+ "ORDER BY t2.meetingOpenDate ")
	public List<TMeetingPlace> findByPlaceId(@Param("placeId") Integer placeId);

	public TMeetingPlace findAllBymeetingPlaceId(@Param("meetingPlaceId") Integer meetingPlaceId);


	
	



}

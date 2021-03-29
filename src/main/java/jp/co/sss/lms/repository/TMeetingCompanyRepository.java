package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TMeetingCompany;

@Repository
public interface TMeetingCompanyRepository extends JpaRepository<TMeetingCompany, Integer> {

	@Query("SELECT tmc FROM TMeetingCompany tmc"
			+ " LEFT OUTER JOIN tmc.mCompany mc"
			+ " LEFT OUTER JOIN tmc.tMeetingPlace tmp "
			+ "WHERE mc.companyId = :companyId"
			+ " AND tmp.meetingPlaceId = :meetingPlaceId"
			+ " AND tmc.deleteFlg = 0")
	public TMeetingCompany findByCompanyIdAndMeetingPlaceId(@Param("companyId") Integer companyId,
			@Param("meetingPlaceId") Integer meetingPlaceId);

}

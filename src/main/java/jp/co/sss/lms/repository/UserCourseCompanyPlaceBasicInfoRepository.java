package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.UserCourseCompanyPlaceInfo;

/**
 * @author 梶山
 * 
 * */
@Repository
public interface UserCourseCompanyPlaceBasicInfoRepository extends JpaRepository<UserCourseCompanyPlaceInfo, Integer> {
	
	@Query(value = 	"SELECT"		 
			      +" mLU.lms_user_id, " 
			      +" mU.user_name,"
			      +" mU.leave_flg, " 
			      +" mU.leave_date, " 
			      +" mLU.role, " 
			      +" mCompany.company_name, " 
			      +" mCompany.company_id,"
			      +" mCourse.course_name, " 
			      +" mP.place_description,"
			      +" mCourse.course_id,"
			      +" mP.place_name, "
			      +" mP.place_id, "
			      +" mP.place_description "
			      +" FROM" 
			      +" m_lms_user AS mLU " 
			      +" LEFT JOIN m_user mU " 
			      +"   ON mlu.user_id = mU.user_id " 
			      +" LEFT JOIN t_user_company tUC " 
			      +"   ON tUC.lms_user_id = mLU.lms_user_id " 
			      +" LEFT JOIN m_company mCompany " 
			      +"   ON tUC.company_id = mCompany.company_id " 
			      +" LEFT JOIN t_course_user mCU " 
			      +"   ON mCU.lms_user_id = mLU.lms_user_id " 
			      +" LEFT JOIN m_course mCourse " 
			      +"   ON mCU.course_id = mCourse.course_id " 
			      +" LEFT JOIN t_user_place tUP " 
			      +"   ON tUP.lms_user_id = mLU.lms_user_id " 
			      +" LEFT JOIN m_place mP " 
			      +"   ON mP.place_id = tUP.place_id" 
			      +" WHERE tUP.place_id = :placeId",
			      nativeQuery = true)
	List<UserCourseCompanyPlaceInfo> getUserCourseCompanyPlaceInfoListByPlaceId(@Param("placeId")Integer placeId);

}

package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TDeliverablesSection;

/**
 * MDeliverablesリポジトリ
 * 
 * @author 橋爪　優哉
 */
@Repository
public interface TDeliverablesSectionRepository extends JpaRepository<TDeliverablesSection, Integer>{
	
	//成果物情報サービス.成果物提出情報取得（セクションID,LMSユーザID）
		@Query(value = "SELECT"
				+" d.deliverables_id "
				+" , ds.deliverables_section_id "
				+" , d.deliverables_name "
				+" , ds.submission_deadline "
				+" , dr.deliverables_result_id"
				+" FROM t_deliverables_section ds"
				+" INNER JOIN m_deliverables d "
				+" ON ds.deliverables_id = d.deliverables_id"
				+" AND d.delete_flg = 0"
				+" LEFT JOIN ("
				  +" SELECT deliverables_result_id, deliverables_section_id"
				  +" FROM t_deliverables_result"
				  +" WHERE lms_user_id = :lmsUserId"
				  +" AND delete_flg = 0"
				+" ) dr"
				+" ON ds.deliverables_section_id = dr.deliverables_section_id"
				+" WHERE ds.section_id = :sectionId"
				+" AND ds.delete_flg = 0"
				+" ORDER BY d.deliverables_name" ,nativeQuery = true)
		public List<TDeliverablesSection> getDeliverablesSubmissionFlg
		(@Param("sectionId") Integer sectionId,@Param("lmsUserId") Integer lmsUserId);
		
		@Query(value = "SELECT"
				+" * "
				+" FROM t_deliverables_section T1 "
				+" INNER JOIN m_deliverables T2 "
				+" ON T1.deliverables_id = T2.deliverables_id "
				+" WHERE T1.section_id = :sectionId "
				+" AND T1.delete_flg = 0 "
				+" ORDER BY T2.deliverables_name ",nativeQuery = true)
		public List<TDeliverablesSection> findBySectionId(@Param("sectionId") Integer sectionId);
		
		@Query(value = "SELECT  "
				+ "* "
				+ "FROM t_deliverables_section T1 "
				+ "INNER JOIN m_deliverables T2 "
				+ "ON T1.deliverables_id = T2.deliverables_id "
				+ "INNER JOIN m_section T3 "
				+ "ON T1.section_id = T3.section_id "
				+ "INNER JOIN m_course T4 "
				+ "ON T3.course_id = T4.course_id "
				+ "INNER JOIN t_course_user T5 "
				+ "ON T4.course_id = T5.course_id "
				+ "INNER JOIN m_lms_user T6 "
				+ "ON T5.lms_user_id = T6.lms_user_id "
				+ "INNER JOIN m_user T7 "
				+ "ON T6.user_id = T7.user_id "
				+ "INNER JOIN t_user_place T8 "
				+ "ON T6.lms_user_id = T8.lms_user_id "
				+ "INNER JOIN m_place T9 "
				+ "ON T8.place_id = T9.place_id "
				+ "INNER JOIN t_user_company T10 "
				+ "ON T6.lms_user_id = T10.lms_user_id "
				+ "INNER JOIN m_company T11 "
				+ "ON T10.company_id = T11.company_id "
				+ "LEFT OUTER JOIN t_deliverables_result T12 "
				+ "ON T1.deliverables_section_id = T12.deliverables_section_id "
				+ "WHERE T1.deliverables_id = :deliverablesId "
				+ "AND T8.place_id = :placeId "
				+ "AND T6.role = :role "
				+ "AND T1.delete_flg = 0 "
				+ "AND T2.delete_flg = 0 "
				+ "AND T3.delete_flg = 0 "
				+ "AND T4.delete_flg = 0 "
				+ "AND T5.delete_flg = 0 "
				+ "AND T6.delete_flg = 0 "
				+ "AND T7.delete_flg = 0 "
				+ "AND T8.delete_flg = 0 "
				+ "AND T9.delete_flg = 0 "
				+ "AND T10.delete_flg = 0 "
				+ "AND T11.delete_flg = 0 "
				+ "AND T12.delete_flg = 0 "
				,nativeQuery = true
				)
		List<TDeliverablesSection> findByDeliverablesIdAndPlaceIdAndRole
		(@Param("deliverablesId") Integer deliverablesId,@Param("placeId") Integer placeId,@Param("role") String role);
}
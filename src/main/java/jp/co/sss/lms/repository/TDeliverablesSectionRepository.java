package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.dto.DeliverableServiceDeliverablesWithSubmissionFlgDto;
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
		
}
package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MSection;

/**
 * セクションマスタリポジトリ
 * @author 眞鍋 美佳
 *
 */
@Repository
public interface MSectionRepository extends JpaRepository<MSection, Integer> {

	//セクション関連情報取得
	@Query(value=" SELECT"
				+ "  *" 
				+ " FROM " 
				+ "    m_section T1" 
				+ " LEFT OUTER JOIN t_file_section T2" 
				+ "    ON T1.section_id = T2.section_id"
				+ " LEFT OUTER JOIN m_file T3"
				+ "    ON T2.file_id = T3.file_id"
				+ " LEFT OUTER JOIN t_exam_section T4"
				+ "    ON T1.section_id = T4.section_id"
				+ "        AND T4.delete_flg = :deleteFlg"
				+ " LEFT OUTER JOIN m_exam T5"
				+ "    ON T4.exam_id = T5.exam_id"
				+ "  	   AND T5.delete_flg = :deleteFlg"
				+ " LEFT OUTER JOIN m_category T6"
				+ "    ON T1.category_id = T6.category_id"
				+ " LEFT OUTER JOIN m_course T7"
				+ "    ON T6.course_id = T7.course_id"
				+ " LEFT OUTER JOIN t_course_daily_report T8"
				+ "    ON T7.course_id = T8.course_id"
				+ " LEFT OUTER JOIN m_daily_report T9"
				+ "    ON T8.daily_report_id = T9.daily_report_id"
				+ " LEFT OUTER JOIN t_section_daily_report T10"
				+ "    ON T1.section_id = T10.section_id"
				+ "        AND T10.delete_flg = :deleteFlg"
				+ " LEFT OUTER JOIN t_deliverables_section T11"
				+ "    ON T1.section_id = T11.section_id"
				+ "        AND T11.delete_flg = :deleteFlg"
				+ "	LEFT OUTER JOIN m_deliverables T12"
				+ "    ON T11.deliverables_id = T12.deliverables_id"
				+ "        AND T12.delete_flg = :deleteFlg"
				+ " WHERE"
				+ "    T1.section_id = :sectionId"
				+ "    AND T1.account_id = :accountId"
				+ "    AND T1.delete_flg = :deleteFlg",nativeQuery = true)
	public MSection getSectionDetail(@Param("sectionId") Integer sectionId, @Param("accountId")Integer accountId, @Param("deleteFlg")short deleteFlg);
	
	//セクション情報取得
	public MSection findBySectionId(Integer sectionId);
}

package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MExam;

/**
 * 試験情報リポジトリ
 *
 */
@Repository
public interface MExamRepository extends JpaRepository<MExam, Integer> {

	@Query(value = "SELECT "
			+ "      * " 
			+ "FROM"  
			+ "      m_exam me"  
			+ "            inner join m_question mq" 
			+ "                on mq.exam_id = me.exam_id" 
			+ "            inner join t_exam_section tes" 
			+ "                on tes.exam_id = me.exam_id" 
			+ "            inner join m_section ms" 
			+ "                on ms.section_id = tes.exam_section_id"
			+ "            inner join t_course_user tcu" 
			+ "                on tcu.course_id = ms.section_id "  
			+ "WHERE" 
			+ "      tes.exam_section_id = :examSectionId" 
			+ "      AND tcu.lms_user_id = :lmsUserId" 
			+ "      AND ms.delete_flg = 0 " 
			+ "      AND me.delete_flg = 0 " 
			+ "      AND mq.delete_flg = 0 " 
			+ "      AND tes.delete_flg = 0 " 
			+ "      AND tcu.delete_flg = 0 " 
			+ "      AND me.hidden_flg = 0" 
			+ "Order By mq.question_id ASC;", nativeQuery = true)
	MExam findByExamSectionIdAndLmsUserId(@Param("examSectionId") Integer examSectionId, @Param("lmsUserId") Integer lmsUserId);

	@Query(value = "SELECT "
		      + "* "
		      + "FROM "
		      + "     m_exam me "
		      + "         INNER JOIN m_question mq "
		      + "             ON me.exam_id = mq.exam_id "
		      + "         INNER JOIN m_genre_detail mgd "
		      + "             ON mq.genre_detail_id = mgd.genre_detail_id "
		      + "         INNER JOIN t_exam_section tes "
		      + "             ON me.exam_id = tes.exam_id "
		      + "         INNER JOIN m_section ms "
		      + "            ON tes.section_id = ms.section_id "
		      + "WHERE "
		      + "     me.exam_id = :examId "
		      + "     AND me.delete_flg = 0 "
		      + "     AND me.hidden_flg = 0 "
		      + "     AND tes.delete_flg = 0 "
		      + "   AND ms.delete_flg = 0 "
		      + "   AND mq.delete_flg = 0 "
		      + "   AND mgd.delete_flg = 0", nativeQuery = true)
	List<MExam> getMultiResultByExamId(@Param("examId") Integer examId);
	
	@Query(value = "SELECT" + 
			"    * " + 
			"FROM " + 
			"    m_exam me " + 
			"        LEFT OUTER JOIN m_question mq " + 
			"        ON mq.exam_id = me.exam_id" + 
			"        LEFT OUTER JOIN m_genre_detail mgd" + 
			"        ON mgd.genre_detail_id = mq.genre_detail_id" + 
			"        LEFT OUTER JOIN t_exam_section tes" + 
			"        ON tes.exam_id = me.exam_id " + 
			"WHERE" + 
			"    me.exam_id = :examId AND" + 
			"    me.account_id = :accountId AND" + 
			"    me.delete_flg = 0 " + 
			"ORDER BY" + 
			"    mq.question_id ASC", nativeQuery = true)
	MExam getSindleResultByExamId(@Param("examId") Integer examId, @Param("accountId") Integer accountId);

}
package jp.co.sss.lms.repository;

import java.sql.Timestamp;
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
	/**
	 * 追加：試験一覧用
	 *
	 * @author Satoru Ushiku
	 * @param examId
	 * @param examName
	 * @param examDescription
	 * @return
	 */
	@Query(value = "SELECT"
			+ " * "
			+ "FROM "
			+ "m_exam me"
				+ "INNER JOIN "
					+ "m_question mq ON me.exam_id = mq.exam_id"
			+ "WHERE me.exam_id = :examId"
			+ "AND me.exam_name Like \"%:examName%\" "
			+ "AND me.exam_description Like \"%:examDescription%\" "
			+ "AND me.hidden_flg = 0"
			+ "AND me.delete_flg = 0"
			+ "ORDER BY "
			+ "me.exam_id ASC", nativeQuery = true)
	List<MExam> findByCondition(@Param("examId") Integer examId,@Param("examName") String examName, @Param("examDescription") String examDescription);

	@Query(value = "SELECT * FROM m_exam me"
			+ "INNER JOIN t_exam_section tes ON tes.exam_id = m_exam.exam_id"
			+ "INNER JOIN m_section ms ON ms.section_id = tes.section_id"
			+ "INNER JOIN m_course mc ON mc.course_id = ms.course_id"
			+ "INNER JOIN t_course_user tcuL ON tcuL.course_id = mc.course_id"
			+ "INNER JOIN m_lms_user mlu ON mlu.lms_user_id = tcuL.lms_user_id"
			+ "INNER JOIN t_user_company tuc ON tuc.lms_user_id = mlu.lms_user_id"
			+ "WHERE tuc.company_id = :companyIdAND mc.close_time >= :closeTime"
			+ "AND me.exam_name LIKE \":examName\""
			+ "AND me.account_id = :accountId"
			+ "AND me.delete_flg = 0"
			+ "AND tes.delete_flg = 0"
			+ "AND ms.delete_flg = 0"
			+ "AND mc.delete_flg = 0"
			+ "AND tcuL.delete_flg = 0"
			+ "AND mlu.delete_flg = 0"
			+ "AND tuc.delete_flg = 0", nativeQuery = true)
	List<MExam> findByCompanyId(@Param("companyId")Integer companyId ,@Param("closeTime")Timestamp closeTime,@Param("examName") String examName);
	
	@Query(value ="SELECT *  "
			+ "             FROM "
			+ "                   m_exam me "
			+ "                        inner join t_exam_section tes "
			+ "                             on tes.exam_id = me.exam_id "
			+ "                        inner join m_section ms "
			+ "                             on ms.section_id = tes.exam_section_id"
			+ "                        inner join m_course mc "
			+ "                             on mc.course_id = ms.course_id"
			+ "                        inner join t_course_user tcu "
			+ "                             on tcu.course_id = mc.course_id"
			+ "                        inner join m_lms_user mlu"
			+ "                             on mlu.lms_user_id = tcu.lms_user_id"
			+ "                        inner join t_user_place tup"
			+ "                             on tup.lms_user_id = mlu.lms_user_id"
			+ "             WHERE "
			+ "                   tup.place_id =:placeId"
			+ "                   AND me.account_id =:accountId  "
			+ "                   AND me.delete_flg = 0"
			+ "                   AND tes.delete_flg = 0"
			+ "                   AND ms.delete_flg = 0"
			+ "                   AND mc.delete_flg = 0"
			+ "                   AND tcu.delete_flg = 0"
			+ "                   AND mlu.delete_flg = 0"
			+ "                   AND tup.delete_flg = 0; ", nativeQuery = true)
	List<MExam> findByAccountId(@Param("placeId") Integer PlaceId, @Param("accountId") Integer AccountId);
	
	
	//初期表示の試験一覧を被りが出ないように表示する処理
	@Query(value ="SELECT *  "
			+ "FROM "
			+ "m_exam me "
			+ "inner join t_exam_section tes "
			+ "on tes.exam_id = me.exam_id "
			+ "WHERE "
			+ "me.delete_flg = 0 "
			+ "AND tes.delete_flg = 0 ", nativeQuery = true)
		List<MExam> findUniqueByAccountId();
//	List<MExam> findUniqueByAccountId(@Param("placeId") Integer PlaceId, @Param("accountId") Integer AccountId);
}




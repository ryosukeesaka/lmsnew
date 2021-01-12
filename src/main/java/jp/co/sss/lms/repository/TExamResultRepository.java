package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TExamResult;

/**
 *試験結果リポジトリ
 *
 */
@Repository
public interface TExamResultRepository extends JpaRepository< TExamResult,  Integer> {

	@Query(value = "SELECT "
			+ "      * " 
			+ "FROM" 
			+ "      t_exam_result ter " 
			+ "WHERE" 
			+ "      ter.lms_user_id = :lmsUserId" 
			+ "      AND ter.exam_section_id = :examSectionId" 
			+ "      AND ter.account_id = :accountId" 
			+ "      AND ter.delete_flg = 0 " 
			+ "ORDER BY ter.exam_result_id ASC;", nativeQuery = true)
	List<TExamResult> findByExamSectionIdAndLmsUserIdAndAccountId(@Param("examSectionId") Integer examSectionId, @Param("lmsUserId") Integer lmsUserId, @Param("accountId") Integer accountId);

	@Query(value = "SELECT "
			    +	"	AVG(ter.score) "
			    +	"FROM "
			    +   "	t_exam_result ter "
			    +    "    	inner join t_exam_section tes "
			    +    "        		on ter.exam_section_id = tes.exam_section_id "
			    +    "        	inner join m_exam me "
			    +    "        		on me.exam_id = tes.exam_id "
			    +    "WHERE "
			    +    "	me.exam_id = :examId AND "
			    +    "	ter.delete_flg = 0", nativeQuery = true)
		Double selectExamScoreAvg(@Param("examId") int examId);

	@Query(value = "SELECT "
		    + "	* "
		    + "FROM "
		    + "   t_exam_result ter "
		    + "       inner join t_exam_section tes "
		    + "           on ter.exam_section_id = tes.exam_section_id "
		    + "       inner join m_exam me "
		    + "           on me.exam_id = tes.exam_id "
		    + "WHERE "
		    + "   me.exam_id = :examId AND "
		    + "   ter.delete_flg = 0", nativeQuery = true)
	List<TExamResult> findByExamId(@Param("examId") int examId);
	
	@Query(value = "SELECT" + 
			"    * " + 
			"FROM" + 
			"    t_exam_result ter " + 
			"        INNER JOIN t_exam_result_detail terd" + 
			"        on ter.exam_result_id = terd.exam_result_id" + 
			"        inner join t_exam_section tes" + 
			"        on ter.exam_section_id = tes.exam_section_id" + 
			"        inner join m_exam me" + 
			"        on tes.exam_id = me.exam_id " + 
			"WHERE" + 
			"    ter.exam_result_id = :examResultId AND" + 
			"    ter.account_id = :accountId AND" + 
			"    ter.delete_flg = 0", nativeQuery = true)
	TExamResult findByExamResultId(@Param("examResultId") int examResultId, @Param("accountId") int accountId);


}



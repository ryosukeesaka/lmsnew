package jp.co.sss.lms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.lms.entity.TExamResult;

/**
 * 試験リポジトリ
 * 
 * @author ヒサオカ
 */
public interface ExamRepository extends JpaRepository<TExamResult, Integer>{
	
	
	@Query("SELECT r FROM TExamResult r " 
			+ "INNER JOIN r.tExamSection s " 
			+ "INNER JOIN s.mExam e "
			+" INNER JOIN r.mLmsUser u "
			+ "WHERE u.lmsUserId = :lmsUserId "
			+ "AND	 r.deleteFlg = 0 "
			+ "AND 	 r.firstCreateDate IS NOT NULL "
			+ "ORDER BY e.examId"
			)
	
	
	//ユーザー詳細の試験結果を呼び出すメソッド
//	public TExamResult findByExamResult(@Param("lmsUserId") Integer lmsUserId);
	List<TExamResult> findByExamResult(@Param("lmsUserId") Integer lmsUserId);
	

}

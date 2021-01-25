package jp.co.sss.lms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TStudentAttendance;

/**
 * 勤怠情報(受講生)リポジトリ
 * 
 * @author ogura
 */
@Repository
public interface TStudentAttendanceRepository extends JpaRepository<TStudentAttendance, Integer> {

	/**
	 * LMSユーザIDに紐づく勤怠情報を取得
	 * 
	 * @param lmsUserId	 LMSユーザID
	 * @return	List<TStudentAttendance> LMSユーザIDに紐づく勤怠情報		
	 */
	@Query("SELECT t1 FROM TStudentAttendance t1"
			+ " WHERE t1.lmsUserId = :lmsUserId"
			+ " AND t1.deleteFlg = 0 ORDER BY t1.trainingDate ASC")
	List<TStudentAttendance> findByLmsUserId(@Param("lmsUserId") Integer lmsUserId);

	/**
	 * LMSユーザIDと研修日に紐づく勤怠情報を取得
	 * 
	 * @param lmsUserId	 LMSユーザID
	 * @param trainingDate	研修日
	 * @return TStudentAttendance LMSユーザIDと研修日に紐づく勤怠情報
	 */
	@Query("SELECT t1 FROM TStudentAttendance t1"
			+ " WHERE t1.lmsUserId = :lmsUserId"
			+ " AND t1.trainingDate = :trainingDate"
			+ " AND t1.deleteFlg = 0")
	TStudentAttendance findByLmsUserIdAndTrainingDate(@Param("lmsUserId") Integer lmsUserId, @Param("trainingDate") Date trainingDate);

}

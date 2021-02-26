package jp.co.sss.lms.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.lms.entity.TDailyReportSubmit;
import jp.co.sss.lms.util.LoggingUtil;

@Repository
public interface TDailyReportSubmitRepository extends JpaRepository<TDailyReportSubmit, Integer> {
	
	@Query("SELECT tdrs FROM  TDailyReportSubmit tdrs " + 
			" LEFT OUTER JOIN tdrs.mLmsUser mlu " + 
			" LEFT OUTER JOIN tdrs.mDailyReport mdr" + 
			" WHERE mlu.lmsUserId = :loginLmsUserId"+
			" AND mdr.dailyReportId = :dailyReportId"+
			" AND tdrs.date = :date"+
			" AND  tdrs.deleteFlg = 0 ")
	TDailyReportSubmit findByLmsUserIdAndDate(@Param("loginLmsUserId")Integer lmsUserId,@Param("date")Timestamp date,@Param("dailyReportId")Integer dailyReportId);
	
	@Query("SELECT tdrs FROM  TDailyReportSubmit tdrs " + 
			" LEFT OUTER JOIN tdrs.mLmsUser mlu " + 
			" LEFT OUTER JOIN tdrs.mDailyReport mdr " +
//			" LEFT OUTER JOIN tdrs.tDailyReportSubmitDetail tdrsd " + // TODO:2021/02/26 久岡 ユーザー詳細のDLボタン実装途中
			" WHERE mlu.lmsUserId = :loginLmsUserId "+
			" AND  tdrs.deleteFlg = 0 ")
	List<TDailyReportSubmit> findByLmsUserId(@Param("loginLmsUserId")Integer lmsUserId);
	
/*	@Query("SELECT s FROM TDailyReportSubmit s"
			+ " LEFT OUTER JOIN s.tDailyReportSubmitDetailList sd"
			+ " LEFT OUTER JOIN s.tIintelligibilityList i"
			+ " LEFT OUTER JOIN s,mDailyReportList dr"
			+ " LEFT OUTER JOIN dr.mDailyReportDetailList drd"
			+ " LEFT OUTER JOIN s.mLmsUser lu"
			+ " LEFT OUTER JOIN lu.mUser u"
			+ " WHERE s.dailyReportId = :daily_report_id"
			+ " AND s.lmsUserId = :lms_user_id"
			+ " AND s.date = :date"
			+ " AND s.accountId = :account_id"
			+ " AND s.deleteFlg = 0")
	TDailyReportSubmit findByDailyReportIdANDLmsUserIdANDDate(
			@Param("daily_report_id") Integer dailyReportId, 
			@Param("lms_user_id") Integer lmsUserId, 
			@Param("date") Date Date,
			@Param("account_id") Integer accountId);*/
	
	
/*	*//** 日報提出と日報マスタ結合*//*
	@Query("SELECT s FROM TDailyReportSubmit s LEFT JOIN TDailyReportSubmitDetail sd ON s.dailyReportSubmitId = sd.dailyReportSubmitId " 
            + "LEFT JOIN TIntelligibility i ON s.dailyReportSubmitId = i.dailyReportSubmitId "
			+ "LEFT JOIN MDailyReport dr ON s.dailyReportId = dr.dailyReportId "
			+ "LEFT JOIN MDailyReportDetail drd  ON dr.dailyReportId = drd.dailyReportId "
			+ "LEFT JOIN MLmsUser lu ON s.lmsUserId = lu.lmsUserId LEFT OUTER JOIN MUser u ON lu.userId = u.mLmsUser "
			+ "WHERE s.dailyReportId = :Id AND s.date = :date AND s.lmsUserId = :lmsUserId AND s.accountId = :accountId")
	List<TDailyReportSubmit> findByDailyReportIdANDDateANDLmsUserIdANDAccountId(
			@Param("Id") Integer dailyReportId,
			@Param("date") Date date,
			@Param("lmsUserId") Integer lmsUserId,
			@Param("accountId") Integer accountId);*/
	
	@Query(value="Select" 
			+ "		count(*)" 
			+ "	FROM"
			+ "    t_daily_report_submit T1"
			+ " LEFT OUTER JOIN t_daily_report_submit_detail T2"
			+ "    ON T1.daily_report_submit_id = T2.daily_report_submit_detail_id"
			+ " LEFT OUTER JOIN t_intelligibility T3"
			+ "    ON T1.daily_report_submit_id = T3.daily_report_submit_id"
			+ " LEFT OUTER JOIN m_daily_report T4"
			+ "    ON T1.daily_report_id = T4.daily_report_id"
			+ " LEFT OUTER JOIN m_daily_report_detail T5" 
			+ "    ON T4.daily_report_id = T5.daily_report_id" 
			+ " LEFT OUTER JOIN m_lms_user T6"
			+ "    ON T1.lms_user_id = T6.lms_user_id"
			+ "	LEFT OUTER JOIN m_user T7"
			+ "    ON T6.user_id = T7.user_id"
			+ " WHERE"
			+ "    T1.daily_report_id = :dailyReportId"
			+ "    AND T1.lms_user_id = :lmsUserId" 
			+ "    AND T1.date = :date"
			+ "    AND T1.delete_flg = :deleteFlg ",nativeQuery = true)
	Integer countByUserAndDate(@Param("dailyReportId")Integer dailyReportId,@Param("lmsUserId")Integer lmsUserId, @Param("date")Timestamp date,@Param("deleteFlg")short deleteFlg);

	@Query("SELECT s FROM TDailyReportSubmit s"
			+ " LEFT OUTER JOIN s.tDailyReportSubmitDetailList sd"
			+ " LEFT OUTER JOIN s.tIintelligibilityList i"
			+ " LEFT OUTER JOIN s.mDailyReport dr"
			+ " LEFT OUTER JOIN dr.mDailyReportDetailList drd"
			+ " LEFT OUTER JOIN s.mLmsUser lu"
			+ " LEFT OUTER JOIN lu.mUser u"
			+ " WHERE"
			+ " s.dailyReportSubmitId = :daily_report_submit_id"
			+ " AND lu.lmsUserId = :lms_user_id"
//			+ " AND s.date = :date"
			+ " AND s.accountId = :account_id"
			+ " AND s.deleteFlg =:deleteFlg ")
	TDailyReportSubmit findByDailyReportSubmitIdANDLmsUserIdANDDate(
			@Param("daily_report_submit_id") Integer dailyReportSubmitId, 
			@Param("lms_user_id") Integer lmsUserId, 
//			@Param("date") Date Date,
			@Param("account_id") Integer accountId,
			@Param("deleteFlg")short deleteFlg);

	// TODO:2021/02/26 久岡 ユーザー詳細のDLボタン実装途中
//	@Query("SELECT s FROM TDailyReportSubmit s "
//			+ "LEFT OUTER JOIN s.tDailyReportSubmitDetailList sd "
//			+ "LEFT OUTER JOIN s.tIintelligibilityList i "
//			+ "LEFT OUTER JOIN s.mLmsUser lu"
//			+ "LEFT OUTER JOIN lu.mUser u "
//			+ "LEFT OUTER JOIN s.tDailyReportFb fb "
//			+ "LEFT OUTER JOIN fb.mLmsUser flu "
//			+ "LEFT OUTER JOIN flu.mUser fu "
//			+ "WHERE s.dailyReportSubmitId = :dailyReportSubmitId "
////			+ "AND s.accountId, LoginUserUtil.LoginAccountId "
//			+ "SND s.deleteFlg = 0 "
//			+ "orderBy sd.fieldNum, i.fieldNum, fb..dailyReportFbId")
//	TDailyReportSubmit findBySubmitId(@Param("dailyReportSubmitId")Integer dailyReportSubmitId);
	

}

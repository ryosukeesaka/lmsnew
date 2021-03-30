package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TDeliverablesResult;
import jp.co.sss.lms.entity.TDeliverablesSection;

@Repository
public interface TDeliverablesResultRepository extends JpaRepository<TDeliverablesResult, Integer>{
	
	//成果物提出情報取得
	List<TDeliverablesResult> findByFilePath(String filePath);
	
	//成果物情報取得
	@Query("Select T1 From TDeliverablesResult T1"
			+ " Inner Join T1.tDeliverablesSection T2"
			+ " Inner Join T1.mLmsUser T3"
			+ " Where T2.deliverablesSectionId = :deliverablesSectionId"
			+ " And T3.lmsUserId = :lmsUserId")
	public TDeliverablesResult findByDeliverablesSectionIdAndLmsUserId(@Param("deliverablesSectionId")Integer deliverablesSectionId,@Param("lmsUserId")Integer lmsUserId);
	
	
	@Query("SELECT t1 FROM TDeliverablesResult t1 "
			+ " INNER JOIN t1.tDeliverablesSection t2 "
			+ " INNER JOIN t1.mLmsUser t3"
			+ " INNER JOIN t2.mDeliverables t4 "
			+ " INNER JOIN t2.mSection t5 "
			+ " WHERE t3.lmsUserId = :lmsUserId "
			+ " AND t1.deleteFlg = 0 "
			+ " ORDER BY t5.date "
			)
	
	List<TDeliverablesResult> findByDeliverablesLmsUserId(@Param("lmsUserId") Integer lmsUserId);
	
	@Query("SELECT t1 FROM TDeliverablesResult t1 "
			+ " INNER JOIN t1.tDeliverablesSection t2 "
			+ " INNER JOIN t2.mDeliverables t3"
			+ " WHERE t1.deliverablesResultId = :deliverablesResultId "
			+ " AND t2.deleteFlg = 0 "
			+ " AND t3.deleteFlg = 0 ")
	TDeliverablesResult findByDeliverablesResultId(@Param("deliverablesResultId") Integer deliverablesResultId);
	
	@Query("SELECT t1 FROM TDeliverablesResult t1 "
			  + " INNER JOIN t1.tDeliverablesSection t2 "
			  + " INNER JOIN t2.mSection t3"
			  + " INNER JOIN t2.mDeliverables t4 "
			  + " INNER JOIN t1.mLmsUser t5 "
			  + " INNER JOIN t5.mUser t6 "
			  + " WHERE t1.deliverablesResultId IN (:deliverablesResultIdList) "
			  + " AND t1.deleteFlg = 0 "
			  + " AND t2.deleteFlg = 0 "
			  + " AND t3.deleteFlg = 0 "
			  + " AND t4.deleteFlg = 0 "
			  + " AND t5.deleteFlg = 0 "
			  + " AND t6.deleteFlg = 0 ")
	List<TDeliverablesResult> findByIdListWithUser(@Param("deliverablesResultIdList") List<Integer> deliverablesResultIdList) ;
	
	@Query("SELECT t1 FROM TDeliverablesResult t1 "
			+ " WHERE t1.deliverablesResultId IN (:deliverablesResultIdList) "
			+ " AND t1.deleteFlg = 0 ")
	List<TDeliverablesResult> findByIdList(@Param("deliverablesResultIdList") List<Integer> deliverablesResultIdList) ;
}

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
	
}

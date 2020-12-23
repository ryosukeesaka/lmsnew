package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TInfo;

@Repository
public interface TInfoRepository extends JpaRepository<TInfo, Integer> {
	
	@Query("SELECT t1 FROM TInfo t1 WHERE t1.lastModifiedDate = (SELECT MAX(t2.lastModifiedDate) FROM TInfo t2) AND t1.deleteFlg = 0")
	TInfo findBySingleResult();

}

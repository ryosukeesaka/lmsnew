package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TDeliverablesResult;

@Repository
public interface TDeliverablesResultRepository extends JpaRepository<TDeliverablesResult, Integer>{
	
	//成果物提出情報取得
	List<TDeliverablesResult> findByFilePath(String filePath);
	
}

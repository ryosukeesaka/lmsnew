package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TExamResultDetail;

/**
 * 試験結果詳細リポジトリ
 * 
 * @author 植草 大徳
 *
 */
@Repository
public interface TExamResultDetailRepository extends JpaRepository< TExamResultDetail,  Integer> {

}

package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MFrequentlyAskedQuestionCategory;

/**
 * よくある質問カテゴリリポジトリ
 * 
 * @author 菅原 俊大
 */
@Repository
public interface MFrequentlyAskedQuestionCategoryRepository
		extends JpaRepository<MFrequentlyAskedQuestionCategory, Integer> {

	/**
	 * よくある質問カテゴリを全件取得する
	 * 
	 * @return よくある質問カテゴリリスト
	 */
	@Query("SELECT t1 FROM MFrequentlyAskedQuestionCategory t1 WHERE t1.deleteFlg = 0")
	List<MFrequentlyAskedQuestionCategory> findAllFrequentlyAskedQuestionCategory();

}


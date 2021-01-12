package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MFrequentlyAskedQuestion;

/**
 * よくある質問リポジトリ
 * 
 * @author 菅原 俊大
 */
@Repository
public interface MFrequentlyAskedQuestionRepository extends JpaRepository<MFrequentlyAskedQuestion, Integer> {

	/**
	 * よくある質問を全件取得する
	 * 
	 * @return よくある質問リスト
	 */
	@Query("SELECT t1 FROM MFrequentlyAskedQuestion t1 WHERE t1.deleteFlg = 0")
	List<MFrequentlyAskedQuestion> findAllFrequentlyAskedQuestion();

	/**
	 * カテゴリIDに一致したよくある質問を取得する
	 * 
	 * @param frequentlyAskedQuestionCategoryId カテゴリID
	 * @return よくある質問リスト
	 */
	@Query("SELECT t1 FROM MFrequentlyAskedQuestion t1 "
			+ "WHERE t1.mFrequentlyAskedQuestionCategory.frequentlyAskedQuestionCategoryId = :frequentlyAskedQuestionCategoryId "
			+ "AND t1.deleteFlg = 0")
	List<MFrequentlyAskedQuestion> findByFrequentlyAskedQuestionCategoryId(
			@Param("frequentlyAskedQuestionCategoryId") Integer frequentlyAskedQuestionCategoryId);

	/**
	 * 検索キーワードに部分一致したよくある質問を取得する
	 * 
	 * @param keyword 検索キーワード
	 * @return よくある質問リスト
	 */
	@Query("SELECT t1 FROM MFrequentlyAskedQuestion t1 WHERE (t1.question LIKE :keyword "
			+ "OR t1.answer LIKE :keyword) AND  t1.deleteFlg = 0")
	List<MFrequentlyAskedQuestion> findByKeyword(@Param("keyword") String keyword);

}

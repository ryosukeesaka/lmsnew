package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MMovieCategory;

/**
 * MMovieCategoryRepository 
 * 
 * 動画カテゴリリポジトリ
 * 
 * @author Aizawa
 */
@Repository
public interface MMovieCategoryRepository extends JpaRepository<MMovieCategory, Integer> {
	
	/**
	 * 動画カテゴリと紐づく動画情報を全件取得する
	 * 
	 * @return 動画リスト
	 */
	@Query("SELECT distinct t1 FROM MMovieCategory t1"
			   + " LEFT OUTER JOIN t1.mMovieList t2"
			   + " ON t2.deleteFlg = 0"
			   + " WHERE t1.deleteFlg = 0"
			   + " ORDER BY t1.movieCategoryId ASC")
	
	List<MMovieCategory> getMovieCategory();
}
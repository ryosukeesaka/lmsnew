package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MPlace;
/**
 * MPlaceリポジトリ
 * 
 * @author endo
 *
 */
@Repository
public interface MPlaceRepository extends JpaRepository<MPlace, Integer> {

	MPlace findByPlaceId(Integer lmsuserid);
	
	/**
	 * 会場マスタエンティティ取得処理
	 * 
	 * 現行ソースでのメソッド名は「findById」であるが、
	 * このメソッド名では独自のクエリとして認識されないためか、
	 * 戻り値がエラーになってしまうため、メソッド名を変更
	 * 
	 * @author 東　茉奈
	 * 
	 * @param placeId 会場ID
	 * @return MPlace　会場マスタエンティティ
	 */
	@Query("SELECT T1 FROM MPlace T1"
			+ " LEFT OUTER JOIN TUserPlace T2"
			+ " ON T1.placeId = T2.placeId"
			+ " AND T2.deleteFlg = 0"
			+ " WHERE T1.placeId = :placeId"
			+ " AND T1.deleteFlg = 0")
	MPlace getMPlaceById(@Param("placeId") Integer placeId);
	
	@Query("SELECT T1 FROM MPlace T1 WHERE T1.accountId = :accountId AND T1.deleteFlg = 0 "
			+ "ORDER BY T1.placeId ASC")
	List<MPlace> findByAccountId(Integer accountId);
}

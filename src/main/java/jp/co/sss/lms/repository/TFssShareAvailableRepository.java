package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TFssShareAvailable;

/**
 * 共有可能ファイルリポジトリ
 * @author 廣江 凌也
 *
 */
@Repository
public interface TFssShareAvailableRepository extends JpaRepository<TFssShareAvailable,Integer>{
	
	/**
	 * 共有するユーザを削除
	 * @param fssGroupId
	 * @param fssUserId
	 */
	@Query("SELECT t1"
			+ " FROM TFssShareAvailable t1"
			+ " INNER JOIN MFssUser t2"
			+ " ON t1.fssUserId = t2.fssUserId"
			+ " INNER JOIN TFssUserGroup t3"
			+ " ON t2.fssUserId = t3.fssUserId"
			+ " WHERE t1.deleteFlg = 0"
			+ " AND t2.deleteFlg = 0"
			+ " AND t3.deleteFlg = 0"
			+ " AND t1.fssUserId = :fssUserId"
			+ " AND t3.fssGroupId = :fssGroupId")
	List<TFssShareAvailable>findByShareUser(@Param("fssGroupId")Integer fssGroupId,@Param("fssUserId")Integer fssUserId);
	
	/**
	 * 共有されるユーザを削除
	 * @param fssGroupId
	 * @param fssUserId
	 */
	@Query("SELECT t1 FROM TFssShareAvailable t1"
			+ " INNER JOIN MFssUser t2"
			+ " ON t1.fssUserId = t2.fssUserId"
			+ " INNER JOIN TFssUserGroup t3"
			+ " ON t2.fssUserId = t3.fssUserId"
			+ " WHERE t1.deleteFlg = 0"
			+ " AND t2.deleteFlg = 0"
			+ " AND t3.deleteFlg = 0"
			+ " AND t3.fssGroupId = :fssGroupId"
			+ " AND t1.fssUserId = :fssUserId")
	List<TFssShareAvailable>findBySharedUser(@Param("fssGroupId")Integer fssGroupId,@Param("fssUserId")Integer fssUserId);
	
	
	/**
	 * 共有ユーザIDで共有可能テーブル情報を取得
	 * @param fssUserId
	 */
	@Query("SELECT t1 FROM TFssShareAvailable t1"
			+ " INNER JOIN MFssUser t2"
			+ " ON t1.fssUserId = t2.fssUserId"
			+ " INNER JOIN TUserFssUser t3"
			+ " ON t2.fssUserId = t3.fssUserId"
			+ " INNER JOIN MUser t4"
			+ " ON t3.userId = t4.userId"
			+ " INNER JOIN TFssUserGroup t5"
			+ " ON t1.fssUserId = t5.fssUserId"
			+ " INNER JOIN MFssGroup t6"
			+ " ON t5.fssGroupId = t6.fssGroupId"
			+ " WHERE t1.deleteFlg = 0"
			+ " AND t2.deleteFlg = 0"
			+ " AND t3.deleteFlg = 0"
			+ " AND t4.deleteFlg = 0"
			+ " AND t5.deleteFlg = 0"
			+ " AND t6.deleteFlg = 0"
			+ " AND t1.fssUserId = :fssUserId")
	List<TFssShareAvailable>findByFssUserId(@Param("fssUserId")Integer fssUserId);
	
}

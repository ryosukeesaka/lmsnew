package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MFssUser;

/**
 * 共有ユーザマスタリポジトリ
 * 
 * @author 廣江 凌也
 *
 */
@Repository
public interface MFssUserRepository extends JpaRepository<MFssUser, Integer> {

	/**
	 * 共有ユーザIDで共有ユーザ詳細情報を取得
	 * 
	 * @param fssUserId
	 */
	@Query("SELECT t1 FROM MFssUser t1" + " LEFT OUTER JOIN TUserFssUser t2" + " ON t1.fssUserId = t2.fssUserId"
			+ " LEFT OUTER JOIN MUser t3" + " ON t2.userId = t3.userId" + " WHERE t2.deleteFlg = 0"
			+ " AND t3.deleteFlg = 0")
	List<MFssUser> findByFssUserDetail(@Param("fssUserId") Integer fssUserId);

	/**
	 * 共有ユーザIDで共有ユーザ全項目情報を取得
	 * 
	 * @param fssUserId
	 */
	@Query("SELECT t1 FROM MFssUser t1" + " LEFT OUTER JOIN TFssUserGroup t2" + " ON t1.fssUserId = t2.fssUserId"
			+ " LEFT OUTER JOIN TFssFile t3" + " ON t1.fssUserId = t3.ownerFssUserId" + " LEFT OUTER JOIN TFssFile t4"
			+ " ON t1.fssUserId = t4.sharedFssUserId" + " LEFT OUTER JOIN TFssShareAvailable t5"
			+ " ON t3.ownerFssUserId = t5.fssUserId" + " LEFT OUTER JOIN TFssShareAvailable t6"
			+ " ON t4.sharedFssUserId = t6.fssUserId" + " WHERE t1.deleteFlg = 0" + " AND t2.deleteFlg = 0"
			+ " AND t3.deleteFlg = 0" + " AND t4.deleteFlg = 0" + " AND t5.deleteFlg = 0" + " AND t6.deleteFlg = 0"
			+ " AND t1.fssUserId = :fssUserId")
	List<MFssUser> findByAllFssUserRelation(@Param("fssUserId") Integer fssUserId);

	/**
	 * ユーザIDで共有ユーザ情報を取得
	 * 
	 * @param userId
	 */
	@Query("SELECT t1 FROM MFssUser t1" + " LEFT OUTER JOIN TUserFssUser t2" + " ON t1.fssUserId = t2.fssUserId"
			+ " WHERE t2.userId = :userId" + " AND t1.deleteFlg = 0" + " AND t2.deleteFlg = 0")
	List<MFssUser> findByUserId(@Param("userId") Integer userId);
	
	/**
	 * 共有ユーザIDで共有ユーザ情報を取得(配列使用)
	 * @param searchFssUserIdArr
	 * @return
	 */
	@Query("SELECT t FROM TFssUserGroup t"
			+ " WHERE t.fssUserId in :searchFssUserIdArr")
	List<MFssUser> findByUserIdArr(@Param("searchFssUserIdArr") Integer[] searchFssUserIdArr);

	/**
	 * 共有ユーザIDで共有ユーザ情報を取得
	 * 
	 * @param fssUserId
	 * @return
	 */
	@Query("SELECT t FROM MFssUser t" + " WHERE t.fssUserId = :fssUserId")
	List<MFssUser> findByFssUserId(@Param("fssUserId") Integer fssUserId);

	/**
	 * 共有ユーザ情報取得(グループID)
	 * 
	 * @param fssUserId
	 * @return
	 */
	@Query("SELECT t FROM MFssUser t" + " WHERE t.fssUserId = :fssUserId")
	List<MFssUser> findByFssGroupIdArr(@Param("fssUserId") MFssUser fssUserId);

}

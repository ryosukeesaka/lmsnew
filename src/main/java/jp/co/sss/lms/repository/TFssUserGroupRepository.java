package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TFssUserGroup;

/**
 * 共有ユーザ・グループ紐付けリポジトリ
 * @author 廣江 凌也
 *
 */
@Repository
public interface TFssUserGroupRepository extends JpaRepository<TFssUserGroup, Integer> {
	
	/**
	 * 共有ユーザID・共有グループIDで共有ユーザ・グループ情報取得
	 * @param fssUserId
	 * @param fssGroupId
	 */
	@Query("SELECT t FROM TFssUserGroup t"
			+ " WHERE t.fssUserId = :fssUserId"
			+ " AND t.fssGroupId = :fssGroupId")
	List<TFssUserGroup>findByfssUserIdAndfssGroupId(@Param("fssUserId")Integer fssUserId,@Param("fssGroupId")Integer fssGroupId);
	
	/**
	 * 共有ユーザ・グループ情報取得(共有グループID)
	 * @param adminFssGroupIdArr
	 * @return
	 */
	@Query(value = "SELECT"
			+ "	*"
			+ " FROM"
			+ "	t_fss_user_group t1"
			+ " INNER JOIN"
			+ "	m_fss_user t2"
			+ " ON"
			+ " t1.fss_user_id = t2.fss_user_id"
			+ " INNER JOIN"
			+ "	t_user_fss_user t3"
			+ " ON"
			+ "	t1.fss_user_id = t3.fss_user_id"
			+ " INNER JOIN"
			+ "	m_user t4"
			+ " ON"
			+ "	t3.user_id = t4.user_id"
			+ " INNER JOIN"
			+ "	m_fss_group t5"
			+ " ON"
			+ "	t1.fss_group_id = t5.fss_group_id"
			+ " WHERE"
			+ "	t1.fss_group_id IN :fssGroupId"
			+ " AND	"
			+ "	t1.delete_flg = 0"
			+ " AND"
			+ "	t2.delete_flg = 0"
			+ " AND"
			+ "	t3.delete_flg = 0"
			+ " AND"
			+ "	t4.delete_flg = 0"
			+ " AND"
			+ "	t5.delete_flg = 0"
			,nativeQuery = true)
	List<TFssUserGroup>findByFssGroupId(@Param("fssGroupId")Integer[] adminFssGroupIdArr);
	
	/**
	 * 共有ユーザ・グループ情報取得(共有グループ名)
	 * @param fssGroupName
	 * @return
	 */
	@Query(value = "SELECT"
			+ "	*"
			+ " FROM"
			+ "	t_fss_user_group t1"
			+ " INNER JOIN"
			+ "	m_fss_group t2"
			+ " ON"
			+ "	t1.fss_group_id = t2.fss_group_id"
			+ " WHERE"
			+ "	t1.delete_flg = 0"
			+ " AND"
			+ "	t2.delete_flg = 0"
			+ " AND"
			+ "	t2.group_name LIKE 'fssGroupName'"
			,nativeQuery = true)
	List<TFssUserGroup>findByFssGroupName(@Param("fssGroupName")String fssGroupName);
	
	/**
	 * 共有ユーザ・グループ情報取得(共有ユーザーID､認証)
	 * @param fssUserId
	 * @param auth
	 * @return
	 */
	@Query(value = "SELECT"
			+ "	*"
			+ " FROM"
			+ "	t_fss_user_group"
			+ " WHERE"
			+ "	delete_flg = 0"
			+ " AND"
			+ "	fss_user_id = :fssUserId"
			+ " AND"
			+ "	auth = :auth"
			,nativeQuery = true)
	List<TFssUserGroup>findByFssUserIdAndAuth(@Param("fssUserId")Integer fssUserId,@Param("auth")Short auth);
	
	
}

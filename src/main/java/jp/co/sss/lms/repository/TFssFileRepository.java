package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TFssFile;

/**
 * 共有ファイル情報リポジトリ
 * @author 廣江 凌也
 * 
 *
 */
@Repository
public interface TFssFileRepository extends JpaRepository<TFssFile, Integer>{
	
	/**
	 * 共有ユーザIDで取得
	 * @param fssUserId
	 * 
	 */
	@Query("SELECT t1 FROM TFssFile t1"
			+ " LEFT OUTER JOIN MFssUser t2"
			+ " ON t1.ownerFssUserId = t2.fssUserId"
			+ " LEFT OUTER JOIN TUserFssUser t3"
			+ " ON t2.fssUserId = t3.fssUserId"
			+ " LEFT OUTER JOIN MUser t4"
			+ " ON t3.userId = t4.userId"
			+ " LEFT OUTER JOIN MFssUser t5"
			+ " ON t1.sharedFssUserId = t5.fssUserId"
			+ " LEFT OUTER JOIN TUserFssUser t6"
			+ " ON t5.fssUserId = t6.fssUserId"
			+ " LEFT OUTER JOIN MUser t7"
			+ " ON t6.userId = t7.userId"
			+ " WHERE t1.ownerFssUserId = :fssUserId"
			+ " AND t1.deleteFlg = 0"
			+ " AND t2.deleteFlg = 0"
			+ " AND t3.deleteFlg = 0"
			+ " AND t4.deleteFlg = 0"
			+ " AND t5.deleteFlg = 0"
			+ " AND t6.deleteFlg = 0"
			+ " AND t7.deleteFlg = 0"
			+ " OR t1.sharedFssUserId = :fssUserId"
			+ " AND t1.deleteFlg = 0"
			+ " AND t2.deleteFlg = 0"
			+ " AND t3.deleteFlg = 0"
			+ " AND t4.deleteFlg = 0"
			+ " AND t5.deleteFlg = 0"
			+ " AND t6.deleteFlg = 0"
			+ " AND t7.deleteFlg = 0"
			+ " ORDER BY t1.filePath,t2.fssUserId ASC")
	List<TFssFile>findByFssUserId(@Param("fssUserId")Integer fssUserId);
	
	
	/**
	 * 共有ユーザID・ファイル識別子で取得
	 * @param fssUserId
	 * @param fileId
	 */
	@Query("SELECT t1 FROM TFssFile t1"
			+ " WHERE t1.ownerFssUserId = :fssUserId"
			+ " AND t1.fssFileId = :fileId"
			+ " AND t1.deleteFlg = 0"
			+ " OR t1.sharedFssUserId = :fssUserId"
			+ " AND t1.fssFileId = :fileId"
			+ " AND t1.deleteFlg = 0")
	List<TFssFile>findByFssUserIdAndFileId(@Param("fssUserId")Integer fssUserId,@Param("fileId")Integer fileId);
	
	/**
	 * 共有済みファイル取得
	 * @param filePathArr
	 * @param ownerFssUserIdArr
	 * @param sharedFssUserIdArr
	 */
	@Query(value = "SELECT COUNT(*)"
			+ " FROM t_fss_file t"
			+ " WHERE file_path = ?1"
			+ " AND owner_fss_user_id = ?2"
			+ " AND shared_fss_user_id = ?3"
			+ " AND delete_flg = 0",
			nativeQuery = true)
	List<TFssFile>findByFilePathArrAndOwnerFssUserIdArrAndSharedFssUserIdArr(
			@Param("filePathArr")String filePathArr,
			@Param("ownerFssUserIdArr")Integer ownerFssUserIdArr,
			@Param("sharedFssUserIdArr")Integer sharedFssUserIdArr
	);
	
	/**
	 * 識別子で取得
	 * @param fssFileId
	 */
	@Query("SELECT t FROM TFssFile t"
			+ " WHERE t.fssFileId = :fssFileId")
	List<TFssFile>findByFssFileId(@Param("fssFileId")Integer fssFileId);
	
	/**
	 * 複数識別子で取得
	 * @param fssFileIdArr
	 */
	@Query("SELECT t FROM TFssFile t"
			+ " WHERE fssFileId = :fssFileIdArr"
			+ " AND deleteFlg = 0")
	List<TFssFile>findByFssFileIdArr(@Param("fssFileIdArr")List<TFssFile> fssFileIdArr);
	
	/**
	 * ファイルパスで取得
	 * @param filePath
	 */
	@Query("SELECT t FROM TFssFile t"
			+ " WHERE t.filePath = :filePath"
			+ " AND t.deleteFlg = 0")
	List<TFssFile>findByFilePath(@Param("filePath")String filePath);
	
	/**
	 * 共有ファイルサイズの合計を取得
	 * @param fssUserId
	 */
	@Query(value = "SELECT SUM(file_size)"
			+ " FROM t_fss_file"
			+ " WHERE owner_fss_user_id = :fssUserId"
			+ " AND shared_fss_user_id IS NULL"
			+ " AND delete_flg = 0",
			nativeQuery = true)
	List<TFssFile> findBySumFileSize(@Param("fssUserId")Integer fssUserId);
	
}

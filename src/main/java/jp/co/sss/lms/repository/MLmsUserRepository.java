package jp.co.sss.lms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MLmsUser;


@Repository
public interface MLmsUserRepository  extends JpaRepository<MLmsUser, Integer>{

	//③.①で取得したパスワード変更情報．ユーザーIDをパラメータとして下記サービスを呼び出し、LMSユーザーマスタ情報を取得する｡
	MLmsUser findByUserId(Integer userid);
	
	/**
	 * ユーザー詳細画面の基本情報ブロックの情報を取得
	 * 
	 * @return LMSユーザーマスタ
	 */
	@Query("SELECT t1 FROM MLmsUser t1"
			+ " INNER JOIN t1.mUser t2"
			+ " LEFT OUTER JOIN t1.tUserCompany t3"
			+ " LEFT OUTER JOIN t3.mCompany t4"
			+ " LEFT OUTER JOIN t1.tCourseUser t5"
			+ " LEFT OUTER JOIN t5.mCourse t6"
			+ " LEFT OUTER JOIN t1.tUserPlace t7"
			+ " LEFT OUTER JOIN t7.mPlace t8"
			+ " WHERE t1.lmsUserId = :lmsUserId AND t1.deleteFlg = 0")
	public MLmsUser getUserDetailBasicInfo(@Param("lmsUserId") Integer lmsUserId);
	
	/**
	 * コース一覧画面
	 * ユーザ情報取得処理
	 */
	@Query(value="SELECT t1 FROM MLmsUser t1 "
				+ "LEFT OUTER JOIN t1.tUserCompany t2 "
				+ "WHERE t1.userId = :userId")
	 public MLmsUser getUserWithCompany(@Param("userId")Integer userId);
	
	@Query("SELECT t1 FROM MLmsUser t1"
			+ " INNER JOIN t1.mUser t2"
			+ " INNER JOIN t1.tUserCompany t3"
			+ " INNER JOIN t3.mCompany t4"
			+ " INNER JOIN t1.tCourseUser t5"
			+ " INNER JOIN t5.mCourse t6"
			+ " INNER JOIN t1.tUserPlace t7"
			+ " INNER JOIN t7.mPlace t8"
			+ " WHERE t8.placeId = :placeId"
			+ " AND t6.openTime <= :courseDate"
			+ " AND t6.closeTime >= :courseDate"
			+ " AND t2.deleteFlg = 0"
			+ " AND t3.deleteFlg = 0"
			+ " AND t4.deleteFlg = 0"
			+ " AND t5.deleteFlg = 0"
			+ " AND t6.deleteFlg = 0"
			+ " AND t7.deleteFlg = 0"
			+ " AND t8.deleteFlg = 0")
	public List<MLmsUser> findStudentByPlaceId(@Param("placeId") Integer placeId, @Param("courseDate") Date courseDate);
	
	@Query(value="SELECT * FROM m_lms_user as t1 "
			+ "INNER JOIN t_course_user as t2 ON t1.lms_user_id = t2.lms_user_id "
			+ "INNER JOIN m_course as t3 ON t2.course_id = t3.course_id "
			+ "INNER JOIN t_user_company as t4 ON t1.lms_user_id = t4.lms_user_id "
			+ "INNER JOIN m_company as t5 ON t4.company_id = t5.company_id "
			+ "INNER JOIN t_user_place as t6 ON t1.lms_user_id = t6.lms_user_id "
			+ "INNER JOIN m_place as t7 ON t6.place_id = t7.place_id "
			+ "INNER JOIN m_user as t8 ON t8.user_id = t1.user_id "
			+ "WHERE t3.course_name ILIKE %:courseName% "
			+ "AND t5.company_name ILIKE %:companyName% "
			+ "AND t6.place_id = :placeId "
			+ "AND t8.user_name ILIKE %:userName% "
			+ "AND t1.role = :role "
			+ "AND t1.account_id = :accountId "
			+ "AND t1.delete_flg = 0", nativeQuery = true
			)
	public List<MLmsUser> findByStudentWithAddress(@Param("courseName") String courseName,
			@Param("companyName") String companyName, @Param("placeId") Integer placeId, @Param("userName") String userName, 
			@Param("role") String role, @Param("accountId") Integer accountId);

	
}

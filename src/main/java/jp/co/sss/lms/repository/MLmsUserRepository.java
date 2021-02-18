package jp.co.sss.lms.repository;

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
}

package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MLmsUser;

/**
 * ログイン情報リポジトリ
 * 
 * @author 加藤正純
 */
@Repository
public interface LoginRepository extends JpaRepository<MLmsUser, Integer> {
	@Query("SELECT t1 FROM MLmsUser t1" + " INNER JOIN t1.mUser t2" + " LEFT OUTER JOIN t1.tUserCompany t3"
			+ " LEFT OUTER JOIN t3.mCompany t4" + " LEFT OUTER JOIN t1.tUserPlace t5"
			+ " LEFT OUTER JOIN t5.mPlace t6" + " LEFT OUTER JOIN t1.tCourseUser t7"
			+ " WHERE t2.loginId = :loginId AND t2.password = :password AND t1.deleteFlg = 0")
	MLmsUser findByLoginIdAndPassword(@Param("loginId") String loginId, @Param("password") String password);
}

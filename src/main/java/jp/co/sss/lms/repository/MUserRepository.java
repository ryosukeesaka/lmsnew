package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MUser;

/**
 * ユーザーマスタリポジトリ
 * 
 */
@Repository
public interface MUserRepository extends JpaRepository<MUser, Integer> {
	// メールアドレス検索
	MUser findByMailAddress(String mailaddress);

	// ユーザーIDで検索
	MUser findByUserId(Integer userid);

	// ログインIDを検索
	MUser findByLoginId(Integer loginid);
}

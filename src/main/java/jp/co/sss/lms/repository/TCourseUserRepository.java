package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.lms.entity.TCourseUser;

/**
 * コース・ユーザー紐付けリポジトリ
 * 
 * @author otake
 */
public interface TCourseUserRepository extends JpaRepository<TCourseUser, Integer> {

	/**
	 * コース・ユーザー紐付けテーブルのLMSユーザID
	 * 
	 * @param lmsUserId
	 */
	TCourseUser findByLmsUserId(Integer lmsUserId);
}
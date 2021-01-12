package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TUserCompany;
/**
 * TUserCompanyリポジトリ
 * 
 * @author endo
 *
 */
@Repository
public interface TUserCompanyRepository extends JpaRepository<TUserCompany, Integer> {

	// LMSユーザーIDで検索
	TUserCompany findByLmsUserId(Integer lmsuserid);
}

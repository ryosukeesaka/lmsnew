package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MCompany;

/**
 * 企業情報リポジトリ
 * @author kato
 *
 */
@Repository
public interface MCompanyRepository extends JpaRepository<MCompany, Integer> {
	
	@Query("SELECT t1 FROM MCompany t1 WHERE t1.companyId = :companyId AND t1.deleteFlg = 0")
	MCompany findByCompanyId(@Param("companyId") Integer companyId);

}

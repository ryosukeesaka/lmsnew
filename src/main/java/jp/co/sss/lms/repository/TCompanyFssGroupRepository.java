package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TCompanyFssGroup;

/**
 * 共有グループマスタ情報リポジトリ
 * 
 * @author Shin
 */
@Repository
public interface TCompanyFssGroupRepository extends JpaRepository<TCompanyFssGroup, Integer> {

	/**
	 * 企業・共有グループ紐付け情報リストを取得
	 * 
	 * @param CompanyId	 企業ID
	 * @param CompanyFssGroupId 企業・共有グループID
	 * @return	List<TCompanyFssGroup> 企業IDに紐づく企業・共有グループ紐づけ情報リスト	
	 */
//	@Query("SELECT t1 FROM TCompanyFssGroup t1"
//	        + " INNER JOIN t1.MCompany m2"
//			+ " ON t1.companyId = m2.companyId"
//			+ " WHERE compnayId  = :companyId")
//	List<TCompanyFssGroup> findByCompanyIdAndCompanyFssGroupId(@Param("companyId") Integer companyId,@Param("companyFssGroupId")Integer companyFssGroupId);
	
	List<TCompanyFssGroup> findByCompanyId(Integer companyId);
}

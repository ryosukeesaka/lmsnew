package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.lms.entity.TCompanyCourse;
import jp.co.sss.lms.entity.TCourseDailyReport;


/**
 * 企業・コース紐付けテーブル
 * 
 * @author 高谷
 */

public interface TCompanyCourseRepository extends JpaRepository<TCompanyCourse, Integer> {

	@Query("SELECT "
			+ " T1 "
			+ " FROM"
			+ "		TCompanyCourse	T1"
			+ " INNER JOIN T1.mCompany T2"
			+ " INNER JOIN T1.mCourse T3"
			+ " WHERE T2.companyId = :companyId")
	public List<TCompanyCourse> findByCompanyId(@Param("companyId")Integer companyId);
	
	/**
      * 企業IDで絞り込み企業・コース紐付けテーブルと契約者同意テーブルの結合結果を取得する。
	  * 最終更新日時を昇順でソート。
	  * @author Shin
	  * @param CompanyId	 企業ID
	  * @return TCompanyCourse コース紐付けテーブルの企業コースIDに紐づく契約同意情報
	  */
	@Query("SELECT T1 FROM TCompanyCourse T1"
				+ " INNER JOIN T1.mCompany T2"
				+ " INNER JOIN T1.tAgreementConsent T3"
				+ " WHERE T2.companyId = :companyId"
				+ " AND T1.deleteFlg = 0"
				+ " AND T3.deleteFlg = 0"
				+ " ORDER BY T1.lastModifiedDate ASC")
	public List<TCompanyCourse> getCompanyCourseAndAgreementConsent(@Param("companyId") Integer companyId);	
}

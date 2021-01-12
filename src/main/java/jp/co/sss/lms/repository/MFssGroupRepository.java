package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MFssGroup;

/**
 * 共有グループマスタリポジトリ
 * @author 廣江 凌也
 *
 */
@Repository
public interface MFssGroupRepository extends JpaRepository<MFssGroup, Integer> {
	/**
	 * 共有グループIDで共有グループ情報を取得
	 * @param fssGroupId
	 */
	@Query("SELECT t FROM MFssGroup t"
			+ " WHERE t.fssGroupId = :fssGroupId")
	List<MFssGroup>findByFssGroupId(@Param("fssGroupId")Integer fssGroupId);
	
	
}

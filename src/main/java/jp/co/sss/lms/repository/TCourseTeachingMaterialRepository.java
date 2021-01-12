package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import jp.co.sss.lms.entity.TCourseTeachingMaterial;

/**
 * コース・教材紐付けリポジトリ
 * 
 * @author otake
 */

public interface TCourseTeachingMaterialRepository extends JpaRepository<TCourseTeachingMaterial, Integer> {

	/**
	 * コース・教材紐付けテーブルのコースID
	 * 
	 * @param courseId
	 */
	List<TCourseTeachingMaterial> findByCourseId(Integer courseId);

}

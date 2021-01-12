package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MPlace;
/**
 * MPlaceリポジトリ
 * 
 * @author endo
 *
 */
@Repository
public interface MPlaceRepository extends JpaRepository<MPlace, Integer> {

	MPlace findByPlaceId(Integer lmsuserid);

}

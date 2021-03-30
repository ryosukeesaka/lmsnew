package jp.co.sss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TUserPlace;
/**
 * TUserPlaceRepositoryリポジトリ
 * 
 * @author endo
 *
 */
@Repository
public interface TUserPlaceRepository extends JpaRepository<TUserPlace, Integer> {

    //LMSユーザーIDで検索
	TUserPlace findByLmsUserId(Integer lmsUserId);
}
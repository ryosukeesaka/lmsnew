package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.TTemporaryPassStorage;

/**
 * TTemporaryPassStorageリポジトリ
 * 
 * @author endo
 *
 */
@Repository
public interface TTemporaryPassStorageRepository extends JpaRepository<TTemporaryPassStorage, Integer> {

	// ユーザーIDで検索

	TTemporaryPassStorage findByUserId(Integer userid);

	// 変更キーで検索

	TTemporaryPassStorage findByChangeKey(String changeKey);

	// 削除
	TTemporaryPassStorage deleteByTemporaryPassStorageId(Integer temporaryPassStorageId);

}

package jp.co.sss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.lms.entity.MDeliverables;

/**
 * MDeliverablesリポジトリ
 *@author 眞鍋 美佳
 */
@Repository
public interface MDeliverablesRepository extends JpaRepository<MDeliverables, Integer>{

}

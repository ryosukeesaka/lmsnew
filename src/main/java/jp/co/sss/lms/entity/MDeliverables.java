package jp.co.sss.lms.entity;

import java.sql.Timestamp;
//import java.util.List;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 成果物マスタエンティティクラス
 * @author 眞鍋 美佳
 */
@Entity
@Table (name = "m_deliverables")
public class MDeliverables  {


    /**成果物ID */
    @Id
    @Column (name = "deliverables_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(name = "generator", allocationSize = 1)
    public Integer deliverablesId;

    /**成果物名 */
    @Column
    private String deliverablesName;

    /** 採点フラグ */
    @Column
    private Short scoreFlg;

    /** フィードバックフラグ */
    @Column
    private Short feedbackFlg;

    /** 備考 */
    @Column
    private String note;

    /** 企業アカウントID */
    @Column
    private Integer accountId;

    /** 削除フラグ */
    @Column
    private Short deleteFlg;

    /** 初回作成者 */
    @Column
    private Integer firstCreateUser;

    /** 初回作成日時 */
    @Column
    private Timestamp firstCreateDate;

    /** 最終更新者 */
    @Column
    private Integer lastModifiedUser;

    /** 最終更新日時 */
    @Column
    private Timestamp lastModifiedDate;

    /** 非表示フラグ */
    @Column
    private Short hiddenFlg;

    @OneToMany(mappedBy = "mDeliverables")
    private List<TDeliverablesSection> tDeliverablesSectionList;

	public Integer getDeliverablesId() {
		return deliverablesId;
	}

	public void setDeliverablesId(Integer deliverablesId) {
		this.deliverablesId = deliverablesId;
	}

	public String getDeliverablesName() {
		return deliverablesName;
	}

	public void setDeliverablesName(String deliverablesName) {
		this.deliverablesName = deliverablesName;
	}

	public Short getScoreFlg() {
		return scoreFlg;
	}

	public void setScoreFlg(Short scoreFlg) {
		this.scoreFlg = scoreFlg;
	}

	public Short getFeedbackFlg() {
		return feedbackFlg;
	}

	public void setFeedbackFlg(Short feedbackFlg) {
		this.feedbackFlg = feedbackFlg;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Short getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Short deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public Integer getFirstCreateUser() {
		return firstCreateUser;
	}

	public void setFirstCreateUser(Integer firstCreateUser) {
		this.firstCreateUser = firstCreateUser;
	}

	public Timestamp getFirstCreateDate() {
		return firstCreateDate;
	}

	public void setFirstCreateDate(Timestamp firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}

	public Integer getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(Integer lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Short getHiddenFlg() {
		return hiddenFlg;
	}

	public void setHiddenFlg(Short hiddenFlg) {
		this.hiddenFlg = hiddenFlg;
	}

	public List<TDeliverablesSection> getTDeliverablesSectionList() {
		return tDeliverablesSectionList;
	}

	public void setTDeliverablesSectionList(List<TDeliverablesSection> tDeliverablesSectionList) {
		this.tDeliverablesSectionList = tDeliverablesSectionList;
	}
}

package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;



/**
 * TDeliverablesSectionエンティティクラス
 * @author 眞鍋 美佳
 */
@Entity
@Table(name = "t_deliverables_section")
public class TDeliverablesSection {


    /** 成果物セクションID */
    @Id
    @Column (name = "deliverables_section_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(name = "generator", allocationSize = 1)
    private Integer deliverablesSectionId;

    /** 提出期限 */
    @Column
    private String submissionDeadline;

    /** 企業アカウントID */
    @Column
    private Integer accountId;

    /** 削除フラグ */
    @Column
    private Short deleteFlg;

    /** 初回作成者 */
    @Column
    private Integer firstCreateUser;

    /** 初回作成日 */
    @Column
    private Timestamp firstCreateDate;

    /** 最終更新者 */
    @Column
    private Integer lastModifiedUser;

    /** 最終更新日 */
    @Column
    private Timestamp lastModifiedDate;
    
    @ManyToOne
    @JoinColumn(name = "deliverables_id", referencedColumnName = "deliverables_id")
    private MDeliverables mDeliverables;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "section_id")
    private MSection mSection;
    
    @OneToMany(mappedBy = "tDeliverablesSection")
    private List<TDeliverablesResult> tDeliverablesResultList;

	public Integer getDeliverablesSectionId() {
		return deliverablesSectionId;
	}

	public void setDeliverablesSectionId(Integer deliverablesSectionId) {
		this.deliverablesSectionId = deliverablesSectionId;
	}

	public String getSubmissionDeadline() {
		return submissionDeadline;
	}

	public void setSubmissionDeadline(String submissionDeadline) {
		this.submissionDeadline = submissionDeadline;
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

	public MDeliverables getMDeliverables() {
		return mDeliverables;
	}

	public void setMDeliverables(MDeliverables mDeliverables) {
		this.mDeliverables = mDeliverables;
	}

	public MSection getMSection() {
		return mSection;
	}

	public void setMSection(MSection mSection) {
		this.mSection = mSection;
	}

	public List<TDeliverablesResult> getTDeliverablesResultList() {
		return tDeliverablesResultList;
	}

	public void setTDeliverablesResultList(List<TDeliverablesResult> tDeliverablesResultList) {
		this.tDeliverablesResultList = tDeliverablesResultList;
	}
}

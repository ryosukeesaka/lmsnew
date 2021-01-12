package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TDeliverableエンティティクラス
 * @author 眞鍋 美佳
 */
@Entity
@Table(name = "t_deliverable")
public class TDeliverable {


    /** 成果物ID */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
        name = "generator",
        allocationSize = 1)
    private Integer deliverableId;

    /** 成果物名 */
    @Column
    private String deliverableName;

    /** 課題ID */
    @Column
    private Integer ticketId;

    /** lLMSユーザID */
    @Column
    private Integer lmsUserId;

    /** 削除フラグ */
    @Column
    private Short status;

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
    
    /** ファイル */
    @ManyToOne
    @JoinColumn(name="file_id", referencedColumnName = "file_id")
    private MFile mFile;
 
	public Integer getDeliverableId() {
		return deliverableId;
	}

	public void setDeliverableId(Integer deliverableId) {
		this.deliverableId = deliverableId;
	}

	public String getDeliverableName() {
		return deliverableName;
	}

	public void setDeliverableName(String deliverableName) {
		this.deliverableName = deliverableName;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public Integer getLmsUserId() {
		return lmsUserId;
	}

	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
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

	public MFile getMFile() {
		return mFile;
	}

	public void setMFile(MFile mFile) {
		this.mFile = mFile;
	}

}

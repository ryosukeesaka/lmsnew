package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * MSubsidyTemplateエンティティクラス
 * @author 眞鍋 美佳
 */
@Entity
@Table(name = "m_subsidy_template")
public class MSubsidyTemplate {

    /** 助成金テンプレートID */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(name = "generator", allocationSize = 1)
    private Integer subsidyTemplateId;

    /** 助成金カテゴリID */
    @Column
    private Integer subsidyCategoryId;

    /** 申請種別コード */
    @Column
    private Short shinseiTypeCd;

    /** 出力タイプコード */
    @Column
    private Short templateTypeCd;

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

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "file_id")
    private MFile mFile;

	public Integer getSubsidyTemplateId() {
		return subsidyTemplateId;
	}

	public void setSubsidyTemplateId(Integer subsidyTemplateId) {
		this.subsidyTemplateId = subsidyTemplateId;
	}

	public Integer getSubsidyCategoryId() {
		return subsidyCategoryId;
	}

	public void setSubsidyCategoryId(Integer subsidyCategoryId) {
		this.subsidyCategoryId = subsidyCategoryId;
	}

	public Short getShinseiTypeCd() {
		return shinseiTypeCd;
	}

	public void setShinseiTypeCd(Short shinseiTypeCd) {
		this.shinseiTypeCd = shinseiTypeCd;
	}

	public Short getTemplateTypeCd() {
		return templateTypeCd;
	}

	public void setTemplateTypeCd(Short templateTypeCd) {
		this.templateTypeCd = templateTypeCd;
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
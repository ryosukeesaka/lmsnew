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
 * TFileMailTemplateエンティティクラス
 * @author 眞鍋 美佳
 */
@Entity
@Table(name = "t_file_mail_template")
public class TFileMailTemplate  {

    /** fileMailTemplateIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(name = "generator", allocationSize = 1)
    public Integer fileMailTemplateId;

    /** mailTemplateIdプロパティ */
    @Column
    private Integer mailTemplateId;

    /** deleteFlgプロパティ */
    @Column
    private Short deleteFlg;

    /** firstCreateUserプロパティ */
    @Column
    private Integer firstCreateUser;

    /** firstCreateDateプロパティ */
    @Column
    private Timestamp firstCreateDate;

    /** lastModifiedUserプロパティ */
    @Column
    private Integer lastModifiedUser;

    /** lastModifiedDateプロパティ */
    @Column
    private Timestamp lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "file_id", referencedColumnName = "file_id")
    private MFile mFile;

	public Integer getFileMailTemplateId() {
		return fileMailTemplateId;
	}

	public void setFileMailTemplateId(Integer fileMailTemplateId) {
		this.fileMailTemplateId = fileMailTemplateId;
	}

	public Integer getMailTemplateId() {
		return mailTemplateId;
	}

	public void setMailTemplateId(Integer mailTemplateId) {
		this.mailTemplateId = mailTemplateId;
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

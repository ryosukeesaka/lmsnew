package jp.co.sss.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ファイルマスタエンティティ
 * @author 眞鍋 美佳
 *
 */

@Entity
@Table(name = "m_file")
public class MFile {
	/**ファイルID */
	@Id
	@Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator",
	    allocationSize = 1)
	private Integer fileId;

	/** ファイル名 */
	@Column
	private String fileName;

	/** ファイル場所 */
	@Column
	private String filePath;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstCreateDate;

	/** 最終更新者 */
	@Column
	private Integer lastModifiedUser;

	/** 最終更新日時 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	/** ファイルセクション */
	@OneToOne
    @JoinColumn(name="file_id", referencedColumnName = "file_section_id")
    private TFileSection tFileSection;

	/** 成果物 */
    @OneToOne
    @JoinColumn(name="file_id", referencedColumnName = "file_id")
    private TDeliverable tDeliverable;
    
    /** ファイル・プロジェクトリスト */
    @OneToMany(mappedBy = "mFile")
    private List<TFileProject> tFileProjectList;
    
    /** ファイル・課題リスト */
    @OneToMany(mappedBy = "mFile")
    private List<TFileTicket> tFileTicketList;
    
    /** ファイル・小課題リスト */
    @OneToMany(mappedBy = "mFile")
    private List<TFileChildTicket> tFileChildTicketList;
    
    /** ファイル・メールテンプレートリスト */
    @OneToMany(mappedBy = "mFile")
    private List<TFileMailTemplate> tFileMailTemplateList;
    
    /** 誓約書テンプレート */
    @OneToOne(mappedBy = "mFile")
    private MSubsidyTemplate mSubsidyTemplate;
    
    /** メール送信リスト */
    @OneToMany(mappedBy = "mFile")
    private List<TMailQueFile> tMailQueFileList;

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public Date getFirstCreateDate() {
		return firstCreateDate;
	}

	public void setFirstCreateDate(Date firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}

	public Integer getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(Integer lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public TFileSection getTFileSection() {
		return tFileSection;
	}

	public void setTFileSection(TFileSection tFileSection) {
		this.tFileSection = tFileSection;
	}

	public TDeliverable getTDeliverable() {
		return tDeliverable;
	}

	public void setTDeliverable(TDeliverable tDeliverable) {
		this.tDeliverable = tDeliverable;
	}

	public List<TFileProject> getTFileProjectList() {
		return tFileProjectList;
	}

	public void setTFileProjectList(List<TFileProject> tFileProjectList) {
		this.tFileProjectList = tFileProjectList;
	}

	public List<TFileTicket> getTFileTicketList() {
		return tFileTicketList;
	}

	public void setTFileTicketList(List<TFileTicket> tFileTicketList) {
		this.tFileTicketList = tFileTicketList;
	}

	public List<TFileChildTicket> getTFileChildTicketList() {
		return tFileChildTicketList;
	}

	public void setTFileChildTicketList(List<TFileChildTicket> tFileChildTicketList) {
		this.tFileChildTicketList = tFileChildTicketList;
	}

	public List<TFileMailTemplate> getTFileMailTemplateList() {
		return tFileMailTemplateList;
	}

	public void setTFileMailTemplateList(List<TFileMailTemplate> tFileMailTemplateList) {
		this.tFileMailTemplateList = tFileMailTemplateList;
	}

	public MSubsidyTemplate getMSubsidyTemplate() {
		return mSubsidyTemplate;
	}

	public void setMSubsidyTemplate(MSubsidyTemplate mSubsidyTemplate) {
		this.mSubsidyTemplate = mSubsidyTemplate;
	}

	public List<TMailQueFile> getTMailQueFileList() {
		return tMailQueFileList;
	}

	public void setTMailQueFileList(List<TMailQueFile> tMailQueFileList) {
		this.tMailQueFileList = tMailQueFileList;
	}

}
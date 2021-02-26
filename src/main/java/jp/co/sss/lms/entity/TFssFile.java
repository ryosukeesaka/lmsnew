package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 共有ファイルテーブル
 * @author 廣江 凌也
 *
 */
@Entity
@Table(name = "t_fss_file")
public class TFssFile {
	
	/**共有ファイルID*/
	@Id
	@Column(name = "fss_file_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
		    name = "generator", 
		    table = "id_generator",
		    pkColumnName = "pk",
		    valueColumnName = "value",
		    pkColumnValue = "T_FSS_FILE_FSS_FILE_ID",
		    allocationSize = 1
		    )
	private Integer fssFileId;
	/**所有共有ユーザID*/
	@Column(name = "owner_fss_user_id")
	private Integer ownerFssUserId;
	
	/**共有先共有ユーザID*/
	@Column(name = "shared_fss_user_id")
	private Integer sharedFssUserId;
	
	/**ファイルパス*/
	@Column
	private String filePath;
	
	/**ファイルサイズ*/
	@Column
	private Integer fileSize;
	
	/**削除フラグ*/
	@Column
	private Short deleteFlg;
	
	/**初回作成者*/
	@Column
	private Integer firstCreateUser;
	
	/**初回作成日時*/
	@Temporal(TemporalType.TIMESTAMP)
    private Date firstCreateDate;
	
	/**最終更新者*/
	@Column
    private Integer lastModifiedUser;
	
	/**最終更新日時*/
	@Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
	
	/**共有ユーザマスタ*/
	@ManyToOne
	@JoinColumn(name = "owner_fss_user_id",referencedColumnName = "fssUserId", insertable=false, updatable=false)
	private MFssUser mFssUserOwnerFssUser;  
	
	/**共有ユーザマスタ*/
	@ManyToOne
	@JoinColumn(name = "shared_fss_user_id",referencedColumnName = "fssUserId", insertable=false, updatable=false)
	private MFssUser mFssUserSharedFssUser;
	
	/**以下、getter/setter*/
	
	public Integer getFssFileId() {
		return fssFileId;
	}

	public void setFssFileId(Integer fssFileId) {
		this.fssFileId = fssFileId;
	}

	public Integer getOwnerFssUserId() {
		return ownerFssUserId;
	}

	public void setOwnerFssUserId(Integer ownerFssUserId) {
		this.ownerFssUserId = ownerFssUserId;
	}

	public Integer getSharedFssUserId() {
		return sharedFssUserId;
	}

	public void setSharedFssUserId(Integer sharedFssUserId) {
		this.sharedFssUserId = sharedFssUserId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
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

	public MFssUser getMFssUserOwnerFssUser() {
		return mFssUserOwnerFssUser;
	}

	public void setMFssUserOwnerFssUser(MFssUser mFssUserOwnerFssUser) {
		this.mFssUserOwnerFssUser = mFssUserOwnerFssUser;
	}

	public MFssUser getMFssUserSharedFssUser() {
		return mFssUserSharedFssUser;
	}

	public void setMFssUserSharedFssUser(MFssUser mFssUserSharedFssUser) {
		this.mFssUserSharedFssUser = mFssUserSharedFssUser;
	}
}

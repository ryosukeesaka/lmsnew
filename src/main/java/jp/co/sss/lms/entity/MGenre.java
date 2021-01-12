package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * ジャンルエンティティ
 * @author 垣花 武留
 *
 */
@Entity
@Table(name = "m_genre")
public class MGenre {
	

    private static final long serialVersionUID = 1L;

    /** ジャンルID */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator",
	    allocationSize = 1)
    @Column(precision = 10, nullable = false, unique = true)
    private Integer genreId;

    /** ジャンル */
    @Column(length = 100, nullable = true, unique = true)
    private String genreName;

    /** 企業アカウントID */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer accountId;

    /** 削除フラグ */
    @Column(precision = 5, nullable = true, unique = false)
    private Short deleteFlg;

    /** 初回作成者 */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer firstCreateUser;

    /** 初回作成日時 */
    @Column(nullable = true, unique = false)
    private Timestamp firstCreateDate;

    /** 最終更新者 */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer lastModifiedUser;

    /** 最終更新日時 */
    @Column(nullable = true, unique = false)
    private Timestamp lastModifiedDate;
    
    /** ジャンル詳細リスト */
    @OneToMany(mappedBy = "mGenre")
    private List<MGenreDetail> mGenreDetailList;

    /** 試験リスト */
    @OneToMany(mappedBy = "mGenre")
    private List<MExam> mExamList;

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
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

	public List<MGenreDetail> getMGenreDetailList() {
		return mGenreDetailList;
	}

	public void setMGenreDetailList(List<MGenreDetail> mGenreDetailList) {
		this.mGenreDetailList = mGenreDetailList;
	}

	public List<MExam> getMExamList() {
		return mExamList;
	}

	public void setMExamList(List<MExam> mExamList) {
		this.mExamList = mExamList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    
}

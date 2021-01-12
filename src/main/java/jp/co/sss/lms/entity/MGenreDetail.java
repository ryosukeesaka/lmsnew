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
 * ジャンル詳細エンティティ
 * @author 垣花 武留
 *
 */
@Entity
@Table(name =  "m_genre_detail")
public class MGenreDetail {
	
	 private static final long serialVersionUID = 1L;

	    /** ジャンル詳細ID */
	    @Id
	    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	    @TableGenerator(
		    name = "generator",
		    allocationSize = 1)
	    @Column(precision = 10, nullable = false, unique = true)
	    private Integer genreDetailId;

	    /** ジャンル詳細 */
	    @Column(length = 100, nullable = true, unique = true)
	    private String genreDetailName;

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
	    
	    /** ジャンル(外部参照) */
	    @ManyToOne
	    @JoinColumn(name="genre_id", referencedColumnName = "genreId")
	    private MGenre mGenre;
	    
	    /** 試験問題リスト */
	    @OneToMany(mappedBy = "mGenreDetail")
	    private List<MQuestion> mQuestionList;

		public Integer getGenreDetailId() {
			return genreDetailId;
		}

		public void setGenreDetailId(Integer genreDetailId) {
			this.genreDetailId = genreDetailId;
		}

		public String getGenreDetailName() {
			return genreDetailName;
		}

		public void setGenreDetailName(String genreDetailName) {
			this.genreDetailName = genreDetailName;
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

		public MGenre getMGenre() {
			return mGenre;
		}

		public void setMGenre(MGenre mGenre) {
			this.mGenre = mGenre;
		}

		public List<MQuestion> getMQuestionList() {
			return mQuestionList;
		}

		public void setMQuestionList(List<MQuestion> mQuestionList) {
			this.mQuestionList = mQuestionList;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	    
	    
}

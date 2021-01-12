package jp.co.sss.lms.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MMovie 
 * 
 * 動画情報エンティティクラス
 * 
 * @author suwa
 */
@Entity
@Table(name = "m_movie")
public class MMovie {
	/** movieIdプロパティ */
	@Id
	private Integer movieId;

	/** movieNameプロパティ */
	@Column
	private String movieName;

	/** urlプロパティ */
	@Column
	private String url;

	/** sortNumberプロパティ */
	@Column
	private Integer sortNumber;

	/** movieCategoryIdプロパティ */
	@Column(name = "movie_category_id", insertable = false, updatable = false)
	private Integer movieCategoryId;

	/** accountIdプロパティ */
	@Column
	private Integer accountId;

	/** deleteFlgプロパティ */
	@Column
	private Short deleteFlg;

	/** firstCreateUserプロパティ */
	@Column
	private Integer firstCreateUser;

	/** firstCreateDateプロパティ */
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstCreateDate;

	/** lastModifiedUserプロパティ */
	@Column
	private Integer lastModifiedUser;

	/** lastModifiedDateプロパティ */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@ManyToOne
	@JoinColumn(name = "movie_category_id", referencedColumnName = "movieCategoryId")
	public MMovieCategory mMovieCategory;

	/**
	 * movieIdのgetterメソッド
	 * 
	 * @return movieId
	 */
	public Integer getMovieId() {
		return movieId;
	}

	/**
	 * movieIdのsetterメソッド
	 * 
	 * @return movieId
	 */
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	/**
	 * movieNameのgetterメソッド
	 * 
	 * @return movieName
	 */
	public String getMovieName() {
		return movieName;
	}

	/**
	 * movieNameのsetterメソッド
	 * 
	 * @return movieName
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	/**
	 * urlのgetterメソッド
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * urlのsetterメソッド
	 * 
	 * @return url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * sortNumberのgetterメソッド
	 * 
	 * @return sortNumber
	 */
	public Integer getSortNumber() {
		return sortNumber;
	}

	/**
	 * sortNumberのsetterメソッド
	 * 
	 * @return sortNumber
	 */
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	/**
	 * movieCategoryIdのgetterメソッド
	 * 
	 * @return movieCategoryId
	 */
	public Integer getMovieCategoryId() {
		return movieCategoryId;
	}

	/**
	 * movieCategoryIdのsetterメソッド
	 * 
	 * @return movieCategoryId
	 */
	public void setMovieCategoryId(Integer movieCategoryId) {
		this.movieCategoryId = movieCategoryId;
	}

	/**
	 * accountIdのgetterメソッド
	 * 
	 * @return accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * accountIdのsetterメソッド
	 * 
	 * @return accountId
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * deleteFlgのgetterメソッド
	 * 
	 * @return deleteFlg
	 */
	public Short getDeleteFlg() {
		return deleteFlg;
	}

	/**
	 * deleteFlgのsetterメソッド
	 * 
	 * @return deleteFlg
	 */
	public void setDeleteFlg(Short deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	/**
	 * firstCreateUserのgetterメソッド
	 * 
	 * @return firstCreateUser
	 */
	public Integer getFirstCreateUser() {
		return firstCreateUser;
	}

	/**
	 * firstCreateUserのsetterメソッド
	 * 
	 * @return firstCreateUser
	 */
	public void setFirstCreateUser(Integer firstCreateUser) {
		this.firstCreateUser = firstCreateUser;
	}

	/**
	 * firstCreateDateのgetterメソッド
	 * 
	 * @return firstCreateDate
	 */
	public Date getFirstCreateDate() {
		return firstCreateDate;
	}

	/**
	 * firstCreateDateのsetterメソッド
	 * 
	 * @return firstCreateDate
	 */
	public void setFirstCreateDate(Date firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}

	/**
	 * lastModifiedUserのgetterメソッド
	 * 
	 * @return lastModifiedUser
	 */
	public Integer getLastModifiedUser() {
		return lastModifiedUser;
	}

	/**
	 * lastModifiedUserのsetterメソッド
	 * 
	 * @return lastModifiedUser
	 */
	public void setLastModifiedUser(Integer lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	/**
	 * lastModifiedDateのgetterメソッド
	 * 
	 * @return lastModifiedDate
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * lastModifiedDateのsetterメソッド
	 * 
	 * @return lastModifiedDate
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * mMovieCategoryのgetterメソッド
	 * 
	 * @return mMovieCategory
	 */
	public MMovieCategory getMMovieCategory() {
		return mMovieCategory;
	}

	/**
	 * mMovieCategoryのgetterメソッド
	 * 
	 * @return mMovieCategory
	 */
	public void setMMovieCategory(MMovieCategory mMovieCategory) {
		this.mMovieCategory = mMovieCategory;
	}
}

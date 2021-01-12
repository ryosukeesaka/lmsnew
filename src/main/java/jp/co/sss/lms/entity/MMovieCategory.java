package jp.co.sss.lms.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

/**
 * MMovieCategory 
 * 
 * 動画カテゴリエンティティクラス
 * 
 * @author suwa
 */
@Entity
@Table(name = "m_movie_category")
public class MMovieCategory {
	/** movieCategoryIdプロパティ */
	@Id
	private Integer movieCategoryId;

	/** movieCategoryNameプロパティ */
	@Column
	private String movieCategoryName;

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

	@OneToMany(mappedBy = "mMovieCategory")
	@Where(clause="delete_flg=0")
	@OrderBy(value = "sort_number asc")
	private List<MMovie> mMovieList;

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
	 * movieCategoryNameのgetterメソッド
	 * 
	 * @return movieCategoryName
	 */
	public String getMovieCategoryName() {
		return movieCategoryName;
	}

	/**
	 * movieCategoryNameのsetterメソッド
	 * 
	 * @return movieCategoryName
	 */
	public void setMovieCategoryName(String movieCategoryName) {
		this.movieCategoryName = movieCategoryName;
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
	 * mMovieListのgetterメソッド
	 * 
	 * @return mMovieList
	 */
	public List<MMovie> getMMovieList() {
		return mMovieList;
	}

	/**
	 * mMovieListのsetterメソッド
	 * 
	 * @return mMovieList
	 */
	public void setMMovieList(List<MMovie> mMovieList) {
		this.mMovieList = mMovieList;
	}
}

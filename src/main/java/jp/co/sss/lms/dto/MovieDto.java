package jp.co.sss.lms.dto;

/**
 * MovieDto
 * 
 * 動画情報DTOクラス
 * 
 * @author 平賀 知誉
 */
public class MovieDto {

	/** 動画名 */
	private String movieName;

	/** 動画URL */
    private String url;
    
    /** 動画カテゴリID */
    private Integer movieCategoryId;
    
    /** 動画ID */
    private Integer movieId;

    /**
     * 動画IDのgetterメソッド
     * @return movieId
     */
    public Integer getMovieId() {
		return movieId;
	}

    /**
     * 動画IDのsetterメソッド
     * @return movieId
     */
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	/**
	 * 動画名のgetterメソッド
	 * @return movieName
	 */
	public String getMovieName() {
		return movieName;
	}

	/**
	 * 動画名のsetterメソッド
	 * @return movieName
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	/**
	 * 動画URLのgetterメソッド
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 動画URLのsetterメソッド
	 * @return url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 動画カテゴリIDのgetterメソッド
	 * @return movieCategoryId
	 */
	public Integer getMovieCategoryId() {
		return movieCategoryId;
	}

	/**
	 * 動画カテゴリIDのsetterメソッド
	 * @return movieCategoryId
	 */
	public void setMovieCategoryId(Integer movieCategoryId) {
		this.movieCategoryId = movieCategoryId;
	}
}

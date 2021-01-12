package jp.co.sss.lms.dto;

import java.util.List;

/**
 * MovieCategoryDto
 * 
 * 動画カテゴリDTOクラス
 * 
 * @author 平賀 知誉
 */
public class MovieCategoryDto {
	
    /** 動画カテゴリID */
    private Integer movieCategoryId;

    /** 動画カテゴリ名 */
    private String movieCategoryName;

    private List<MovieDto> movieDtoList;

    /**
	 * 動画カテゴリIDのgetterメソッド
	 * @param movieCategoryId
	 */
	public Integer getMovieCategoryId() {
		return movieCategoryId;
	}

	/**
	 * 動画カテゴリIDのsetterメソッド
	 * @param movieCategoryId
	 */
	public void setMovieCategoryId(Integer movieCategoryId) {
		this.movieCategoryId = movieCategoryId;
	}

	/**
	 * 動画カテゴリ名のgetterメソッド
	 * @return movieCategoryName
	 */
	public String getMovieCategoryName() {
		return movieCategoryName;
	}

	/**
	 * 動画カテゴリ名のsetterメソッド
	 * @return movieCategoryName
	 */
	public void setMovieCategoryName(String movieCategoryName) {
		this.movieCategoryName = movieCategoryName;
	}

	/**
	 * 動画DTOリストのgetterメソッド
	 * @return movieDtoList
	 */
	public List<MovieDto> getMovieDtoList() {
		return movieDtoList;
	}
	
	/**
	 * 動画DTOリストのsetterメソッド
	 * @return movieDtoList
	 */
	public void setMovieDtoList(List<MovieDto> movieDtoList) {
		this.movieDtoList = movieDtoList;
	}
    

}

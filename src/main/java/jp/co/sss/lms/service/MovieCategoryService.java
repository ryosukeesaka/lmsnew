package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.co.sss.lms.dto.MovieCategoryDto;
import jp.co.sss.lms.dto.MovieDto;
import jp.co.sss.lms.entity.MMovie;
import jp.co.sss.lms.entity.MMovieCategory;
import jp.co.sss.lms.repository.MMovieCategoryRepository;

/**
 * MovieService
 *  
 * 動画視聴機能サービス
 * 
 * @author 諏訪 雄威
 */
@Service
public class MovieCategoryService {

	@Autowired
	MMovieCategoryRepository movieCategoryRepository;

	/**
	 * 動画情報を取得してDTOへ
	 * 
	 * @param  movieDtoList 動画情報が格納されるリスト
	 * @return 動画カテゴリのDtoリスト
	 */
	public List<MovieCategoryDto> getMovieCategoryList() {
		List<MovieCategoryDto> movieCategoryDtoList = new ArrayList<>();

		// 動画カテゴリ情報をすべて取得
		List<MMovieCategory> mMovieCategoryList = movieCategoryRepository.getMovieCategory();

		for (MMovieCategory mMovieCategory : mMovieCategoryList) {
			MovieCategoryDto movieCategoryDto = new MovieCategoryDto();

			BeanUtils.copyProperties(mMovieCategory, movieCategoryDto);
            
			//Listはフィールド名が異なるため別で送る
			List<MovieDto> movieDtoList = new ArrayList<>();
			for (MMovie mMovie : mMovieCategory.getMMovieList()) {
				MovieDto movieDto = new MovieDto();
				BeanUtils.copyProperties(mMovie, movieDto);
				movieDtoList.add(movieDto);
			}

			movieCategoryDto.setMovieDtoList(movieDtoList);

			movieCategoryDtoList.add(movieCategoryDto);
		}

		// List形式のMovieCategoryDtoを返却
		return movieCategoryDtoList;
	}
}

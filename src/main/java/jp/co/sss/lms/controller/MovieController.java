package jp.co.sss.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.MovieCategoryDto;
import jp.co.sss.lms.service.MovieCategoryService;

/**
 * MovieController
 * 
 * 動画コントローラー
 * 
 * @author 平賀 知誉
 */
@RestController
public class MovieController {
	@Autowired
	private MovieCategoryService movieService;

	/**
	 * 動画視聴機能画面の表示
	 * 
	 * @param model モデル
	 * @return 動画視聴画面への遷移
	 */
	@RequestMapping(value = "/movie")
	public ResponseEntity<List<MovieCategoryDto>> index() {

		// カテゴリに紐付く動画情報を取得
		List<MovieCategoryDto> movieCategoryDtoList = movieService.getMovieCategoryList();

		return new ResponseEntity<>(movieCategoryDtoList, HttpStatus.OK);
		
	}

}

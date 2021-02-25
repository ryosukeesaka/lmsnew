package jp.co.sss.lms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.PresentationPlaceDto;
import jp.co.sss.lms.form.PresentationForm;
import jp.co.sss.lms.service.SchedulePresentationService;

/**
 * 成果報告会一覧画面コントローラー
 *
 * @author Natsuki Matsumoto
 */

@RestController
@RequestMapping("/presentation")
public class PresentationReserveListController {
	@Autowired
	SchedulePresentationService schedulePresentationService;

	@RequestMapping(path = "/reserveList", method = RequestMethod.POST)
	public ResponseEntity<List<PresentationPlaceDto>> reserveList(@RequestBody PresentationForm presentationForm) {

		// 取得した値をパラメータとして下記サービスを呼び出し、成果報告会対象会場情報を取得する。
		List<PresentationPlaceDto> presentationPlaceDtoList = schedulePresentationService.getPresentationPlaceDtoList(presentationForm);

		return new ResponseEntity<List<PresentationPlaceDto>>(presentationPlaceDtoList, HttpStatus.OK);
	}
}

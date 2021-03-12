package jp.co.sss.lms.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.lms.dto.AttendanceManagementDto;
import jp.co.sss.lms.dto.PresentationPlaceDto;
import jp.co.sss.lms.dto.SectionServiceSectionDto;
import jp.co.sss.lms.service.LoginService;
import jp.co.sss.lms.service.TPresentationPlaceService;

/**
 * チーム編成一覧コントローラ
 * 
 * @author Yamaki　Kakeru
 */
@Controller
@RequestMapping("/team")
public class PresentationController {
	@Autowired
	private TPresentationPlaceService placeService;
	
	/**
	 * チーム編成一覧画面の初期表示
	 * 
	 * @param model モデル
	 * @return 勤怠管理画面への遷移
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<PresentationPlaceDto>> index(@RequestParam("placeId") Integer loginPlaceId){
		List<PresentationPlaceDto> PresentationPlaceDtoList = placeService.getPresentationPlaceDtoList(loginPlaceId);
		
		
		
		
		return new ResponseEntity<>(PresentationPlaceDtoList, HttpStatus.OK);
	}
}
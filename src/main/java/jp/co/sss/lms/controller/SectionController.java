package jp.co.sss.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.DeliverableServiceDeliverablesWithSubmissionFlgDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.dto.SectionServiceSectionDto;
import jp.co.sss.lms.service.DeliverableService;
import jp.co.sss.lms.service.SectionService;
import jp.co.sss.lms.util.LoggingUtil;

/**
 * SectionController セクション詳細画面の画面遷移を行うコントローラー
 * 
 * @author 橋爪 優哉
 */
@RestController
@RequestMapping("/section")
public class SectionController {

	@Autowired
	HttpSession httpSession;
	@Autowired
	SectionService sectionService;
	@Autowired
	DeliverableService deliverableService;
	@Autowired
	LoggingUtil loggingUtil;
	@Autowired
	LoginUserDto loginUserDto;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * セクション詳細画面初期表示を行うメソッド
	 * 
	 * @param sectionId セクションID
	 * @param model     モデル
	 * @return /section/detail セクション詳細画面へ遷移する
	 */
	@PostMapping("/detail")
	public ResponseEntity<Model> postDetail(@RequestParam("sectionId") Integer sectionId, Model model) {

		// セクション情報が取得できないときはエラー画面に遷移
		String message = sectionService.getSessionInfo(sectionId);
		if (!message.isEmpty()) {
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());

			model.addAttribute("message", message);

			return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);

		} else {

			SectionServiceSectionDto sectionServiceSectionDto = sectionService.getSectionDto(sectionId);
			List<DeliverableServiceDeliverablesWithSubmissionFlgDto> deliverablesWithSubmissionFlgDtoList = deliverableService
					.getDeliverableWithSubmissionFlgDto(sectionId, loginUserDto.getLmsUserId());

			model.addAttribute("sectionServiceSectionDto", sectionServiceSectionDto);
			model.addAttribute("deliverablesWithSubmissionFlgDtoList", deliverablesWithSubmissionFlgDtoList);

			return new ResponseEntity<>(model, HttpStatus.OK);
		}
	}

	/**
	 * パンくずリストで使用するメソッド
	 * 
	 * @param sectionId セクションID
	 * @param model     モデル
	 * @return postDetailメソッドを呼び出す
	 */
	@GetMapping("/detail")
	public ResponseEntity<Model> getDetail(@RequestParam("sectionId") Integer sectionId, Model model) {
		return this.postDetail(sectionId, model);
	}

}

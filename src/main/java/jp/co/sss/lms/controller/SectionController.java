package jp.co.sss.lms.controller;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.DeliverableServiceDeliverablesWithSubmissionFlgDto;
import jp.co.sss.lms.dto.SectionServiceSectionDto;
import jp.co.sss.lms.form.SectionForm;
import jp.co.sss.lms.service.DeliverableService;
import jp.co.sss.lms.service.SectionService;
import jp.co.sss.lms.util.Constants;
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
	SectionService sectionService;
	@Autowired
	DeliverableService deliverableService;
	@Autowired
	LoggingUtil loggingUtil;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * セクション詳細画面初期表示を行うメソッド
	 * 
	 * @param sectionId セクションID
	 * @param model     モデル
	 * @return /section/detail セクション詳細画面へ遷移する
	 */
	@PostMapping("/detail")
	public ResponseEntity<SectionServiceSectionDto> postDetail(@RequestBody SectionForm sectionForm, Model model) {
		
		SectionServiceSectionDto sectionServiceSectionDto = new SectionServiceSectionDto();
		HttpStatus httpStatus = HttpStatus.OK;
		// セクション情報が取得できないときはエラー画面に遷移
		String message = sectionService.getSessionInfo(sectionForm);
		if (!message.isEmpty()) {
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
			
			httpStatus = HttpStatus.BAD_REQUEST;
			
		} else {
			//20210212_Nishijima_Add_Start
			//ログインユーザのロールを取り出す
			String userRole = sectionForm.getRole();
			sectionServiceSectionDto = sectionService.getSectionDto(sectionForm);
			//受講生の場合
			if(userRole.equals(Constants.CODE_VAL_ROLL_STUDENT)) {
				List<DeliverableServiceDeliverablesWithSubmissionFlgDto> deliverablesWithSubmissionFlgDtoList = deliverableService
						.getDeliverableWithSubmissionFlgDto(Integer.parseInt(sectionForm.getSectionId()), sectionForm.getLmsUserId());
				sectionServiceSectionDto.setDeliverablesWithSubmissionFlgDtoList(deliverablesWithSubmissionFlgDtoList);
				}
			//講師の場合
			else{
				sectionServiceSectionDto.setDeliverablesDtoList(deliverableService.getDeliverablesSectionDtoList(Integer.parseInt(sectionForm.getSectionId())));
			}
		}
		//20210212_Nishijima_Add_End
		
		return new ResponseEntity<>(sectionServiceSectionDto, httpStatus);
	}

	/**
	 * パンくずリストで使用するメソッド
	 * 
	 * @param sectionId セクションID
	 * @param model     モデル
	 * @return postDetailメソッドを呼び出す
	 */
	@GetMapping("/detail")
	public ResponseEntity<SectionServiceSectionDto> getDetail(@RequestBody SectionForm sectionForm, Model model) {
		
		return this.postDetail(sectionForm,model);
	}

}
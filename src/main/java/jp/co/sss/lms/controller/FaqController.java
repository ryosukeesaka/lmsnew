package jp.co.sss.lms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.form.FaqSearchForm;
import jp.co.sss.lms.service.FaqService;

/**
 * よくある質問コントローラー
 * 
 * @author 菅原 俊大
 */
@RestController
@RequestMapping("/faq")
public class FaqController {

	@Autowired
	private FaqService faqService;

	/**
	 * よくある質問画面の初期表示
	 * 
	 * @param model モデル
	 * @param form  よくある質問入力フォーム
	 * @return よくある質問画面への遷移
	 */
	@RequestMapping(value = "")
	public ResponseEntity<Map<String, Object>> index(@RequestBody FaqSearchForm faqSearchForm) {

		Map<String, Object> map = new HashMap<>();
		map.put("faqCategoryList", faqService.getFaqCategoryList());
		map.put("faqList", faqService.getFaqDtoList(faqSearchForm));

		return new ResponseEntity<>(map, HttpStatus.OK);
		
	}

}

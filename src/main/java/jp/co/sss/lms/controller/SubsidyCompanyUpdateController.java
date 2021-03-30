package jp.co.sss.lms.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jp.co.sss.lms.dto.SubsidyCompanyUpdateDto;
import jp.co.sss.lms.form.SubsidyCompanyUpdateForm;
import jp.co.sss.lms.repository.TCompanyCourseRepository;
import jp.co.sss.lms.service.SubsidyCompanyUpdateService;

/**
 * 企業情報編集コントローラ
 * 
 * @author Shin
 */
@RestController
@RequestMapping("/subsidy/company/update")
public class SubsidyCompanyUpdateController {

	@Autowired
	SubsidyCompanyUpdateService subsidyCompanyUpdateService;
	
	/**
	 * 企業情報編集画面の初期表示
	 * 
	 * @param companyId  企業ID
	 * @param lmsUserId LMSユーザID
	 * @param role　権限
	 * @return 企業情報DTOへ遷移
	 */
	@RequestMapping("")
	public ResponseEntity<SubsidyCompanyUpdateDto> get(@RequestParam("companyId") Integer companyId) {
		SubsidyCompanyUpdateDto subsidyCompanyUpdateDto = new SubsidyCompanyUpdateDto();
		
		// 企業情報取得
		subsidyCompanyUpdateDto = subsidyCompanyUpdateService.get(companyId);
		
		return new ResponseEntity<SubsidyCompanyUpdateDto>(subsidyCompanyUpdateDto, HttpStatus.OK);
	}
	
	/**
	 * 企業情報編集画面の更新
	 * 
	 * @param subsidyCompanyUpdateForm 企業編集情報のフォーム
	 * @return 企業情報画面の初期表示メソッドへ遷移
	 */
	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> complete(@RequestBody SubsidyCompanyUpdateForm subsidyCompanyUpdateForm) { 
		Map<String, Object> map = new HashMap<>();
		
		// 企業情報更新
		subsidyCompanyUpdateService.put(subsidyCompanyUpdateForm);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
	
	


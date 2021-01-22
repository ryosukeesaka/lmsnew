package jp.co.sss.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.OperatorDto;
import jp.co.sss.lms.service.SupportService;

@RestController
@RequestMapping("/support")
public class SupportController {
	
	@Autowired
	private SupportService supportService;
	
	@RequestMapping(value = "")
	public ResponseEntity<List<OperatorDto>> index(@RequestParam("operatorType") Integer operatorType) {
		
		List<OperatorDto> operatorDtoList = supportService.getOperator(operatorType);
		
		return new ResponseEntity<>(operatorDtoList, HttpStatus.OK);
	}

}

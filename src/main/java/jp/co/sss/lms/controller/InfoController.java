package jp.co.sss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.InfoDto;
import jp.co.sss.lms.service.InfoService;

@RestController
@RequestMapping("/api/info")
public class InfoController {
	
	@Autowired
	private InfoService infoService;
	
	@RequestMapping("")
	public ResponseEntity<InfoDto> index() {
		InfoDto infoDto = infoService.getInfo();
		
		return new ResponseEntity<>(infoDto, HttpStatus.OK);
	}

}

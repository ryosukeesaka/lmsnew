package jp.co.sss.lms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.form.DeliverablesForm;
import jp.co.sss.lms.service.DeliverableService;
import jp.co.sss.lms.util.LoggingUtil;


@RestController
@RequestMapping("/deliverables")
public class DeliverableController {
	
	@Autowired
	LoggingUtil loggingUtil;
	
	@Autowired
	DeliverableService deliverableService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostMapping(value="/upload",consumes = "multipart/form-data")
	public ResponseEntity<String> upload(@RequestBody DeliverablesForm uploadFile) {
		//入力パラメータのチェック
		String message = deliverableService.checkDeliverablesInfo(uploadFile);
		
		if (!message.isEmpty()) {
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
		//ファイルアップロード処理 (アップロードに成功した場合true）
		boolean isUpload = deliverableService.deliverableUpload(uploadFile);
		
		//アップロードに失敗した場合
		if(!isUpload) {
			String faildMessage= deliverableService.failUpload();
			return new ResponseEntity<>(faildMessage, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("", HttpStatus.OK);
		
	}
}

package jp.co.sss.lms.controller;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@PostMapping(value="/upload")
	@ResponseBody
	public ResponseEntity<String> upload(@RequestParam("file")MultipartFile multipartFile,@RequestParam("sectionId")String sectionId, @RequestParam("deliverableId")String deliverableId,
											@RequestParam("lmsUserId")String lmsUserId, @RequestParam("accountId") String accountId, @RequestParam("firstCreateUser")String firstCreateUser, 
											 @RequestParam("lastModifiedUser")String lastModifiedUser, @RequestParam("deliverablesName")String deliverablesName) {
		
		//入力パラメータのチェック
		String message = deliverableService.checkDeliverablesInfo(multipartFile,deliverableId,deliverablesName);
		
		if (!message.isEmpty()) {
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
		//ファイルアップロード処理 (アップロードに成功した場合true）
		boolean isUpload = deliverableService.deliverableUpload(multipartFile,sectionId,deliverableId,lmsUserId,accountId,firstCreateUser,lastModifiedUser);
		
		//アップロードに失敗した場合
		if(!isUpload) {
			String faildMessage= deliverableService.failUpload();
			return new ResponseEntity<>(faildMessage, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("", HttpStatus.OK);
		
	}
}

package jp.co.sss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sss.lms.dto.FileShareDto;
import jp.co.sss.lms.dto.ShareUserDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.service.FileShareService;

/**
 * FileShareController
 * ファイル共有コントローラ
 * @author 田中 和希
 */
@RestController
@RequestMapping("/fileshare/list")
public class FileShareController {
	
	@Autowired
	HttpSession session;

	@Autowired
	FileShareService fileShareService;
	
	@Autowired
	LoginUserDto loginUserDto;

	/** ダウンロード可能なファイルのリスト */
	@Autowired
	public List<ShareUserDto> shareUserDtoList;

	/**
	 * 共有ファイルリストの表示<br>
	 * 
	 * @param model
	 * @return ファイル共有画面
	 */
	@RequestMapping(value = "")
	public ResponseEntity<List<FileShareDto>> index(@RequestParam("userId") Integer userId) {

		// ファイルリストの取得
		List<FileShareDto> fileShareDtoList = fileShareService.getFileList(userId);

		return new ResponseEntity<>(fileShareDtoList, HttpStatus.OK);

	}

	/**
	 * ファイルのアップロード、現在未完成
	 */
	//@PostMapping(value="/upload")
	//@ResponseBody
	
	@RequestMapping(value = "/upload", method = {RequestMethod.POST})
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile) {
		
		boolean isUploadSuccess = fileShareService.uploadFile(multipartFile);
		
		if(!isUploadSuccess) {
			//エラーメッセージを格納
			//String message = "エラー";
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("", HttpStatus.OK);

	}

	/**
	 * ファイルの削除、現在未完成
	 */
	@RequestMapping("/delete")
	@Transactional
	public ResponseEntity<String> delete(@RequestParam (name="fileId") String[] fileId) {
		// セッションに登録してあるloginUserDtoを取得
		loginUserDto = (LoginUserDto) session.getAttribute("loginUserDto");
		
		// 削除対象のファイルを削除する
		fileShareService.delete(fileId, loginUserDto.getUserId());
		
		// ファイル一覧画面を再表示する
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * ファイルの共有（工数が足りず未実装）
	 */
//	@RequestMapping("/share")
//	public String share() {
//		
//	}

	/**
	 * ファイルの共有解除（工数が足りず未実装）
	 */
//	@RequestMapping("/sharerelease")
//	public String shareRelease() {
//		
//	}
}

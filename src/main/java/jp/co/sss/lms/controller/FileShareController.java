package jp.co.sss.lms.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sss.lms.dto.FileShareDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.dto.ShareUserDto;
import jp.co.sss.lms.service.FileShareService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.LoggingUtil;
import jp.co.sss.lms.util.MessageUtil;

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

	@Autowired
	MessageUtil messageUtil;
	
	@Autowired
	LoggingUtil loggingUtil;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** ダウンロード可能なファイルのリスト */
	@Autowired
	public List<ShareUserDto> shareUserDtoList;

	/**
	 * 共有ファイルリストの表示<br>
	 * 
	 * @param userId
	 * @return ファイル共有画面
	 */
	@RequestMapping(value = "")
	public ResponseEntity<List<FileShareDto>> index(@RequestParam("userId") Integer userId) {

		// ファイルリストの取得
		List<FileShareDto> fileShareDtoList = fileShareService.getFileList(userId);

		return new ResponseEntity<>(fileShareDtoList, HttpStatus.OK);

	}

	/**
	 * ファイルのアップロード
	 * userIdを追加（東　茉奈）
	 * @param multipartFile
	 * @param userId
	 */
	@RequestMapping(value = "/upload", method = {RequestMethod.POST})
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile,
											@RequestParam(name="userId") List<Integer> userId) {
		
		boolean isUploadSuccess = true;
		
		//ファイルが空の場合
		if(multipartFile.isEmpty() || multipartFile.getSize() == 0 ) {
			
			String[] msgParam = {"ファイル"};
			
			// エラーメッセージを格納
			String message = messageUtil.getMessage(Constants.VALID_KEY_REQUIRED_SELECT,msgParam);
								
			// エラーメッセージをログに出力
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.error(sb.toString());
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
		//ファイルサイズが超過した場合
		if(multipartFile.getSize() >= 100000) {
			
			String[] msgParam = {"100000"};
			
			// エラーメッセージを格納
			String message = messageUtil.getMessage(Constants.VALID_KEY_MAXFILEAMOUNT_OVERLIMIT,msgParam);
									
			// エラーメッセージをログに出力
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.error(sb.toString());
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
		// 第二引数を追加（東　茉奈）
		isUploadSuccess = fileShareService.uploadFile(multipartFile, userId.get(0));
		
		//アップロードファイルが重複した場合
		if(!isUploadSuccess) {
			
			String[] msgParam = {multipartFile.getOriginalFilename()};
			
			// エラーメッセージを格納
			String message = messageUtil.getMessage(Constants.VALID_KEY_ALREADY_FILESHARE,msgParam);
						
			// エラーメッセージをログに出力
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.error(sb.toString());
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * ファイルの削除
	 * 変更　梶山卓
	 * @param fileIdList
	 *   fileIdをリスト型で取得
	 * @param userId
	 *   userIdをリスト型で取得
	 * @return ファイル共有画面
	 */
	@RequestMapping("/delete")
	@Transactional
	public ResponseEntity<String> delete(@RequestParam (name="fileId") List<String> fileIdList,
										@RequestParam(name="userId") List<Integer> userId) {
		//fileIdListがlist形式のためString配列に変換する
		String[] fileId = fileIdList.toArray(new String[fileIdList.size()]);
		// セッション登録未実装
		//loginUserDto = (LoginUserDto) session.getAttribute("loginUserDto");
		
		//*/
		// 削除対象のファイルを削除する
		fileShareService.delete(fileId,userId.get(0));
		// ファイル一覧画面を再表示する
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**  
	 * ダウンロード （一部未実装）
	 * 
	 * 2021/02/26　
	 * htmlやtxt、vueファイルのダウンロードは可能。
	 * web上のデータ(直リンク)やバイナリデータのDLは未実装。
	 * 
	 * @param fileId
	 */
	@RequestMapping(value="/download", method = RequestMethod.GET)
	public void download(@RequestParam("fileId") Integer fileId, HttpServletResponse response){
		String filePath = fileShareService.getDownloadUrl(fileId);
				
		// ファイルが見つからない場合
		if (filePath == null) {
			return;
		}	
		
		// ダウンロードファイルを出力
		fileShareService.doDownload(response, filePath);
		
		// ヘッダー情報をセット
		fileShareService.setHeaders(response, filePath);
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

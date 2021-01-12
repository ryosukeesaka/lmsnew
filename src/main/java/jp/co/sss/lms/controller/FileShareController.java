package jp.co.sss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.FileShareDto;
import jp.co.sss.lms.dto.ShareUserDto;
import jp.co.sss.lms.service.FileShareService;
import jp.co.sss.lms.util.LoginUserUtil;

/**
 * FileShareController<br>
 * ファイル共有コントローラ<br>
 * 
 * @author 杉本 将義
 */
@RestController
@RequestMapping("/fileshare/list")
public class FileShareController {

	@Autowired
	FileShareService fileShareService;
	@Autowired
	LoginUserUtil loginUserUtil;

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
	public ResponseEntity<List<FileShareDto>> index() {

		// ファイルリストの取得
		List<FileShareDto> fileShareDtoList = fileShareService.getFileList(loginUserUtil.getLoginUserId());

		return new ResponseEntity<>(fileShareDtoList, HttpStatus.OK);

	}

	/**
	 * ファイルのアップロード（工数が足りず未実装）
	 */
//	@RequestMapping("/upload")
//	public String upload() {
//
//	}

	/**
	 * ファイルの削除（工数が足りず未実装）
	 */
//	@RequestMapping("/delete")
//	public String delete() {
//		
//	}

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

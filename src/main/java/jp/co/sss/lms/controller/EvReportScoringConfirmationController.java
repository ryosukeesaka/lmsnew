package jp.co.sss.lms.controller;

// 未完成の処理で使用するimport文はコメントアウト
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.EvReportDto;
import jp.co.sss.lms.dto.EvReportFilteringDto;
//import jp.co.sss.lms.dto.EvReportScoringSearchDto;
//import jp.co.sss.lms.dto.EvReportScoringSearchResultDto;
import jp.co.sss.lms.form.EvReportScoringForm;
import jp.co.sss.lms.service.EvReportService;
import jp.co.sss.lms.service.PlaceService;
import jp.co.sss.lms.dto.PlaceDto;
import jp.co.sss.lms.util.Constants;

/**
 * EvReportScoringConfirmationController
 * 評価レポート採点一覧コントローラー
 * 
 * @author 東　茉奈
 *
 */
@RestController
@RequestMapping("/evReport/scoringConfirmation")
public class EvReportScoringConfirmationController {
	
	@Autowired
	EvReportService evReportService;
	
	@Autowired
	PlaceService placeService;
	
	@Autowired
	PlaceDto placeDto;
	
	@Autowired
	EvReportScoringForm evReportScoringForm;
	
	public List<EvReportDto> evReportDtoList = null;
	
	/**
	 * 初期表示処理
	 * 
	 * @param role　ログインユーザーの権限
	 * @param accountId　ログインユーザーの企業アカウントID
	 * @param placeId　ログインユーザーの会場ID
	 * @return evReportFilteringDto　検索条件表示用DTO
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<EvReportFilteringDto> index(@RequestParam("role") String role,
														@RequestParam("accountId") Integer accountId,
														@RequestParam("placeId") Integer placeId) {
		
		// 検索条件の選択肢を取得する
        init(role, accountId, placeId);
        
        EvReportFilteringDto evReportFilteringDto = new EvReportFilteringDto();
        
        // initメソッドで取得した評価レポートDTOリストを、検索条件表示用DTOに設定する。
        evReportFilteringDto.setEvReportDtoList(evReportDtoList);
        
        // 講師権限の場合
        if (Constants.CODE_VAL_ROLL_TEACHER.equals(role)) {
        	
        	// initメソッドで取得した会場DTOを、検索条件表示用DTOに設定する。
        	evReportFilteringDto.setPlaceDto(placeDto);
        	
        // 管理者権限の場合
        } else {
        	
        	// initメソッドで取得した会場DTOリストを、検索条件表示用DTOに設定する。
//        	evReportFilteringDto.setPlaceDtoList(placeDtoList);
        }
        return new ResponseEntity<>(evReportFilteringDto, HttpStatus.OK);
    }
	
//	/**
//	 * 検索結果表示処理（未完成）
//	 * 
//	 * @param role　ログインユーザーの権限
//	 * @param accountId　ログインユーザーの企業アカウントID
//	 * @param placeId　ログインユーザーの会場ID
//	 * @return EvReportScoringSearchResultDto　検索結果表示用DTO
//	 */
//	@RequestMapping(value = "/search", method = RequestMethod.POST)
//	public ResponseEntity<EvReportScoringSearchResultDto> search(@RequestParam("role") String role,
//																	@RequestParam("accountId") Integer accountId,
//																	@RequestParam("placeId") Integer placeId) {
//		
//		// 入力チェックエラーがあれば、フロントエンドにエラーメッセージを渡す。
//		// エラーメッセージ渡す用のメソッドを別で作成した方が良いかも
//		if (evReportScoringForm.getEvReportId().isEmpty()) {
//			
//			// エラーメッセージ
//			
//		}
//		
//		// 検索条件の選択肢を取得する
//        init(role, accountId, placeId);
//        
//        List<EvReportScoringSearchDto> evReportScoringList = new ArrayList<>();
//        
//        // 評価レポート採点状況検索DTOリストを取得
//        evReportScoringList = evReportService.getEvReportScoringList(evReportScoringForm);
//        
//        EvReportScoringSearchResultDto resultDto = new EvReportScoringSearchResultDto();
//        
//        // 取得した評価レポート採点状況検索DTOリストを、検索結果を画面に渡すためのDTOに設定する。
//        resultDto.setEvReportScoringSearchDtoList(evReportScoringList);
//        
//        if(evReportScoringList != null && evReportScoringList.size() > 0){
//        	
//        	boolean deliverableFlg = evReportScoringList.get(0).getDeliverableCount() > 0;
//        	boolean commentFlg = evReportScoringList.get(0).getCommentCount() > 0;
//        	
//        	// 成果物フラグ・コメントフラグを、検索結果を画面に渡すためのDTOに設定する。
//        	resultDto.setDeliverableFlg(deliverableFlg);
//        	resultDto.setCommentFlg(commentFlg);
//        }
//        
//        // 評価レポート採点一覧画面のフォームに、CSVダウンロード用の会場ID、評価レポートID、ユーザー名を設定する。
//        evReportScoringForm.setPlaceIdDownload(evReportScoringForm.getPlaceId());
//        evReportScoringForm.setEvReportIdDownload(evReportScoringForm.getEvReportId());
//        evReportScoringForm.setUserNameDownload(evReportScoringForm.getUserName());
//        
//        return new ResponseEntity<>(resultDto, HttpStatus.OK);
//    }
	
//	/**
//	 * 評価レポートCSVダウンロード処理（未完成）
//	 * 
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/download", method = RequestMethod.GET)
//	public void download() throws Exception {
//		evReportService.scoringCsvDownload(evReportScoringForm);
//    }
	
	/**
	 * 検索条件の選択肢取得処理
	 * 
	 * @param role　ログインユーザーの権限
	 * @param accountId　ログインユーザーの企業アカウントID
	 * @param placeId　ログインユーザーの会場ID
	 */
	public void init(String role, Integer accountId, Integer placeId) {
		
		// 講師権限の場合
        if (Constants.CODE_VAL_ROLL_TEACHER.equals(role)) {
        	
            if (placeId != null) {
            	
            	// 会場DTO取得
                placeDto = placeService.getPlaceDto(placeId);
                // 評価レポートDTOリスト取得
            	evReportDtoList = evReportService.getEvReportDtoList(accountId, placeId);
            	// 評価レポート採点一覧画面のフォームの会場IDに、会場DTOの会場IDを設定する。
            	evReportScoringForm.setPlaceId(Integer.toString(placeDto.getPlaceId()));
            }
        // 管理者権限の場合
        } else {
//            placeDtoList = placeService.getPlaceDtoList();
//            evReportDtoList = evReportService.getEvReportDtoList();
        }
    }
	
	//アップロード処理（未実装）
}

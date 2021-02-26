package jp.co.sss.lms.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.DailyReportDto;
import jp.co.sss.lms.dto.DeliverablesResultDto;
import jp.co.sss.lms.dto.ExamDto;
import jp.co.sss.lms.dto.ExamServiceExamDto;
import jp.co.sss.lms.dto.ExamServiceExamResultDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.dto.UserDetailDto;
import jp.co.sss.lms.dto.WorkbookDto;
import jp.co.sss.lms.entity.TDailyReportSubmit;
import jp.co.sss.lms.form.ExamPlayForm;
import jp.co.sss.lms.repository.ExamRepository;
import jp.co.sss.lms.service.DeliverableService;
import jp.co.sss.lms.service.ExamService;
import jp.co.sss.lms.service.TDailyReportSubmitService;
import jp.co.sss.lms.service.DailyReportSubmitService;
import jp.co.sss.lms.service.UserService;
import jp.co.sss.lms.util.ExcelUtil;

/**
 * ユーザー詳細コントローラー
 * 
 * @author naraoka
 */
@RestController
@RequestMapping("/user/detail")
public class UserDetailController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ExamService examService;
	@Autowired
	private DeliverableService deliverableService;
	@Autowired
	private DailyReportSubmitService dailyReportSubmitService;
	@Autowired
	private TDailyReportSubmitService tDailyReportSubmitService;
	
	/**
	 * 初期表示
	 * 
	 * @param model モデル
	 * @return ユーザー詳細画面
	 */
	@RequestMapping("")
	public ResponseEntity<Map<String, Object>> index(@RequestParam("lmsUserId") Integer lmsUserId) {
		
		
		UserDetailDto userDetailDto = userService.getUserDetailDto(lmsUserId);
		List<ExamDto> examDto = examService.getExamDto(lmsUserId);
		List<DeliverablesResultDto> deliverablesResultDto = deliverableService.getDeliverablesResultDto(lmsUserId);
		List<DailyReportDto> dailyReportDto = dailyReportSubmitService.getDailyReportDto(lmsUserId);

		// 上で取得した試験結果情報をもとに試験結果詳細情報の項目を設定
		Map<String, Object> map = new HashMap<>();
		map.put("userDetailDto", userDetailDto);
		map.put("examDto", examDto);
		map.put("deliverablesResultDto", deliverablesResultDto);
		map.put("dailyReportDto", dailyReportDto);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/download")
	public ResponseEntity<String> download(@RequestParam("dailyReportSubmitId") Integer dailyReportSubmitId, @RequestParam("dailyReportId") Integer dailyReportId, @RequestParam("lmsUserId") Integer lmsUserId,HttpServletResponse response) throws IOException {

		WorkbookDto workbookDto = this.getWorkbookDto(1,2,3);
		ExcelUtil.downloadBook(workbookDto, response);
		return null;
	}
	
	/**
	 * 日報をダウンロードするメソッド 日報情報をExcelでデータ化し、DTOを返却
	 */
	private WorkbookDto getWorkbookDto(@RequestParam("dailyReportSubmitId") Integer dailyReportSubmitId, @RequestParam("dailyReportId") Integer dailyReportId, @RequestParam("lmsUserId") Integer lmsUserId) {
		// TODO:2021/02/26 久岡 ユーザー詳細のDLボタン実装途中
		
		//      MDailyReport mDailyReport = mDailyReportService.findById(dailyReportId);
//		List<DailyReportDto> dailyReportDto = dailyReportSubmitService.getDailyReportDto(lmsUserId);
		
		
		// TODO:仮実装
//		DailyReportDto dailyReportDtoSingle = new DailyReportDto();
//		UserDetailDto userDetailDto = userService.getUserDetailDto(lmsUserId);
//		TDailyReportSubmit tDailyReportSubmit = tDailyReportSubmitService.findBySubmitId(dailyReportSubmitId);

//      UserFacade userFacade = SingletonS2Container.getComponent(UserFacade.class);
//      LmsUserDto lmsUserDto = userFacade.getLmsUserDto(userFacade.getMLmsUser(tDailyReportSubmit.lmsUserId));
		
		// TODO:どこかの設定ファイルからパスを取得する。一旦ベタ書き
		String commonFileDir = "src/main/resources/static/template";
		// TODO:どこかの設定ファイルからファイル名を取得する。一旦ベタ書き
		String excelFilePath = commonFileDir + "/" + "日報_テンプレート.xlsx";
		ExcelUtil excelUtil = new ExcelUtil(excelFilePath);

		// TODO:仮実装
//		BeanUtils.copyProperties(tDailyReportSubmit,dailyReportDtoSingle);
//		BeanUtils.copyProperties(tDailyReportSubmit.getMDailyReport(),dailyReportDtoSingle);
//		BeanUtils.copyProperties(tDailyReportSubmit.getMLmsUser(),dailyReportDtoSingle);
//		BeanUtils.copyProperties(tDailyReportSubmit.getTDailyReportSubmitDetailList(),dailyReportDtoSingle);
		
//        // 企業名
//        excelUtil.setVal("日間研修報告書",2,0, "てすとてすと");
		//ユーザー名
//		excelUtil.setVal("日間研修報告書",0,29,userDetailDto.getUserName());
        // 日付
//        excelUtil.setVal("日間研修報告書",5,6,dailyReportDtoSingle.getDate());
        // 本文
//        String content =  dailyReportDtoSingle.getContent();
//        excelUtil.setVal("日間研修報告書",8,6,dailyReportDtoSingle.getContent());
        
//      // 「日報作成詳細テーブル」と「日報詳細マスタ」のデータを設定する。
//      if (tDailyReportSubmit.tDailyReportSubmitDetailList != null) {
//          for (TDailyReportSubmitDetail tDailyReportSubmitDetail : tDailyReportSubmit.tDailyReportSubmitDetailList) {
//              for (MDailyReportDetail mDailyReportDetail : mDailyReport.mDailyReportDetailList) {
//                  if (mDailyReportDetail.fieldNum.equals(tDailyReportSubmitDetail.fieldNum)) {
//                      excelUtil.setVal(mDailyReport.sheetName,
//                                       mDailyReportDetail.row - 1,
//                                       mDailyReportDetail.clm - 1,
//                                       tDailyReportSubmitDetail.content);
//                  }
//              }
//          }
//      }
//      if (tDailyReportSubmit.tIntelligibilityList != null) {
//
//          Integer rowIntelFld = mDailyReport.rowIntelFld;
//          Integer rowIntel = mDailyReport.rowIntel;
//
//          for (TIntelligibility tIntelligibility : tDailyReportSubmit.tIntelligibilityList) {
//
//              if (tIntelligibility.fieldName != null
//                  && !tIntelligibility.fieldName.isEmpty()) {
//                  excelUtil.setVal(mDailyReport.sheetName, rowIntelFld - 1,
//                                   mDailyReport.clmIntelFld - 1,
//                                   tIntelligibility.fieldName);
//                  excelUtil.setVal(mDailyReport.sheetName, rowIntel - 1,
//                                   mDailyReport.clmIntel - 1,
//                                   tIntelligibility.fieldValue);
//                  rowIntelFld++;
//                  rowIntel++;
//              }
//          }
//      }
//      if (tDailyReportSubmit.tDailyReportFbList != null) {
//          //フィードバックコメントが2件以上ある時、フォーマットをコピーする
//          if(tDailyReportSubmit.tDailyReportFbList.size() > 1){
//              for(int i = 1; i < tDailyReportSubmit.tDailyReportFbList.size();i++){
//                  excelUtil.sheetCopy("フィードバックコメント", 6, 8, i);
//              }
//          }
//          for (int i = 0; i < tDailyReportSubmit.tDailyReportFbList.size();i++) {
//              TDailyReportFb tDailyReportFb = tDailyReportSubmit.tDailyReportFbList.get(i);
//              //指定位置にフィードバックしたユーザを設定
//              excelUtil.setVal("フィードバックコメント", 6+(i*2), 0,
//                               tDailyReportFb.mLmsUser.mUser.userName);
//              //指定位置にコメントを入力した日付を設定
//              excelUtil.setVal("フィードバックコメント", 6+(i*2), 17,
//                               tDailyReportFb.firstCreateDate);
//              //指定位置に入力内容を設定
//              excelUtil.setVal("フィードバックコメント", 7+(i*2), 10, tDailyReportFb.content);
//          }
//      }
		WorkbookDto workbookDto = new WorkbookDto();
		workbookDto.wb = excelUtil.getWb();
		workbookDto.wb.setForceFormulaRecalculation(true);
//        String[] fileName = mDailyReport.fileName.split("\\.");
			String[] fileName = "aaa.xlsx".split("\\."); // TODO:仮
        String bookName = "";
        for (int i = 0; i < fileName.length - 1; i++) {
            if (i > 0) {
                bookName += ".";
            }
            bookName += fileName[i];
        }
//
//        // 受講生名に全角スペースが使用されている場合は半角スペースに置き換える
//        String userName = lmsUserDto.userName.replaceAll("　", " ");
//        String companyName = lmsUserDto.companyDto.companyName.replaceAll("　", " ");

		String userName = "山田太郎"; // TODO:仮
		String companyName = "株式会社テスト"; // TODO:仮
		
//        workbookDto.wbName = bookName + "_"
//        + DateUtil.toString(tDailyReportSubmit.date, "YYYYMMdd") + "_"
//        + companyName + "_" + userName
//        + "." + fileName[fileName.length - 1];
//        return workbookDto;
		workbookDto.wbName = bookName + "_" + "20210220" + "_" + companyName + "_" + userName + "."
				+ fileName[fileName.length - 1]; // TODO:仮
		return workbookDto;
	}
}

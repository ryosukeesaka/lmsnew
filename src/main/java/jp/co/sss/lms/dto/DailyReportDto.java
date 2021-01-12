package jp.co.sss.lms.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DailyReportDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//日報ID
	private Integer dailyReportId;
	//日報詳細ID
	private Integer dailyReportSubmitId;
	//コースID
	private Integer courseId;
	//セクションID
	private Integer sectionId;
	//セクション・日報紐付けID
	private Integer sectionDailyReportId;
	//日報名
	private String reportName;
	//ファイル名
	private String fileName;
	//シート名
	private String sheetName;
	//社名出力行番号
	private Integer rowCompany;
	//社名出力列番号
	private Integer clmCompany;
	//ユーザー名出力行番号
	private Integer rowUser;
	//ユーザー名出力例番号
	private Integer clmUser;
	//日付出力行番号
	private Integer rowDate;
	//日付出力列番号
	private Integer clmDate;
	//学習理解度入力フラグ
	private Short intelligibilityFlg;
	//学習理解度項目数
	private Short intelligibilityFieldNum;
	//学習理解度数
	private Short intelligibilityNum;
	//理解度項目出力開始行番号
	private Integer rowIntelFld;
	//理解度項目出力開始列番号
	private Integer clmIntelFld;
	//理解度出力開始行番号
	private Integer rowIntel;
	//理解度出力開始列番号
	private Integer clmIntel;
	//ユーザー名
	private String userName;
	//日付
	private Date date;
	//日付(from)
	private String dateFrom;
	//日付(To)
	private String dateTo;
	//TODO フィールド名要確認
	private Integer fbCount;
	//TODO フィールド名要確認
	private Date lastFeedbackDate;
	//削除フラグ
	private short deleteFlg;
	//項目番号
	private List<Integer> fieldNum;
	//項目名
	private List<String> fieldName;
	//日付出力行
	private List<Integer> row;
	//日付出力列
	private List<Integer> clm;
	//入力チェック
	private List<String> requireCheck;
	//型
	private List<String> type;
	//範囲(From)
	private List<String> rangeFrom;
	//範囲(To)
	private List<String> rangeTo;
	//内容
	private List<String> content;
	//日報フィードバックDTO
	private List<DailyReportFbDto> dailyReportFbDtoList;
	//学習理解度DTO
	private List<IntelligibilityDto> intelligibilityDtoList;

	public Integer getDailyReportId() {
		return dailyReportId;
	}

	public void setDailyReportId(Integer dailyReportId) {
		this.dailyReportId = dailyReportId;
	}

	public Integer getDailyReportSubmitId() {
		return dailyReportSubmitId;
	}

	public void setDailyReportSubmitId(Integer dailyReportSubmitId) {
		this.dailyReportSubmitId = dailyReportSubmitId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getSectionDailyReportId() {
		return sectionDailyReportId;
	}

	public void setSectionDailyReportId(Integer sectionDailyReportId) {
		this.sectionDailyReportId = sectionDailyReportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Integer getRowCompany() {
		return rowCompany;
	}

	public void setRowCompany(Integer rowCompany) {
		this.rowCompany = rowCompany;
	}

	public Integer getClmCompany() {
		return clmCompany;
	}

	public void setClmCompany(Integer clmCompany) {
		this.clmCompany = clmCompany;
	}

	public Integer getRowUser() {
		return rowUser;
	}

	public void setRowUser(Integer rowUser) {
		this.rowUser = rowUser;
	}

	public Integer getClmUser() {
		return clmUser;
	}

	public void setClmUser(Integer clmUser) {
		this.clmUser = clmUser;
	}

	public Integer getRowDate() {
		return rowDate;
	}

	public void setRowDate(Integer rowDate) {
		this.rowDate = rowDate;
	}

	public Integer getClmDate() {
		return clmDate;
	}

	public void setClmDate(Integer clmDate) {
		this.clmDate = clmDate;
	}

	public Short getIntelligibilityFlg() {
		return intelligibilityFlg;
	}

	public void setIntelligibilityFlg(Short intelligibilityFlg) {
		this.intelligibilityFlg = intelligibilityFlg;
	}

	public Short getIntelligibilityFieldNum() {
		return intelligibilityFieldNum;
	}

	public void setIntelligibilityFieldNum(Short intelligibilityFieldNum) {
		this.intelligibilityFieldNum = intelligibilityFieldNum;
	}

	public Short getIntelligibilityNum() {
		return intelligibilityNum;
	}

	public void setIntelligibilityNum(Short intelligibilityNum) {
		this.intelligibilityNum = intelligibilityNum;
	}

	public Integer getRowIntelFld() {
		return rowIntelFld;
	}

	public void setRowIntelFld(Integer rowIntelFld) {
		this.rowIntelFld = rowIntelFld;
	}

	public Integer getClmIntelFld() {
		return clmIntelFld;
	}

	public void setClmIntelFld(Integer clmIntelFld) {
		this.clmIntelFld = clmIntelFld;
	}

	public Integer getRowIntel() {
		return rowIntel;
	}

	public void setRowIntel(Integer rowIntel) {
		this.rowIntel = rowIntel;
	}

	public Integer getClmIntel() {
		return clmIntel;
	}

	public void setClmIntel(Integer clmIntel) {
		this.clmIntel = clmIntel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public Integer getFbCount() {
		return fbCount;
	}

	public void setFbCount(Integer fbCount) {
		this.fbCount = fbCount;
	}

	public Date getLastFeedbackDate() {
		return lastFeedbackDate;
	}

	public void setLastFeedbackDate(Date lastFeedbackDate) {
		this.lastFeedbackDate = lastFeedbackDate;
	}

	public short getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(short deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public List<Integer> getFieldNum() {
		return fieldNum;
	}

	public void setFieldNum(List<Integer> fieldNum) {
		this.fieldNum = fieldNum;
	}

	public List<String> getFieldName() {
		return fieldName;
	}

	public void setFieldName(List<String> fieldName) {
		this.fieldName = fieldName;
	}

	public List<Integer> getRow() {
		return row;
	}

	public void setRow(List<Integer> row) {
		this.row = row;
	}

	public List<Integer> getClm() {
		return clm;
	}

	public void setClm(List<Integer> clm) {
		this.clm = clm;
	}

	public List<String> getRequireCheck() {
		return requireCheck;
	}

	public void setRequireCheck(List<String> requireCheck) {
		this.requireCheck = requireCheck;
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public List<String> getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(List<String> rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public List<String> getRangeTo() {
		return rangeTo;
	}

	public void setRangeTo(List<String> rangeTo) {
		this.rangeTo = rangeTo;
	}

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

	public List<DailyReportFbDto> getDailyReportFbDtoList() {
		return dailyReportFbDtoList;
	}

	public void setDailyReportFbDtoList(List<DailyReportFbDto> dailyReportFbDtoList) {
		this.dailyReportFbDtoList = dailyReportFbDtoList;
	}

	public List<IntelligibilityDto> getIntelligibilityDtoList() {
		return intelligibilityDtoList;
	}

	public void setIntelligibilityDtoList(List<IntelligibilityDto> intelligibilityDtoList) {
		this.intelligibilityDtoList = intelligibilityDtoList;
	}


}

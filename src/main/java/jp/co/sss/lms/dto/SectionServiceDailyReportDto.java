package jp.co.sss.lms.dto;

import java.util.Date;

/**
 * セクションサービスDTO
 * @author 眞鍋 美佳
 */
public class SectionServiceDailyReportDto {
	/** レポートID */
	private Integer dailyReportId;
	/** レポート提出ID */
	private Integer dailyReportSubmitId;
	/** レポート名 */
	private String reportName;
	/** 日付 */
	private Date date;
	
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

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}

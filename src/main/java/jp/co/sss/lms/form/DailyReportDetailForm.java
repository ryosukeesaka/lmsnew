package jp.co.sss.lms.form;

import java.util.Date;

/**
 * レポート詳細フォーム
 * 
 * レポート詳細初期表示
 * フィードバック登録
 * フィードバック削除
 * 
 * @author kawakubo
 *
 */

public class DailyReportDetailForm {
	/**日報提出ID*/
	private Integer dailyReportSubmitId;
	/**日報フィードバックコメントID*/
	private Integer dailyReportFbId;
	/**コメント*/
	private String content;
	/** 日報日付 */
	private Date date;

	public Integer getDailyReportSubmitId() {
		return dailyReportSubmitId;
	}

	public void setDailyReportSubmitId(Integer dailyReportSubmitId) {
		this.dailyReportSubmitId = dailyReportSubmitId;
	}

	public Integer getDailyReportFbId() {
		return dailyReportFbId;
	}

	public void setDailyReportFbId(Integer dailyReportFbId) {
		this.dailyReportFbId = dailyReportFbId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

package jp.co.sss.lms.dto;

import java.io.Serializable;
import java.util.Date;

public class DailyReportFbDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//日報フィードバックコメントID
	private Integer dailyReportFbId;
	//LMSユーザーID
	private Integer lmsUserId;
	//ユーザー名
	private String userName;
	//内容
	private String content;
	//投稿日時
	private Date date;

	public Integer getDailyReportFbId() {
		return dailyReportFbId;
	}

	public void setDailyReportFbId(Integer dailyReportFbId) {
		this.dailyReportFbId = dailyReportFbId;
	}

	public Integer getLmsUserId() {
		return lmsUserId;
	}

	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

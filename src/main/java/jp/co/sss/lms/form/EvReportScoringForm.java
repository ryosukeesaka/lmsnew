package jp.co.sss.lms.form;

import org.springframework.stereotype.Component;

/**
 * EvReportScoringForm
 * 評価レポート採点一覧画面のフォーム
 * 
 * @author 東　茉奈
 *
 */
@Component
public class EvReportScoringForm {
	
    /** 会場ID */
	private String placeId;

    /** 評価レポートID */
	private String evReportId;

    /** ユーザー名 */
	private String userName;

    /** 会場ID（CSVダウンロード用） */
	private String placeIdDownload;

    /** 評価レポートID（CSVダウンロード用） */
	private String evReportIdDownload;

    /** ユーザー名（CSVダウンロード用） */
	private String userNameDownload;

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getEvReportId() {
		return evReportId;
	}

	public void setEvReportId(String evReportId) {
		this.evReportId = evReportId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlaceIdDownload() {
		return placeIdDownload;
	}

	public void setPlaceIdDownload(String placeIdDownload) {
		this.placeIdDownload = placeIdDownload;
	}

	public String getEvReportIdDownload() {
		return evReportIdDownload;
	}

	public void setEvReportIdDownload(String evReportIdDownload) {
		this.evReportIdDownload = evReportIdDownload;
	}

	public String getUserNameDownload() {
		return userNameDownload;
	}

	public void setUserNameDownload(String userNameDownload) {
		this.userNameDownload = userNameDownload;
	}

}

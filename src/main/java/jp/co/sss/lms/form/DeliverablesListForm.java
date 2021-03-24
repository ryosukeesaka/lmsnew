package jp.co.sss.lms.form;

/**
 * 成果物一覧フォーム
 * 
 * @author uno
 */

public class DeliverablesListForm {
	
	 /** 成果物ID */
    public String deliverablesId;

    /** 会場ID */
    public String placeId;

    /** レコードチェックフラグ */
    public String checkFlgArr[];

    /** 成果物結果ID */
    public String deliverablesResultIdArr[];

    /** 採点 */
    public String scoreArr[];

    /** フィードバック */
    public String feedbackArr[];

	public String getDeliverablesId() {
		return deliverablesId;
	}

	public void setDeliverablesId(String deliverablesId) {
		this.deliverablesId = deliverablesId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String[] getCheckFlgArr() {
		return checkFlgArr;
	}

	public void setCheckFlgArr(String[] checkFlgArr) {
		this.checkFlgArr = checkFlgArr;
	}

	public String[] getDeliverablesResultIdArr() {
		return deliverablesResultIdArr;
	}

	public void setDeliverablesResultIdArr(String[] deliverablesResultIdArr) {
		this.deliverablesResultIdArr = deliverablesResultIdArr;
	}

	public String[] getScoreArr() {
		return scoreArr;
	}

	public void setScoreArr(String[] scoreArr) {
		this.scoreArr = scoreArr;
	}

	public String[] getFeedbackArr() {
		return feedbackArr;
	}

	public void setFeedbackArr(String[] feedbackArr) {
		this.feedbackArr = feedbackArr;
	}
    
}

package jp.co.sss.lms.dto;

/**
 * 評価レポート採点状況検索DTO
 * 
 * @author 東　茉奈
 *
 */
public class EvReportScoringSearchDto {
	
	/** ユーザID */
	private Integer lmsUserId;

    /** 受講生名 */
	private String userName;

    /** 企業名 */
	private String companyName;

    /** 企業住所 */
	private String companyAddress;

    /** 詳細マスタ コメント件数 */
	private Integer commentCount;

    /** 詳細マスタ 成果物件数 */
	private Integer deliverableCount;

    /** コメント評価件数 */
	private Integer commentScoringCount;

    /** 成果物評価件数 */
	private Integer deliverableScoringCount;

    /** コメント評価ステータス */
	private String commentStatus;

    /** 成果物評価ステータス */
	private String deliverableStatus;

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getDeliverableCount() {
		return deliverableCount;
	}

	public void setDeliverableCount(Integer deliverableCount) {
		this.deliverableCount = deliverableCount;
	}

	public Integer getCommentScoringCount() {
		return commentScoringCount;
	}

	public void setCommentScoringCount(Integer commentScoringCount) {
		this.commentScoringCount = commentScoringCount;
	}

	public Integer getDeliverableScoringCount() {
		return deliverableScoringCount;
	}

	public void setDeliverableScoringCount(Integer deliverableScoringCount) {
		this.deliverableScoringCount = deliverableScoringCount;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	public String getDeliverableStatus() {
		return deliverableStatus;
	}

	public void setDeliverableStatus(String deliverableStatus) {
		this.deliverableStatus = deliverableStatus;
	}

}

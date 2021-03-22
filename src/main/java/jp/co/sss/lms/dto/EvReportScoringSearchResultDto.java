package jp.co.sss.lms.dto;

import java.util.List;

/**
 * 評価レポート採点一覧画面の、検索結果を画面に渡すためのDTO
 * 
 * @author 東　茉奈
 *
 */
public class EvReportScoringSearchResultDto {

	/** 評価レポート採点状況検索DTOリスト */
	private List<EvReportScoringSearchDto> evReportScoringSearchDtoList;
	
	/** 成果物フラグ */
	private boolean deliverableFlg;
	
	/** コメントフラグ */
	private boolean commentFlg;

	public List<EvReportScoringSearchDto> getEvReportScoringSearchDtoList() {
		return evReportScoringSearchDtoList;
	}

	public void setEvReportScoringSearchDtoList(List<EvReportScoringSearchDto> evReportScoringSearchDtoList) {
		this.evReportScoringSearchDtoList = evReportScoringSearchDtoList;
	}

	public boolean isDeliverableFlg() {
		return deliverableFlg;
	}

	public void setDeliverableFlg(boolean deliverableFlg) {
		this.deliverableFlg = deliverableFlg;
	}

	public boolean isCommentFlg() {
		return commentFlg;
	}

	public void setCommentFlg(boolean commentFlg) {
		this.commentFlg = commentFlg;
	}
}

package jp.co.sss.lms.dto;


import java.util.List;

import jp.co.sss.lms.entity.MEvReport;

public class EvReportDto {

    public Integer evReportId;
    public Integer evReportResultId;
    public String reportName;
    public String fileName;
    //deleteFlgの追加 t-murakami
    public Integer deleteFlg;
    public List<EvReportDetailDto> evReportDetailDtoList;
    public List<MEvReport> ratedEvReportResultList;
    public List<MEvReport> unratedEvReportList;

    //評価レポート一覧の追加Ph2 k-kinjo

    /** コース名 */
    public String courseName;

    /** 開講日 */
    public String openTime;

    /** 閉講日 */
    public String closeTime;

    /** 会場名 */
    public String placeName;

    /** 受講生名 */
    public String userName;

    /** 講師名 */
    public String tName ;

    /** 公開フラグ */
    public Short publishedFlg;

    /** 会場備考 */
    public String placeNote;

    /** 企業名 */
    public String companyName;

    /** ロール */
    public String role;

    /** 会場ID */
    public Integer placeId;

    /** ユーザID */
    public Integer lmsUserId;



}
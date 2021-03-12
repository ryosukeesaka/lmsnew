package jp.co.sss.lms.dto;

import java.util.Date;

public class DeliverableDto {

    /** 成果物ID */
    public Integer deliverableId;

    /** ファイルID */
    public String fileId;

    /** ファイル名 */
    public String fileName;

    /** ユーザー名 */
    public String userName;

    /** 成果物提出日時 */
    public Date uploadDate;

    /** 状態 */
    public Short status;

    /** コース名 */
    public String courseName;

    /** コースID */
    public Integer courseId;

    /** プロジェクト名 */
    public String projectName;

    /** プロジェクトID */
    public Integer projectId;

    /** チケット名 */
    public String ticketName;

    /** チケットID */
    public Integer ticketId;

    /** フィードバックの件数 */
    public Integer feedbackCount;
}

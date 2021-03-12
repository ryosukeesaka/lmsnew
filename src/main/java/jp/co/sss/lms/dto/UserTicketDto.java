package jp.co.sss.lms.dto;

public class UserTicketDto {

    /** チケットID */
    public Integer ticketId;

    /** チケット名 */
    public String ticketName;

    /** ステータス */
    public Short status;

    /** 子課題数 */
    public Integer childTicketCount;

    /** 子課題完了数 */
    public Integer childTicketCompleteCount;

}

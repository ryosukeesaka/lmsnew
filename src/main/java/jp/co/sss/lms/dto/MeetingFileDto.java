package jp.co.sss.lms.dto;

public class MeetingFileDto {

    public Integer meetingFileId;
    public String fileName;
    public String sheetName;
    public Integer rowCompany;
    public Integer clmCompany;
    public Integer rowUser;
    public Integer clmUser;
    public Integer rowDate;
    public Integer clmDate;
    public Integer rowMeeting;
    public Integer clmMeeting;
    //deleteFlgの追加 t-murakami
    public Short deleteFlg;
}

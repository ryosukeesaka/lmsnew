package jp.co.sss.lms.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingDto {

    public String meetingId;
    public String lmsUserId;
    public Date firstCreateDate;
    public List<MeetingDetailDto> meetingDetailDtoList = new ArrayList<MeetingDetailDto>();
    public Integer deleteFlg;
}

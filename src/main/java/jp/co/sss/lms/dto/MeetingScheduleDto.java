package jp.co.sss.lms.dto;

import java.util.Date;

public class MeetingScheduleDto {
	
	/** ミーティングスケジュールID */
    public Integer meetingScheduleId;

    /**  邱ｨ髮�譛滄剞 */
    public Date editLimit;

    /**  髱｢隲�髢句ｧ区律 */
    public Date meetingOpenDate;

    /**  髱｢隲�邨ゆｺ�譌･ */
    public Date meetingCloseDate;

    /**  逶ｮ逧� */
    public String purpose;

    /** 蟇ｾ雎｡莨壼�ｴID */
    public Integer meetingPlaceId;

    /** 莨壼�ｴ蜷� */
    public String placeName;

    /** 蜈ｬ髢九ヵ繝ｩ繧ｰ */
    public Short publishedFlg;

}

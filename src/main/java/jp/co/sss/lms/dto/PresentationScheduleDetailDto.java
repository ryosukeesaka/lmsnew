package jp.co.sss.lms.dto;

import java.io.Serializable;

public class PresentationScheduleDetailDto implements Serializable {

    private static final long serialVersionUID = 2521387102548885569L;

    /** 成果報告会スケジュール詳細ID */
    public Integer presentationScheduleDetailId;
    /** 成果報告会スケジュールID */
    public Integer presentationScheduleId;
    /** 開催時刻 */
    public String presentationTime;
    /** 用途 */
    public String purpose;
    /**
     * 成果報告会スケジュール詳細IDを取得します。
     * @return 成果報告会スケジュール詳細ID
     */
    public Integer getPresentationScheduleDetailId() {
        return presentationScheduleDetailId;
    }
    /**
     * 成果報告会スケジュールIDを取得します。
     * @return 成果報告会スケジュールID
     */
    public Integer getPresentationScheduleId() {
        return presentationScheduleId;
    }
    /**
     * 開催時刻を取得します。
     * @return 開催時刻
     */
    public String getPresentationTime() {
        return presentationTime;
    }
    /**
     * 用途を取得します。
     * @return 用途
     */
    public String getPurpose() {
        return purpose;
    }
}

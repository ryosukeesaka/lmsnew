package jp.co.sss.lms.dto;

import java.util.Date;
import java.util.Map;

public class ExamResultDto {

    /** 試験ID */
    public Integer examId;

    /** 試験結果ID */
    public Integer examResultId;

    /** 試験・セクション紐づけID */
    public Integer examSectionId;

    /** 試験名 */
    public String examName;

    /** 試験実施日付 */
    public Date date;

    /** 点数 */
    public Short score;

    /**  */
    public Map<String, Short> genreScoreMap;

    /** 試験DTO */
    public ExamDto examDto;
}

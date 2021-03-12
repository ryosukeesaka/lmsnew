package jp.co.sss.lms.dto;

import java.util.Date;
import java.util.List;

public class SectionDto {

    /** セクションID */
    public Integer sectionId;

    /** セクション名 */
    public String sectionName;

    /** セクション説明 */
    public String sectionDescription;

    /** セクションの日付 */
    public Date date;

    /** セクションに紐づくファイル */
    public List<FileDto> fileList;

    /** セクションに紐づく試験 */
    public List<ExamDto> examList;

    /** セクションに紐づく日報 */
    public List<DailyReportDto> dailyReportList;

    /** セクションに紐づく成果物 */
    public List<DeliverablesSectionDto> deliverablesSectionDtoList;

    /** コースID */
    public Integer courseId;

    public String maxFileSize;
}

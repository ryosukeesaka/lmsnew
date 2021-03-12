package jp.co.sss.lms.dto;

import java.util.List;

public class DeliverablesSectionDto {

    /** 成果物・セクション紐付けID */
    public Integer deliverablesSectionId;

    /** 提出期限 */
    public String submissionDeadline;

    /** 成果物Dto */
    public DeliverablesDto deliverablesDto;

    /** 成果物結果Dto */
    public List<DeliverablesResultDto> deliverablesResultDtoList;

}

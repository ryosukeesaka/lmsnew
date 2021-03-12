package jp.co.sss.lms.dto;

import java.util.Date;
import java.util.List;

public class CategoryDto {

    /** カテゴリID */
    public Integer categoryId;

    /** カテゴリ名 */
    public String categoryName;

    /** カテゴリ説明 */
    public String categoryDescription;

    /** セクションリスト */
    public List<SectionDto> sectionDtoList;

    /** カテゴリ開始日時 */
    public Date startDate;

    /** カテゴリ終了日 */
    public Date endDate;
}

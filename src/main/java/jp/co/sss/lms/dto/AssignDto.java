package jp.co.sss.lms.dto;

import java.io.Serializable;


public class AssignDto {

    /** */
    private static final long serialVersionUID = 1L;

    /** エントリー年月 */
    public String period;

    /** 会場アサインID */
    public int placeAssignId;

    /** エントリー人数 */
    public int applicantAmount;

    /** コース情報 **/
    public CourseServiceCourseListDto courseServiceCourseListDto;

    /** 企業DTO */
    public CompanyDto companyDto;

    /** 会場DTO */
    public PlaceDto placeDto;

    /**
     * エントリー年月を取得します。
     * @return エントリー年月
     */
    public String getPeriod() {
        return period;
    }

    /**
     * 会場アサインIDを取得します。
     * @return 会場アサインID
     */
    public int getPlaceAssignId() {
        return placeAssignId;
    }

    /**
     * エントリー人数を取得します。
     * @return エントリー人数
     */
    public int getApplicantAmount() {
        return applicantAmount;
    }

    /**
     * コース情報 *を取得します。
     * @return コース情報 *
     */
    public CourseServiceCourseListDto getCourseServiceListDtoDto() {
        return courseServiceCourseListDto;
    }

    /**
     * 企業DTOを取得します。
     * @return 企業DTO
     */
    public CompanyDto getCompanyDto() {
        return companyDto;
    }

    /**
     * 会場DTOを取得します。
     * @return 会場DTO
     */
    public PlaceDto getPlaceDto() {
        return placeDto;
    }



}

package jp.co.sss.lms.dto;

import java.util.ArrayList;
import java.util.List;


import jp.co.sss.lms.dto.LmsUserDtoBase;

public class LmsUserDto extends LmsUserDtoBase {

    private static final long serialVersionUID = 1L;

    /** ログインID */
    public String loginId;

    /** コース名 */
    public String courseName;

    /** コースID */
    public Integer couseId;

    /** コース種別 */
    public Short courseType;

    /** 研修を通じてどのようになってほしいか */
    public String hopeViaTraning;

    /** プログラム経験 */
    public Short programmingExperience;

    /** 企業出力DTO */
    public CompanyDto companyDto;

    /** 会場出力DTO */
    public PlaceDto placeDto;

    /** 試験結果出力DTO */
    public ExamResultDto examResultDto;

    /** ユーザーマスタDTO */
    public MUserDto mUserDto;

    /** レポートDTO */
    public List<DailyReportDto> dailyReportDtoList = new ArrayList<>();

    /** 試験DTO */
    public List<ExamResultDto> examResultDtoList = new ArrayList<>();

    /** 評価レポートDTO */
    public List<EvReportDto> evReportDtoList = new ArrayList<>();

    /** 面談記録DTO */
    public List<MeetingDto> meetingDtoList = new ArrayList<>();

    public MeetingFileDto meetingFileDto;

    /** 課題DTO */
    public List<UserTicketDto> ticketDtoList = new ArrayList<>();

    /** 成果物DTO */
    public List<DeliverableDto> deliverableDtoList = new ArrayList<>();

    /** ユーザーマスタDTO */
    public List<MUserDto> mUserDtoList = new ArrayList<>();

    /** 習得スキルリスト */
    public List<SkillDto> skillDtoList = new ArrayList<>();

    /** 助成金カテゴリID */
    public Integer subsidyCategoryId;

    /** 助成金カテゴリ名 */
    public String subsidyCategoryName;

    /** ユーザー・企業紐付けID */
    public Integer userCompanyId;

    /**
     * ログインIDを取得します。
     * @return ログインID
     */
    public String getLoginId() {
        return loginId;
    }
    
    public CompanyDto getCompanyDto() {
    	return companyDto;
    }
    
    public void setCompanyDto(CompanyDto companyDto) {
    	this.companyDto = companyDto;
    }
    
    public void setPlaceDto(PlaceDto placeDto) {
    	this.placeDto = placeDto;
    }

}

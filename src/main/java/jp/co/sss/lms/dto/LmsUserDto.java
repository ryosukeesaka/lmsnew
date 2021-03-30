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
    
    /** 会場出力Dto */
    public List<PlaceDto> placeDtoList = new ArrayList<>();
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
    
    /** ユーザの企業名	 */
    public String companyName;
    
    /** 企業Id */
    public Integer companyId;
    public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCouseId() {
		return couseId;
	}
	public void setCouseId(Integer couseId) {
		this.couseId = couseId;
	}
	public Short getCourseType() {
		return courseType;
	}
	public void setCourseType(Short courseType) {
		this.courseType = courseType;
	}
	public String getHopeViaTraning() {
		return hopeViaTraning;
	}
	public void setHopeViaTraning(String hopeViaTraning) {
		this.hopeViaTraning = hopeViaTraning;
	}
	public Short getProgrammingExperience() {
		return programmingExperience;
	}
	public void setProgrammingExperience(Short programmingExperience) {
		this.programmingExperience = programmingExperience;
	}
	public CompanyDto getCompanyDto() {
		return companyDto;
	}
	public void setCompanyDto(CompanyDto companyDto) {
		this.companyDto = companyDto;
	}
	public PlaceDto getPlaceDto() {
		return placeDto;
	}
	public void setPlaceDto(PlaceDto placeDto) {
		this.placeDto = placeDto;
	}
	public ExamResultDto getExamResultDto() {
		return examResultDto;
	}
	public void setExamResultDto(ExamResultDto examResultDto) {
		this.examResultDto = examResultDto;
	}
	public MUserDto getmUserDto() {
		return mUserDto;
	}
	public void setmUserDto(MUserDto mUserDto) {
		this.mUserDto = mUserDto;
	}
	public List<DailyReportDto> getDailyReportDtoList() {
		return dailyReportDtoList;
	}
	public void setDailyReportDtoList(List<DailyReportDto> dailyReportDtoList) {
		this.dailyReportDtoList = dailyReportDtoList;
	}
	public List<ExamResultDto> getExamResultDtoList() {
		return examResultDtoList;
	}
	public void setExamResultDtoList(List<ExamResultDto> examResultDtoList) {
		this.examResultDtoList = examResultDtoList;
	}
	public List<EvReportDto> getEvReportDtoList() {
		return evReportDtoList;
	}
	public void setEvReportDtoList(List<EvReportDto> evReportDtoList) {
		this.evReportDtoList = evReportDtoList;
	}
	public List<MeetingDto> getMeetingDtoList() {
		return meetingDtoList;
	}
	public void setMeetingDtoList(List<MeetingDto> meetingDtoList) {
		this.meetingDtoList = meetingDtoList;
	}
	public MeetingFileDto getMeetingFileDto() {
		return meetingFileDto;
	}
	public void setMeetingFileDto(MeetingFileDto meetingFileDto) {
		this.meetingFileDto = meetingFileDto;
	}
	public List<UserTicketDto> getTicketDtoList() {
		return ticketDtoList;
	}
	public void setTicketDtoList(List<UserTicketDto> ticketDtoList) {
		this.ticketDtoList = ticketDtoList;
	}
	public List<DeliverableDto> getDeliverableDtoList() {
		return deliverableDtoList;
	}
	public void setDeliverableDtoList(List<DeliverableDto> deliverableDtoList) {
		this.deliverableDtoList = deliverableDtoList;
	}
	public List<MUserDto> getmUserDtoList() {
		return mUserDtoList;
	}
	public void setmUserDtoList(List<MUserDto> mUserDtoList) {
		this.mUserDtoList = mUserDtoList;
	}
	public List<SkillDto> getSkillDtoList() {
		return skillDtoList;
	}
	public void setSkillDtoList(List<SkillDto> skillDtoList) {
		this.skillDtoList = skillDtoList;
	}
	public Integer getSubsidyCategoryId() {
		return subsidyCategoryId;
	}
	public void setSubsidyCategoryId(Integer subsidyCategoryId) {
		this.subsidyCategoryId = subsidyCategoryId;
	}
	public String getSubsidyCategoryName() {
		return subsidyCategoryName;
	}
	public void setSubsidyCategoryName(String subsidyCategoryName) {
		this.subsidyCategoryName = subsidyCategoryName;
	}
	public Integer getUserCompanyId() {
		return userCompanyId;
	}
	public void setUserCompanyId(Integer userCompanyId) {
		this.userCompanyId = userCompanyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
     * ログインIDを取得します。
     * @return ログインID
     */
    public String getLoginId() {
        return loginId;
    }
	public List<PlaceDto> getPlaceDtoList() {
		return placeDtoList;
	}
	public void setPlaceDtoList(List<PlaceDto> placeDtoList) {
		this.placeDtoList = placeDtoList;
	}

}

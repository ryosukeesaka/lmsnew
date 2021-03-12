package jp.co.sss.lms.dto;

import java.io.Serializable;


public class PresentationCompanyDto implements Serializable{

	private static final long serialVersionUID =1L;

	/**対象企業ID**/
	public Integer presentationCompanyId;
	/**企業ID**/
	public Integer companyId;
	/**チームID**/
    public Integer presentationTeamId;
    /**参加可能フラグ**/
    public Short joinAbleFlg;
    /**参加人数**/
    public Integer joinAmount;
    /**参加者名**/
    public String joinName;
    /**成果報告会チームDTO**/
    public PresentationTeamDto presentationTeamDto;
    /**企業DTO**/
//    public CompanyDto companyDto;
    /**成果報告会スケジュール詳細DTO**/
    public PresentationScheduleDetailDto presentationScheduleDetailDto;
}

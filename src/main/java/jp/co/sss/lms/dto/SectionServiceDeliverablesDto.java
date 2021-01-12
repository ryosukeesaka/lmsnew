package jp.co.sss.lms.dto;

public class SectionServiceDeliverablesDto {
	
	/** 成果物ID */
    private Integer deliverablesId;

    /** 成果物名 */
    private String deliverablesName;

	public Integer getDeliverablesId() {
		return deliverablesId;
	}

	public void setDeliverablesId(Integer deliverablesId) {
		this.deliverablesId = deliverablesId;
	}

	public String getDeliverablesName() {
		return deliverablesName;
	}

	public void setDeliverablesName(String deliverablesName) {
		this.deliverablesName = deliverablesName;
	}
    
}

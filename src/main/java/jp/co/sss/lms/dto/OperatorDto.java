package jp.co.sss.lms.dto;

public class OperatorDto {
	
	/** オペレーターID */
    private Integer operatorId;
    /** オペレーター名 */
    private String operatorName;
    /** 連携用URL */
    private String url;
    /** オペレーター種別 */
    private Short operatorType;
    
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Short getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(Short operatorType) {
		this.operatorType = operatorType;
	}
    
}

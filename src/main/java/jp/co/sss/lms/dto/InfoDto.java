package jp.co.sss.lms.dto;

import java.util.Date;

public class InfoDto {
	
	// お知らせID
    private Integer infoId;
    // 内容
    private String content;
    // 更新日時
    private Date lastModifiedDate;
    
	public Integer getInfoId() {
		return infoId;
	}
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}

package jp.co.sss.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "m_place")
public class MPlace {
	@Id
	private Integer placeId;
	@Column
	private String placeName;
	@Column
	private String placeDescription;
	@Column
	private Short supportAvailable;
	@Column
	private Integer accountId;
	@Column
	private Short deleteFlg;
	@Column
	private Integer firstCreateUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstCreateDate;
	@Column
	private Integer lastModifiedUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	@Column
	private Integer seatingCapacity;
	@Column
	private String placeNote;
	@Column
	private Short hiddenFlg;
	@OneToMany(mappedBy = "mPlace")
    private List<TUserPlace> tUserPlaceList;
	
	public Integer getPlaceId() {
		return placeId;
	}
	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceDescription() {
		return placeDescription;
	}
	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}
	public Short getSupportAvailable() {
		return supportAvailable;
	}
	public void setSupportAvailable(Short supportAvailable) {
		this.supportAvailable = supportAvailable;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Short getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(Short deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public Integer getFirstCreateUser() {
		return firstCreateUser;
	}
	public void setFirstCreateUser(Integer firstCreateUser) {
		this.firstCreateUser = firstCreateUser;
	}
	public Date getFirstCreateDate() {
		return firstCreateDate;
	}
	public void setFirstCreateDate(Date firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}
	public Integer getLastModifiedUser() {
		return lastModifiedUser;
	}
	public void setLastModifiedUser(Integer lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public String getPlaceNote() {
		return placeNote;
	}
	public void setPlaceNote(String placeNote) {
		this.placeNote = placeNote;
	}
	public Short getHiddenFlg() {
		return hiddenFlg;
	}
	public void setHiddenFlg(Short hiddenFlg) {
		this.hiddenFlg = hiddenFlg;
	}
	public List<TUserPlace> getTUserPlaceList() {
		return tUserPlaceList;
	}
	public void setTUserPlaceList(List<TUserPlace> tUserPlaceList) {
		this.tUserPlaceList = tUserPlaceList;
	}
	
}

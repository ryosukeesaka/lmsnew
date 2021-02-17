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

/**
 * MPlaceエンティティクラス
 *@author 江坂亮典
 */

@Entity
@Table(name = "m_place")
public class MPlace {
	
	/** 会場ID */
	@Id
	private Integer placeId;
	
	/** 会場名 */
	@Column
	private String placeName;
	
	/** 会場説明 */
	@Column
	private String placeDescription;
	
	/**　利用可能なサポート */
	@Column
	private Short supportAvailable;
	
	/** アカウントID */
	@Column
	private Integer accountId;
	
	/** 論理削除 */
	@Column
	private Short deleteFlg;
	
	/** 初回作成者 */
	@Column
	private Integer firstCreateUser;
	
	/** 作成日 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstCreateDate;
	
	/** 最終更新者 */
	@Column
	private Integer lastModifiedUser;
	
	/** 最終更新日時 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	/** 座席定員 */
	@Column
	private Integer seatingCapacity;
	
	/** 備考 */
	@Column
	private String placeNote;
	
	/** 非表示フラグ */
	@Column
	private Short hiddenFlg;
	
	/** リレーションテーブル */
	@OneToMany(mappedBy = "mPlace")
    private List<TUserPlace> tUserPlaceList;
	
	/** リレーションテーブル */
	@OneToMany(mappedBy = "mPlace")
	private List<TMeetingPlace> tMeetingPlaceList;

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

	public List<TUserPlace> gettUserPlaceList() {
		return tUserPlaceList;
	}

	public void settUserPlaceList(List<TUserPlace> tUserPlaceList) {
		this.tUserPlaceList = tUserPlaceList;
	}

	public List<TMeetingPlace> gettMeetingPlaceList() {
		return tMeetingPlaceList;
	}

	public void settMeetingPlaceList(List<TMeetingPlace> tMeetingPlaceList) {
		this.tMeetingPlaceList = tMeetingPlaceList;
	}
	
	
	
}

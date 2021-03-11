package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "t_presentation_place")
public class TPresentationPlace {

	/** 成果報告会対象会場ID */
	@Id
	//@Column(name = "presentation_place_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(name = "generator", allocationSize = 1)
	private Integer presentationPlaceId;

	/** 削除フラグ */
	@Column
	private Short deleteFlg;

	/** 初回作成者 */
	@Column
	private Integer firstCreateUser;

	/** 初回作成日時 */
	@Column
	private Timestamp firstCreateDate;

	/** 最終更新者 */
	@Column
	private Integer lastModifiedUser;

	/** 最終更新日時 */
	@Column
	private Timestamp lastModifiedDate;

	/** チーム確定フラグ */
	@Column
	private Short confirmFlg;

	/** 公開フラグ */
	@Column
	private Short publishedFlg;
	
	//@Column
    //public Integer placeId;

	@ManyToOne
	@JoinColumn(name = "place_id", referencedColumnName = "placeId")
	private MPlace mPlace;

	@ManyToOne
	@JoinColumn(name = "presentation_schedule_id", referencedColumnName = "presentationScheduleId")
	public MPresentationSchedule mPresentationSchedule;

	@OneToMany(mappedBy ="tPresentationPlace")
	public  List<MPresentationTeam> mPresentationTeamList;

	public Integer getPresentationPlaceId() {
		return presentationPlaceId;
	}

	public void setPresentationPlaceId(Integer presentationPlaceId) {
		this.presentationPlaceId = presentationPlaceId;
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

	public Timestamp getFirstCreateDate() {
		return firstCreateDate;
	}

	public void setFirstCreateDate(Timestamp firstCreateDate) {
		this.firstCreateDate = firstCreateDate;
	}

	public Integer getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(Integer lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Short getConfirmFlg() {
		return confirmFlg;
	}

	public void setConfirmFlg(Short confirmFlg) {
		this.confirmFlg = confirmFlg;
	}

	public Short getPublishedFlg() {
		return publishedFlg;
	}

	public void setPublishedFlg(Short publishedFlg) {
		this.publishedFlg = publishedFlg;
	}

	public MPlace getMPlace() {
		return mPlace;
	}

	public void setMPlace(MPlace mPlace) {
		this.mPlace = mPlace;
	}

	public MPresentationSchedule getMPresentationSchedule() {
		return mPresentationSchedule;
	}

	public void setMPresentationSchedule(MPresentationSchedule mPresentationSchedule) {
		this.mPresentationSchedule = mPresentationSchedule;
	}

	public List<MPresentationTeam> getMPresentationTeamList() {
		return mPresentationTeamList;
	}

	public void setMPresentationTeamList(List<MPresentationTeam> mPresentationTeamList) {
		this.mPresentationTeamList = mPresentationTeamList;
	}

}

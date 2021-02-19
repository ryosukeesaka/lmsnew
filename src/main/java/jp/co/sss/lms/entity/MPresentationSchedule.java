package jp.co.sss.lms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 成果報告会スケジュールマスタテーブルエンティティクラス
 * 
 * @author Yuna Kato
 */

@Entity
@Table(name="m_presentation_schedule")
public class MPresentationSchedule {

	/** 成果報告会スケジュールID */
	@Id
	@Column(name = "presentation_schedule_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
			name = "generator",
			table="id_generator",
			pkColumnName = "pk",
			valueColumnName = "value",
			pkColumnValue = "M_PRESENTATION_SCHEDULE_PRESENTATION_SCHEDULE_ID",
			allocationSize = 1)
	private Integer presentationScheduleId;

	/** 予約編集期限 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date editLimit;

	/** 成果報告会開催日 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date presentationDate;

	/** 用途 */
	@Column
	private String purpose;

	/** 削除フラグ */
	@Column
	private short deleteFlg;

	/** 初回作成者 */
	@Column
	private Integer firstCreateUser;

	/** 初回作成日時 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstCreateDate;

	/** 最終更新者 */
	@Column
	private Integer lastModifiedUser;

	/** 最終更新日時 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@OneToMany(mappedBy = "mPresentationSchedule")
	private List<TPresentationPlace> tPresentationPlaceList;

	@OneToMany(mappedBy ="mPresentationSchedule")
	private  List<MPresentationScheduleDetail> mPresentationScheduleDetailList ;

	public Integer getPresentationScheduleId() {
		return presentationScheduleId;
	}

	public void setPresentationScheduleId(Integer presentationScheduleId) {
		this.presentationScheduleId = presentationScheduleId;
	}

	public Date getEditLimit() {
		return editLimit;
	}

	public void setEditLimit(Date editLimit) {
		this.editLimit = editLimit;
	}

	public Date getPresentationDate() {
		return presentationDate;
	}

	public void setPresentationDate(Date presentationDate) {
		this.presentationDate = presentationDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public short getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(short deleteFlg) {
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

	public List<TPresentationPlace> getTPresentationPlaceList() {
		return tPresentationPlaceList;
	}

	public void setTPresentationPlaceList(List<TPresentationPlace> tPresentationPlaceList) {
		this.tPresentationPlaceList = tPresentationPlaceList;
	}

	public List<MPresentationScheduleDetail> getMPresentationScheduleDetailList() {
		return mPresentationScheduleDetailList;
	}

	public void setMPresentationScheduleDetailList(List<MPresentationScheduleDetail> mPresentationScheduleDetailList) {
		this.mPresentationScheduleDetailList = mPresentationScheduleDetailList;
	}

}
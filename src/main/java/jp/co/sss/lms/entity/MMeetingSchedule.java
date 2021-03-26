package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * MMeetingScheduleエンティティクラス
 * 
 * @author 江坂 亮典
 */

@Entity
@Table(name = "m_meeting_schedule")
public class MMeetingSchedule {

	/** 面談スケジュールID */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(name = "generator", allocationSize = 1)
	private Integer meetingScheduleId;

	/** 予約編集期限 */
	@Column(nullable = true, unique = false)
	private Timestamp editLimit;

	/** 面談開始日 */
	@Column(nullable = true, unique = false)
	private Timestamp meetingOpenDate;

	/** 面談終了日 */
	@Column(nullable = true, unique = false)
	private Timestamp meetingCloseDate;

	/** 用途 */
	@Column(length = 100, nullable = true, unique = false)
	private String purpose;

	/** 削除フラグ */
	@Column(precision = 5, nullable = true, unique = false)
	private Short deleteFlg;

	/** 初回作成者 */
	@Column(precision = 10, nullable = true, unique = false)
	private Integer firstCreateUser;

	/** 初回作成日時 */
	@Column(nullable = true, unique = false)
	private Timestamp firstCreateDate;

	/** 最終更新者 */
	@Column(precision = 10, nullable = true, unique = false)
	private Integer lastModifiedUser;

	/** 最終更新日時 */
	@Column(nullable = true, unique = false)
	private Timestamp lastModifiedDate;

	/** リレーションテーブル */
	@OneToMany(mappedBy = "mMeetingSchedule")
	private List<MMeetingScheduleDetail> mMeetingScheduleDetailList;


	/** リレーションテーブル */
	@OneToMany(mappedBy = "mMeetingSchedule")
	private List<TMeetingPlace> tMeetingPlaceList;

	public Integer getMeetingScheduleId() {
		return meetingScheduleId;
	}

	public void setMeetingScheduleId(Integer meetingScheduleId) {
		this.meetingScheduleId = meetingScheduleId;
	}

	public Timestamp getEditLimit() {
		return editLimit;
	}

	public void setEditLimit(Timestamp editLimit) {
		this.editLimit = editLimit;
	}

	public Timestamp getMeetingOpenDate() {
		return meetingOpenDate;
	}

	public void setMeetingOpenDate(Timestamp meetingOpenDate) {
		this.meetingOpenDate = meetingOpenDate;
	}

	public Timestamp getMeetingCloseDate() {
		return meetingCloseDate;
	}

	public void setMeetingCloseDate(Timestamp meetingCloseDate) {
		this.meetingCloseDate = meetingCloseDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
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

	public List<MMeetingScheduleDetail> getmMeetingScheduleDetailList() {
		return mMeetingScheduleDetailList;
	}

	public void setmMeetingScheduleDetailList(List<MMeetingScheduleDetail> mMeetingScheduleDetailList) {
		this.mMeetingScheduleDetailList = mMeetingScheduleDetailList;
	}

	public List<TMeetingPlace> gettMeetingPlaceList() {
		return tMeetingPlaceList;
	}

	public void settMeetingPlaceList(List<TMeetingPlace> tMeetingPlaceList) {
		this.tMeetingPlaceList = tMeetingPlaceList;
	}

}

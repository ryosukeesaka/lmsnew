package jp.co.sss.lms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TMeetingPlaceエンティティクラス
 *@author 江坂 亮典
 */

@Entity
@Table(name = "t_meeting_place")
public class TMeetingPlace {

    /** 面談対象会場ID */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
        name = "generator",
        allocationSize = 1)
    @Column(precision = 10, nullable = false, unique = true)
    private Integer meetingPlaceId;

    /**  面談対象会場注釈 */
    @Column(length = 1000, nullable = true, unique = false)
    private String meetingPlaceNote;

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

    /** 公開フラグ */
    @Column(precision = 5, nullable = true, unique = false)
    private Short publishedFlg;

    /** リレーションテーブル */
    @ManyToOne
    @JoinColumn(name = "meeting_schedule_id", referencedColumnName = "meetingScheduleId")
    private MMeetingSchedule mMeetingSchedule;

    /** リレーションテーブル */
    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "placeId")
    private MPlace mPlace;

	public Integer getMeetingPlaceId() {
		return meetingPlaceId;
	}

	public void setMeetingPlaceId(Integer meetingPlaceId) {
		this.meetingPlaceId = meetingPlaceId;
	}

	public String getMeetingPlaceNote() {
		return meetingPlaceNote;
	}

	public void setMeetingPlaceNote(String meetingPlaceNote) {
		this.meetingPlaceNote = meetingPlaceNote;
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

	public Short getPublishedFlg() {
		return publishedFlg;
	}

	public void setPublishedFlg(Short publishedFlg) {
		this.publishedFlg = publishedFlg;
	}

	public MMeetingSchedule getmMeetingSchedule() {
		return mMeetingSchedule;
	}

	public void setmMeetingSchedule(MMeetingSchedule mMeetingSchedule) {
		this.mMeetingSchedule = mMeetingSchedule;
	}

	public MPlace getmPlace() {
		return mPlace;
	}

	public void setmPlace(MPlace mPlace) {
		this.mPlace = mPlace;
	}
}

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

/**
 * MMeetingScheduleDetailエンティティクラス
 * 
 * @author 久保修平
 */
@Entity
@Table(name = "m_meeting_schedule_detail")
public class MMeetingScheduleDetail {
	/** 面談スケジュール詳細ID */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(name = "generator", allocationSize = 1)
	private Integer meetingScheduleDetailId;
	
	/**  削除フラグ */
    @Column(precision = 5, nullable = true, unique = false)
    private Short deleteFlg;
    
    /**  初回作成者 */
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
    
    /** 面談日 */
    @Column(nullable = true, unique = false)
    private Timestamp meetingDate;
    
    /** 面談時間 */
    @Column(nullable = true, unique = false)
    private String meetingTime;
    
    /** リレーションテーブル */
	@ManyToOne
	@JoinColumn(name = "meeting_schedule_id", referencedColumnName = "meetingScheduleId")
	private MMeetingSchedule mMeetingSchedule;
	
	@OneToMany(mappedBy = "mMeetingScheduleDetail")
    private List<TMeetingCompany> tMeetingCompanyList;
	

    public Integer getMeetingScheduleDetailId() {
		return meetingScheduleDetailId;
	}

	public void setMeetingScheduleDetailId(Integer meetingScheduleDetailId) {
		this.meetingScheduleDetailId = meetingScheduleDetailId;
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

	public Timestamp getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Timestamp meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}

	public MMeetingSchedule getmMeetingSchedule() {
		return mMeetingSchedule;
	}

	public void setmMeetingSchedule(MMeetingSchedule mMeetingSchedule) {
		this.mMeetingSchedule = mMeetingSchedule;
	}

	public List<TMeetingCompany> gettMeetingCompanyList() {
		return tMeetingCompanyList;
	}

	public void settMeetingCompanyList(List<TMeetingCompany> tMeetingCompanyList) {
		this.tMeetingCompanyList = tMeetingCompanyList;
	}

	

}

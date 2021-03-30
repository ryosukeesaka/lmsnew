package jp.co.sss.lms.entity;

import java.sql.Timestamp;

/**
 * 成果報告会スケジュールマスタテーブルエンティティクラス
 * 
 * @author Shuhei Kubo
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "t_meeting_company")
public class TMeetingCompany {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(name = "generator", table = "id_generator", allocationSize = 1)
	@Column(precision = 10, nullable = false, unique = true)
	private Integer meetingCompanyId;

	/** joinAmountプロパティ */
	@Column(precision = 10, nullable = true, unique = false)
	private Integer joinAmount;

	/** deleteFlgプロパティ */
	@Column(precision = 5, nullable = true, unique = false)
	private Short deleteFlg;

	/** firstCreateUserプロパティ */
	@Column(precision = 10, nullable = true, unique = false)
	private Integer firstCreateUser;

	/** firstCreateDateプロパティ */
	@Column(nullable = true, unique = false)
	private Timestamp firstCreateDate;

	/** lastModifiedUserプロパティ */
	@Column(precision = 10, nullable = true, unique = false)
	private Integer lastModifiedUser;

	/** lastModifiedDateプロパティ */
	@Column(nullable = true, unique = false)
	private Timestamp lastModifiedDate;

	/** companyRequestプロパティ */
	@Column(length = 100, nullable = true, unique = false)
	private String companyRequest;

	@ManyToOne
	@JoinColumn(name = "meeting_place_id", referencedColumnName = "meetingPlaceId")

	private TMeetingPlace tMeetingPlace;

	@ManyToOne
	@JoinColumn(name = "meeting_schedule_detail_id", referencedColumnName = "meetingScheduleDetailId")
	private MMeetingScheduleDetail mMeetingScheduleDetail;

	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName = "companyId")
	private MCompany mCompany;

	public Integer getMeetingCompanyId() {
		return meetingCompanyId;
	}

	public void setMeetingCompanyId(Integer meetingCompanyId) {
		this.meetingCompanyId = meetingCompanyId;
	}

	public Integer getJoinAmount() {
		return joinAmount;
	}

	public void setJoinAmount(Integer joinAmount) {
		this.joinAmount = joinAmount;
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

	public String getCompanyRequest() {
		return companyRequest;
	}

	public void setCompanyRequest(String companyRequest) {
		this.companyRequest = companyRequest;
	}

	public TMeetingPlace gettMeetingPlace() {
		return tMeetingPlace;
	}

	public void settMeetingPlace(TMeetingPlace tMeetingPlace) {
		this.tMeetingPlace = tMeetingPlace;
	}

	public MMeetingScheduleDetail getmMeetingScheduleDetail() {
		return mMeetingScheduleDetail;
	}

	public void setmMeetingScheduleDetail(MMeetingScheduleDetail mMeetingScheduleDetail) {
		this.mMeetingScheduleDetail = mMeetingScheduleDetail;
	}

	public MCompany getmCompany() {
		return mCompany;
	}

	public void setmCompany(MCompany mCompany) {
		this.mCompany = mCompany;
	}

	

	

}

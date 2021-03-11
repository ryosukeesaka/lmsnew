package jp.co.sss.lms.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 成果報告会スケジュール詳細マスタテーブルエンティティクラス
 * 
 * @author Yuna Kato
 */

@Entity
@Table(name = "m_presentation_schedule_detail")
public class MPresentationScheduleDetail {

	/** 成果報告会スケジュール詳細ID */
	@Id
	//@Column(name = "presentation_schedule_detail_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
			name = "generator",
			table="id_generator",
			pkColumnName = "pk",
			valueColumnName = "value",
			pkColumnValue = "M_PRESENTATION_SCHEDULE_DETAIL_PRESENTATION_SCHEDULE_DETAIL_ID",
			allocationSize = 1)
	private Integer presentationScheduleDetailId;

	/** presentation_time */
	@Column
	private String presentationTime;

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

	@ManyToOne
	@JoinColumn(name = "presentation_schedule_id", referencedColumnName = "presentationScheduleId")
	private MPresentationSchedule mPresentationSchedule;

	@OneToMany(mappedBy = "mPresentationScheduleDetail")
	private  List<MPresentationTeam> mPresentationTeamList;

	public Integer getPresentationScheduleDetailId() {
		return presentationScheduleDetailId;
	}

	public void setPresentationScheduleDetailId(Integer presentationScheduleDetailId) {
		this.presentationScheduleDetailId = presentationScheduleDetailId;
	}

	public String getPresentationTime() {
		return presentationTime;
	}

	public void setPresentationTime(String presentationTime) {
		this.presentationTime = presentationTime;
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
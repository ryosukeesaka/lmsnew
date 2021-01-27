package jp.co.sss.lms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 勤怠管理(受講生)テーブルエンティティクラス
 * 
 * @author ogura
 */
@Entity
@Table(name = "t_student_attendance")
public class TStudentAttendance {

	/** 受講生入力勤怠情報ID */
	@Id
	@Column(name = "student_attendance_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
			name = "generator", 
			table="id_generator", 
			pkColumnName = "pk",
		    valueColumnName = "value",
		    pkColumnValue = "T_STUDENT_ATTENDANCE_STUDENT_ATTENDANCE_ID",
			allocationSize = 1)
	private Integer studentAttendanceId;

	/** LMSユーザID */
	@Column
	private Integer lmsUserId;

	/** 日付 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date trainingDate;

	/** 出勤時間 */
	@Column
	private String trainingStartTime;

	/** 退勤時間 */
	@Column
	private String trainingEndTime;

	/** 勤怠状態 */
	@Column
	private Short status;

	/** 備考 */
	@Column
	private String note;

	/** 企業アカウントID */
	@Column
	private Integer accountId;

	/** 削除フラグ */
	@Column
	private Short deleteFlg;

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

	/** 中抜け時間 */
	@Column
	private Integer blankTime;

	/** LMSユーザーマスタ(外部参照) */
	@ManyToOne
	@JoinColumn(name = "lmsUserId", referencedColumnName = "lmsUserId", insertable = false, updatable = false)
	private MLmsUser mLmsUser;

	public Integer getStudentAttendanceId() {
		return studentAttendanceId;
	}

	public void setStudentAttendanceId(Integer studentAttendanceId) {
		this.studentAttendanceId = studentAttendanceId;
	}

	public Integer getLmsUserId() {
		return lmsUserId;
	}

	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
	}

	public Date getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	public String getTrainingStartTime() {
		return trainingStartTime;
	}

	public void setTrainingStartTime(String trainingStartTime) {
		this.trainingStartTime = trainingStartTime;
	}

	public String getTrainingEndTime() {
		return trainingEndTime;
	}

	public void setTrainingEndTime(String trainingEndTime) {
		this.trainingEndTime = trainingEndTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Integer getBlankTime() {
		return blankTime;
	}

	public void setBlankTime(Integer blankTime) {
		this.blankTime = blankTime;
	}

	public MLmsUser getMLmsUser() {
		return mLmsUser;
	}

	public void setMLmsUser(MLmsUser mLmsUser) {
		this.mLmsUser = mLmsUser;
	}

}

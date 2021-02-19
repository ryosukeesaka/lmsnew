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
 * ユーザー・成果報告会チーム紐付けテーブルエンティティクラス
 * 
 * @author Yuna Kato
 */

@Entity
@Table(name = "t_user_presentation_team")
public class TUserPresentationTeam {

	/** ユーザー・チーム紐付けID */
	@Id
	@Column(name = "user_presentation_team_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
			name = "generator",
			table="id_generator",
			pkColumnName = "pk",
			valueColumnName = "value",
			pkColumnValue = "T_USER_PRESENTATION_TEAM_USER_PRESENTATION_TEAM_ID",
			allocationSize = 1)
	private Integer userPresentationTeamId;

	/** LMSユーザID */
	@Column
	private Integer lmsUserId;

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
	@JoinColumn(name = "presentation_team_id", referencedColumnName = "presentation_team_id")
	private MPresentationTeam mPresentationTeam;

//	@ManyToOne
//	@JoinColumn(name = "lms_user_id", referencedColumnName = "lms_user_id")
//	private MLmsUser  mLmsUser;

	public Integer getUserPresentationTeamId() {
		return userPresentationTeamId;
	}

	public void setUserPresentationTeamId(Integer userPresentationTeamId) {
		this.userPresentationTeamId = userPresentationTeamId;
	}

	public Integer getLmsUserId() {
		return lmsUserId;
	}

	public void setLmsUserId(Integer lmsUserId) {
		this.lmsUserId = lmsUserId;
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

	public MPresentationTeam getMPresentationTeam() {
		return mPresentationTeam;
	}

	public void setMPresentationTeam(MPresentationTeam mPresentationTeam) {
		this.mPresentationTeam = mPresentationTeam;
	}

//	public MLmsUser getMLmsUser() {
//		return mLmsUser;
//	}
//
//	public void setMLmsUser(MLmsUser mLmsUser) {
//		this.mLmsUser = mLmsUser;
//	}
}

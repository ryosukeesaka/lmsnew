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
 * 成果報告会チームマスタテーブルエンティティクラス
 * 
 * @author Yuna Kato
 */

@Entity
@Table(name = "m_presentation_team")
public class MPresentationTeam {

	/** 成果報告会チームID */
	@Id
	@Column(name = "presentation_team_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
			name = "generator",
			table="id_generator",
			pkColumnName = "pk",
			valueColumnName = "value",
			pkColumnValue = "M_PRESENTATION_TEAM_PRESENTATION_TEAM_ID",
			allocationSize = 1)
	private Integer presentationTeamId;

	/** 成果報告会チーム名 */
	@Column
	private String presentationTeamName;

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
	@JoinColumn(name = "presentation_place_id", referencedColumnName = "presentation_place_id")
	private TPresentationPlace tPresentationPlace;

	@ManyToOne
	@JoinColumn(name = "presentation_schedule_detail_id", referencedColumnName = "presentation_schedule_detail_id")
	private MPresentationScheduleDetail mPresentationScheduleDetail;

	@OneToMany(mappedBy = "mPresentationTeam")
	private  List<TUserPresentationTeam> tUserPresentationTeamList;

	@OneToMany(mappedBy = "mPresentationTeam")
	private List<TPresentationCompany> tPresentationCompanyList;

	public Integer getPresentationTeamId() {
		return presentationTeamId;
	}

	public void setPresentationTeamId(Integer presentationTeamId) {
		this.presentationTeamId = presentationTeamId;
	}

	public String getPresentationTeamName() {
		return presentationTeamName;
	}

	public void setPresentationTeamName(String presentationTeamName) {
		this.presentationTeamName = presentationTeamName;
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

	public TPresentationPlace gettPresentationPlace() {
		return tPresentationPlace;
	}

	public void settPresentationPlace(TPresentationPlace tPresentationPlace) {
		this.tPresentationPlace = tPresentationPlace;
	}

	public MPresentationScheduleDetail getMPresentationScheduleDetail() {
		return mPresentationScheduleDetail;
	}

	public void setMPresentationScheduleDetail(MPresentationScheduleDetail mPresentationScheduleDetail) {
		this.mPresentationScheduleDetail = mPresentationScheduleDetail;
	}

	public List<TUserPresentationTeam> getTUserPresentationTeamList() {
		return tUserPresentationTeamList;
	}

	public void setTUserPresentationTeamList(List<TUserPresentationTeam> tUserPresentationTeamList) {
		this.tUserPresentationTeamList = tUserPresentationTeamList;
	}

	public List<TPresentationCompany> getTPresentationCompanyList() {
		return tPresentationCompanyList;
	}

	public void setTPresentationCompanyList(List<TPresentationCompany> tPresentationCompanyList) {
		this.tPresentationCompanyList = tPresentationCompanyList;
	}

}

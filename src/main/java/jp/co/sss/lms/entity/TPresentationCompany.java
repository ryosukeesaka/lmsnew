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
 * 成果報告会対象企業テーブルエンティティ
 * 
 * @author Takayuki Nomoto
 */
@Entity
@Table(name = "t_presentation_company")
public class TPresentationCompany {

	/** 成果報告会対象企業ID */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(name = "generator",allocationSize = 1)
	@Column(name = "presentation_company_id")
	private Integer presentationCompanyId;

	/** 参加可能フラグ */
	@Column
	private Short joinAbleFlg;

	/** 参加人数 */
	@Column
	private Integer joinAmount;

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

	/** 参加者名 */
	@Column
	private String joinName;

	@ManyToOne
	@JoinColumn(name = "presentation_team_id", referencedColumnName = "presentation_team_id")
	private MPresentationTeam mPresentationTeam;

	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName = "companyId")
	private MCompany mCompany;

	public Integer getPresentationCompanyId() {
		return presentationCompanyId;
	}

	public void setPresentationCompanyId(Integer presentationCompanyId) {
		this.presentationCompanyId = presentationCompanyId;
	}

	public Short getJoinAbleFlg() {
		return joinAbleFlg;
	}

	public void setJoinAbleFlg(Short joinAbleFlg) {
		this.joinAbleFlg = joinAbleFlg;
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

	public String getJoinName() {
		return joinName;
	}

	public void setJoinName(String joinName) {
		this.joinName = joinName;
	}

	public MPresentationTeam getMPresentationTeam() {
		return mPresentationTeam;
	}

	public void setMPresentationTeam(MPresentationTeam mPresentationTeam) {
		this.mPresentationTeam = mPresentationTeam;
	}

	public MCompany getMCompany() {
		return mCompany;
	}

	public void setMCompany(MCompany mCompany) {
		this.mCompany = mCompany;
	}

}

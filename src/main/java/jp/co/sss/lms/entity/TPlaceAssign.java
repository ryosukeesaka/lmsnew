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
 * TPlaceAssignエンティティクラス
 *@author m-uno
 */
@Entity
@Table(name = "t_place_assign")
public class TPlaceAssign {
	
	/** アサイン情報ID */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
		    name = "generator",
		    table = "id_generator",
		    pkColumnName = "pk",
		    valueColumnName = "value",
		    pkColumnValue = "T_PLACE_ASSIGN_PLACE_ASSIGN_ID",
		    allocationSize = 1
		    )
	private Integer placeAssignId;
	
	/** 人数 */
	@Column
	private Integer applicantAmount;
	
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
	private Integer  lastModifiedUser;
	
	/** 最終更新日時 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	 /** 会場アサイン情報詳細 (外部参照) */
	@OneToMany(mappedBy="tPlaceAssign")
	public List<TPlaceAssignDetail> tPlaceAssignDetailList;
	
	 /**  企業・コース紐付け (外部参照) */
	@ManyToOne
	@JoinColumn(name="company_course_id", referencedColumnName = "company_course_id")
	private TCompanyCourse tCompanyCourse;
	
	/**  会場マスタ (外部参照) */
	@ManyToOne
	@JoinColumn(name="placeId", referencedColumnName = "placeId")
	private MPlace mPlace;

	public Integer getPlaceAssignId() {
		return placeAssignId;
	}

	public void setPlaceAssignId(Integer placeAssignId) {
		this.placeAssignId = placeAssignId;
	}

	public Integer getApplicantAmount() {
		return applicantAmount;
	}

	public void setApplicantAmount(Integer applicantAmount) {
		this.applicantAmount = applicantAmount;
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

	public TCompanyCourse gettCompanyCourse() {
		return tCompanyCourse;
	}

	public void settCompanyCourse(TCompanyCourse tCompanyCourse) {
		this.tCompanyCourse = tCompanyCourse;
	}

	public MPlace getmPlace() {
		return mPlace;
	}

	public void setmPlace(MPlace mPlace) {
		this.mPlace = mPlace;
	}
	
}

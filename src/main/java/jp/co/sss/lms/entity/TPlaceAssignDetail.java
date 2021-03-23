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
 * TPlaceAssignDetailエンティティクラス
 *@author m-uno
 */
@Entity
@Table(name = "t_place_assign_detail")
public class TPlaceAssignDetail {
	
	/** 会場アサイン情報詳細ID */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	@TableGenerator(
		    name = "generator",
		    table = "id_generator",
		    pkColumnName = "pk",
		    valueColumnName = "value",
		    pkColumnValue = "T_PLACE_ASSIGN_DETAIL_PLACE_ASSIGN_DETAIL_ID",
		    allocationSize = 1
		    )
	private Integer placeAssignDetailId;
	
	/** 年月 */
	@Column
	private Date period;
	
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
	
	/** 会場アサイン情報 */
	@ManyToOne
	@JoinColumn(name="placeAssignId", referencedColumnName = "placeAssignId")
	private TPlaceAssign tPlaceAssign;

	public Integer getPlaceAssignDetailId() {
		return placeAssignDetailId;
	}

	public void setPlaceAssignDetailId(Integer placeAssignDetailId) {
		this.placeAssignDetailId = placeAssignDetailId;
	}

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date period) {
		this.period = period;
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

	public TPlaceAssign gettPlaceAssign() {
		return tPlaceAssign;
	}

	public void settPlaceAssign(TPlaceAssign tPlaceAssign) {
		this.tPlaceAssign = tPlaceAssign;
	}

}

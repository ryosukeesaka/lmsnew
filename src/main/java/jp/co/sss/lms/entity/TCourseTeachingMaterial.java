package jp.co.sss.lms.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * コース・教材紐付けエンティティ
 * 
 * @author otake
 */

@Table(name = "t_course_teaching_material")
@Entity
public class TCourseTeachingMaterial {

	/** コース・教材紐付けID */
	@Id
	private Integer courseTeachingMaterialId;

	/** ファイル格納場所 */
	@Column
	private String filePath;

	/** コースID */
	@Column
	private Integer courseId;

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

	public Integer getCourseTeachingMaterialId() {
		return courseTeachingMaterialId;
	}

	public void setCourseTeachingMaterialId(Integer courseTeachingMaterialId) {
		this.courseTeachingMaterialId = courseTeachingMaterialId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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

}
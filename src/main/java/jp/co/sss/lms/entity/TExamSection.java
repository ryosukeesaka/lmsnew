package jp.co.sss.lms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 試験・セクション紐付けエンティティ
 * @author 垣花 武留
 *
 */
@Entity
@Table(name = "t_exam_section")
public class TExamSection {
	
	 private static final long serialVersionUID = 1L;

	    /** 試験・セクション紐付けID */
	    @Id
	    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
	    @TableGenerator(
		    name = "generator",
		    table = "id_generator",
		    pkColumnName = "pk",
		    valueColumnName = "value",
		    pkColumnValue = "T_EXAM_SECTION_EXAM_SECTION_ID",
		    allocationSize = 1
		    )
	    @Column(precision = 10, nullable = false, unique = true)
	    private Integer examSectionId;
	    
	    /** 試験ID */
	    @Column
	    private Integer examId;
	  
	    /** 公開日時 */
	    @Column(nullable = true, unique = false)
	    private Timestamp publicDate;

	    /** 非公開日時 */
	    @Column(nullable = true, unique = false)
	    private Timestamp privateDate;

	    /** 企業アカウントID */
	    @Column(precision = 10, nullable = true, unique = false)
	    private Integer accountId;

	    /** 削除フラグ */
	    @Column(precision = 5, nullable = true, unique = false)
	    private Short deleteFlg;

	    /** 初回作成者 */
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
	    
	    /** セクションマスタ(外部参照) */
	    @ManyToOne 
	    @JoinColumn(name="section_id", referencedColumnName = "section_id")
	    public MSection mSection;
	    
	    /** 試験マスタ */
	    @OneToOne(mappedBy = "tExamSection")
	    private MExam mExam;
	    
	    /** 試験結果 */
	    @OneToOne
	    @JoinColumn(name="exam_section_id", referencedColumnName = "exam_section_id")
	    private TExamResult tExamResult;

		public Integer getExamSectionId() {
			return examSectionId;
		}

		public void setExamSectionId(Integer examSectionId) {
			this.examSectionId = examSectionId;
		}
		
		public Integer getExamId() {
			return examId;
		}

		public void setExamId(Integer examId) {
			this.examId = examId;
		}

		public Timestamp getPublicDate() {
			return publicDate;
		}

		public void setPublicDate(Timestamp publicDate) {
			this.publicDate = publicDate;
		}

		public Timestamp getPrivateDate() {
			return privateDate;
		}

		public void setPrivateDate(Timestamp privateDate) {
			this.privateDate = privateDate;
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

		public MSection getMSection() {
			return mSection;
		}

		public void setMSection(MSection mSection) {
			this.mSection = mSection;
		}

		public MExam getMExam() {
			return mExam;
		}

		public void setMExam(MExam mExam) {
			this.mExam = mExam;
		}

		public TExamResult getTExamResult() {
			return tExamResult;
		}

		public void setTExamResult(TExamResult tExamResult) {
			this.tExamResult = tExamResult;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

}

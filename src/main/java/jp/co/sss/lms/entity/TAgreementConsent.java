package jp.co.sss.lms.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TAgreementConsentエンティティクラス
 *
 */
@Entity
@Table(name = "t_agreement_consent")
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2016/10/26 16:43:08")
public class TAgreementConsent implements Serializable {

    private static final long serialVersionUID = 1L;

    /** agreementConsentIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
        name = "generator",
        allocationSize = 1)
    @Column(precision = 10, nullable = false, unique = true)
    private Integer agreementConsentId;

    /** agreementIdプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer agreementId;

    /** companyCourseIdプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    private Integer companyCourseId;

    /** consent_flgプロパティ */
    @Column(precision = 5, nullable = true, unique = false)
    private Short consentFlg;

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

//    @ManyToOne
//    @JoinColumn(name = "agreement_id", referencedColumnName ="agreement_id")
//    public MAgreement mAgreement;

    @OneToOne(mappedBy="tAgreementConsent")
    private TCompanyCourse tCompanyCourse;

	public Integer getAgreementConsentId() {
		return agreementConsentId;
	}

	public void setAgreementConsentId(Integer agreementConsentId) {
		this.agreementConsentId = agreementConsentId;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getCompanyCourseId() {
		return companyCourseId;
	}

	public void setCompanyCourseId(Integer companyCourseId) {
		this.companyCourseId = companyCourseId;
	}

	public Short getConsentFlg() {
		return consentFlg;
	}

	public void setConsentFlg(Short consentFlg) {
		this.consentFlg = consentFlg;
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

	public TCompanyCourse gettCompanyCourse() {
		return tCompanyCourse;
	}

	public void settCompanyCourse(TCompanyCourse tCompanyCourse) {
		this.tCompanyCourse = tCompanyCourse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
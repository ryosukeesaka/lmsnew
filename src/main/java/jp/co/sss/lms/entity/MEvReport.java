package jp.co.sss.lms.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 * MEvReportエンティティクラス
 *
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/03/15 19:04:15")
public class MEvReport implements Serializable {

    private static final long serialVersionUID = 1L;

    /** evReportIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator",
	    allocationSize = 1)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer evReportId;

    /** reportNameプロパティ */
    @Column(length = 100, nullable = true, unique = false)
    public String reportName;

    /** fileNameプロパティ */
    @Column(length = 100, nullable = true, unique = false)
    public String fileName;

    /** sheetNameStdプロパティ */
    @Column(length = 100, nullable = true, unique = false)
    public String sheetNameStd;

    /** rowCompanyプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer rowCompany;

    /** clmCompanyプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer clmCompany;

    /** rowUserプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer rowUser;

    /** clmUserプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer clmUser;

    /** accountIdプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer accountId;

    /** deleteFlgプロパティ */
    @Column(precision = 5, nullable = true, unique = false)
    public Short deleteFlg;

    /** firstCreateUserプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer firstCreateUser;

    /** firstCreateDateプロパティ */
    @Column(nullable = true, unique = false)
    public Timestamp firstCreateDate;

    /** lastModifiedUserプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer lastModifiedUser;

    /** lastModifiedDateプロパティ */
    @Column(nullable = true, unique = false)
    public Timestamp lastModifiedDate;

    /** hiddenFlgプロパティ */
    @Column(precision = 5, nullable = true, unique = false)
    public Short hiddenFlg;

//    @OneToMany(mappedBy = "mEvReport")
//    public List<MEvReportDetail> mEvReportDetailList;
//
//    @OneToMany(mappedBy = "mEvReport")
//    public List<TEvCourse> tEvCourseList;
//
//    @OneToMany(mappedBy = "mEvReport")
//    public List<TEvReportResult> tEvReportResultList;
}
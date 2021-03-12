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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * TEvReportResultエンティティクラス
 *
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/03/15 19:04:15")
public class TEvReportResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /** evReportResultIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
        name = "generator",
        allocationSize = 1)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer evReportResultId;

    /** evReportIdプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer evReportId;

    /** lmsUserIdプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer lmsUserId;

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

    /** publishedFlgプロパティ */
    @Column(precision = 5, nullable = true, unique = false)
    public Short publishedFlg;

    //@ManyToOne
    //@JoinColumn(name="ev_report_id", referencedColumnName = "evReportId")
    //public MEvReport mEvReport;

    //@ManyToOne
    //@JoinColumn(name="lms_user_id", referencedColumnName = "lmsUserId")
    //public MLmsUser mLmsUser;

    @OneToMany(mappedBy = "tEvReportResult")
    public List<TEvReportResultDetail> tEvReportResultDetailList;
}
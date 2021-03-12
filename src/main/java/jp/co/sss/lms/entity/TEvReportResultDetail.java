package jp.co.sss.lms.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

/**
 * TEvReportResultDetailエンティティクラス
 *
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/03/15 19:04:15")
public class TEvReportResultDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /** evReportResultDetailIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator",
	    allocationSize = 1)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer evReportResultDetailId;

    /** evReportResultIdプロパティ */
    //@Column(precision = 10, nullable = true, unique = false)
    //public Integer evReportResultId;

    /** evReportDetailIdプロパティ */
    //@Column(precision = 10, nullable = true, unique = false)
    //public Integer evReportDetailId;

    /** valueプロパティ */
    @Column(length = 10000, nullable = true, unique = false)
    public String value;

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

    @ManyToOne
    @JoinColumn(name="ev_report_result_id", referencedColumnName = "evReportResultId")
    public TEvReportResult tEvReportResult;

    @ManyToOne
    @JoinColumn(name="ev_report_detail_id", referencedColumnName = "evReportDetailId")
    public MEvReportDetail mEvReportDetail;
}
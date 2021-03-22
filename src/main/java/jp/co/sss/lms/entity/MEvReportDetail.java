package jp.co.sss.lms.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

//import javax.annotation.Generated;
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

import jp.co.sss.lms.entity.MEvReport;
//import jp.co.sss.lms.entity.TEvReportResultDetail;

/**
 * MEvReportDetailエンティティクラス
 *
 */
@Entity
@Table(name = "m_ev_report_detail")
//@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/03/16 23:57:28")
public class MEvReportDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /** evReportDetailIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
        name = "generator",
        allocationSize = 1)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer evReportDetailId;

    /** evReportIdプロパティ */
    @Column(name="ev_report_id", precision = 10, nullable = true, unique = false)
    public Integer evReportId;

    /** fieldNameプロパティ */
    @Column(length = 100, nullable = true, unique = false)
    public String fieldName;

    /** sheetNameプロパティ */
    @Column(length = 100, nullable = true, unique = false)
    public String sheetName;

    /** rowプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer row;

    /** clmプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer clm;

    /** examIdプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer examId;

    /** genreDetailIdプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer genreDetailId;

    /** outputTypeプロパティ */
    @Column(precision = 5, nullable = true, unique = false)
    public Short outputType;

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

    /** requiredFlgプロパティ */
    @Column(precision = 5, nullable = true, unique = false)
    public Short requiredFlg;

    /** inputTypeプロパティ */
    @Column(precision = 5, nullable = true, unique = false)
    public Short inputType;

    /** rangeFromプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer rangeFrom;

    /** rangeToプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer rangeTo;

    /** deliverablesIdプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer deliverablesId;

    @ManyToOne
    @JoinColumn(name="ev_report_id", referencedColumnName = "evReportId", insertable=false, updatable=false)
    public MEvReport mEvReport;

    @OneToMany(mappedBy = "mEvReportDetail")
    public List<TEvReportResultDetail> tEvReportResultDetailList;

    //@ManyToOne
    //@JoinColumn(name="exam_id", referencedColumnName = "examId")
    //public MExam mExam;

    //@ManyToOne
    //@JoinColumn(name="deliverables_id", referencedColumnName = "deliverablesId")
    //public MDeliverables mDeliverables;

}
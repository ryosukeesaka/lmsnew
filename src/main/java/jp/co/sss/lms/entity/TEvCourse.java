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
 * TEvCourseエンティティクラス
 *
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/03/15 19:04:15")
public class TEvCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /** evCourseIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
	    name = "generator",
	    allocationSize = 1)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer evCourseId;

    /** evReportIdプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer evReportId;

    /** courseIdプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer courseId;

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

    //@ManyToOne
    //@JoinColumn(name="course_id", referencedColumnName = "courseId")
    //public MCourse mCourse;

    //@ManyToOne
    //@JoinColumn(name="ev_report_id", referencedColumnName = "evReportId")
    //public MEvReport mEvReport;
}
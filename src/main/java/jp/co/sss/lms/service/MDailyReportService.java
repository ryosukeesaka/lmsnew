package jp.co.sss.lms.service;

import org.springframework.stereotype.Service;

import jp.co.sss.lms.entity.MDailyReport;
import jp.co.sss.lms.repository.MDailyReportRepository;

@Service
public class MDailyReportService {
	
	MDailyReportRepository repository;
//    public MDailyReport findById(Integer dailyReportId) {
//        return select()
//                .join(mDailyReportDetailList(), JoinType.LEFT_OUTER)
//                .join(tCourseDailyReportList(), JoinType.LEFT_OUTER, eq(tCourseDailyReportList().deleteFlg(), Constants.DB_FLG_FALSE))
//                .join(tSectionDailyReportList(), JoinType.LEFT_OUTER, eq(tSectionDailyReportList().deleteFlg(), Constants.DB_FLG_FALSE))
//                .id(dailyReportId).orderBy(asc(mDailyReportDetailList().fieldNum())).getSingleResult();
//    }
//
//    /**
//     * 識別子の昇順ですべてのエンティティを検索します。
//     *
//     * @return エンティティのリスト
//     */
//    public List<MDailyReport> findAllOrderById() {
//        return select().orderBy(asc(dailyReportId())).getResultList();
//    }
//
//    public List<MDailyReport> findByAccountId() {
//
//        Where where = Operations.and(
//                eq(accountId(), LoginUserUtil.getLoginAccountId()),
//                eq(hiddenFlg(), Constants.DB_HIDDEN_FLG_FALSE),
//                eq(deleteFlg(), Constants.DB_FLG_FALSE));
//        return select()
//                .join(mDailyReportDetailList(), JoinType.LEFT_OUTER)
//                .where(where)
//                .orderBy(asc(dailyReportId()),
//                        asc(mDailyReportDetailList().fieldNum()))
//                .getResultList();
//    }
//
//    public List<MDailyReport> findByCondition(MDailyReport mDailyReport) {
//        Where where = Operations.and(
//                eq(dailyReportId(), mDailyReport.dailyReportId),
//                contains(reportName(), mDailyReport.reportName),
//                contains(fileName(), mDailyReport.fileName),
//                contains(sheetName(), mDailyReport.sheetName),
//                eq(hiddenFlg(), Constants.DB_HIDDEN_FLG_FALSE),
//                eq(deleteFlg(), Constants.DB_FLG_FALSE));
//        return select()
//                .where(where)
//                .orderBy(asc(dailyReportId()))
//                .getResultList();
//
//    }
//
    /**
     * 識別子でエンティティを検索します。
     *
     * @param dailyReportSubmitId
     * @return エンティティ
     */
    public MDailyReport findByMDailyReport(Integer dailyReportSubmitId) {
    		return repository.findByMDailyReport(dailyReportSubmitId);
    }
//
//    /**
//     * レポート一覧画面From～To検索
//     * @param mLmsUser
//     * @param dateFrom
//     * @param dateTo
//     * @return
//     */
//    public List<DailyReportListDto> findDailyReportFromToSearch(MLmsUser mLmsUser, Timestamp dateFrom, Timestamp dateTo) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("role", Constants.CODE_VAL_ROLL_STUDENT);                    // 受講生権限のみ
//        param.put("dateFrom", dateFrom);                                       // 日付From
//        param.put("dateTo", dateTo);                                           // 日付To
//        param.put("courseName", mLmsUser.tCourseUser.mCourse.courseName);      // コース名 (管理者、講師権限のみ)
//        param.put("placeId", mLmsUser.tUserPlace.mPlace.placeId);              // 会場ID(管理者任意、それ以外必須)
//        param.put("companyId",  mLmsUser.tUserCompany.mCompany.companyId);     // 企業ID(企業、育成担当者のみ)
//        param.put("companyName", mLmsUser.tUserCompany.mCompany.companyName);  // 企業名(管理者、講師権限のみ)
//        param.put("companyAddress", mLmsUser.tUserCompany.mCompany.address);   // 企業住所(管理者権限のみ)
//        param.put("pastDate", mLmsUser.tCourseUser.mCourse.closeTime);         // 閉講6カ月含めるかどうか
//        return selectBySqlFile(DailyReportListDto.class, "jp/co/sss/lms/sql/selectDailyReport.sql",param).getResultList();
//    }
}

package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.CategoryListDto;
import jp.co.sss.lms.dto.CourseListDto;
import jp.co.sss.lms.dto.CourseServiceCategoryDto;
import jp.co.sss.lms.dto.CourseServiceCourseDto;
import jp.co.sss.lms.dto.CourseServiceSectionDto;
import jp.co.sss.lms.dto.CourseUserServiceDto;
import jp.co.sss.lms.entity.MCategory;
import jp.co.sss.lms.entity.MCourse;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.MSection;
import jp.co.sss.lms.entity.MUser;
import jp.co.sss.lms.entity.TCompanyCourse;
import jp.co.sss.lms.entity.TCourseTeachingMaterial;
import jp.co.sss.lms.repository.MCourseRepository;
import jp.co.sss.lms.repository.MUserRepository;
import jp.co.sss.lms.repository.TCompanyCourseRepository;
import jp.co.sss.lms.repository.TCourseTeachingMaterialRepository;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.MessageUtil;

/**
 * クラス名 CourseService
 * 
 * クラス概要 コース情報サービス
 * 
 * @author 橋爪 優哉
 */
@Service
public class CourseService {

	@Autowired
	MCourseRepository courseRepository;

	@Autowired
	TCompanyCourseRepository tcourseRepository;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private MUserRepository muserRepository;

	@Autowired
	private TCourseTeachingMaterialRepository tRepository;

	/**
	 * 関数概要 コースIDのエラーチェック
	 * 
	 * @param courseId コースID
	 * @return errorMessege
	 */
	public String getCourseInfo(String courseId) {
		// Integer nullJudgment = null;
		if (!isNumber(courseId.toString())) {
			String[] values = { "courseId" };
			return messageUtil.getMessage(Constants.VALID_KEY_INTEGER, values);

		}
		// コース情報取得
		MCourse mCourse = courseRepository.findByCourseId(Integer.parseInt(courseId));

		// コース情報取得に失敗した場合
		if (mCourse == null) {
			String[] values = { "courseId" };
			return messageUtil.getMessage(Constants.VALID_KEY_ALREADYDELETE, values);
		} else {
			return "";
		}
	}

	/**
	 * 
	 * 関数概要 コースIDのエラーチェック
	 * 
	 * @param courseId コースID
	 * @return courseServiceCourseDto
	 */
	public CourseServiceCourseDto getCourseDetail(Integer courseId) {

		// リポジトリで検索結果を取得
		MCourse mCourse = courseRepository.getCourseDetail(courseId);

		// CourseServiceCategoryDtoListの作成
		List<CourseServiceCategoryDto> courseServiceCategoryDtoList = new ArrayList<CourseServiceCategoryDto>();

		for (MCategory mCategory : mCourse.getMCategoryList()) {

			CourseServiceCategoryDto courseServiceCategoryDto = new CourseServiceCategoryDto();

			// CategoryEntityをCategoryDtoに詰め替える
			BeanUtils.copyProperties(mCategory, courseServiceCategoryDto);

			// CourseServiceSectionDtoListの作成
			List<CourseServiceSectionDto> courseServiceSectionDtoList = new ArrayList<CourseServiceSectionDto>();

			for (MSection mSection : mCategory.getMSectionList()) {

				CourseServiceSectionDto courseServiceSectionDto = new CourseServiceSectionDto();

				// SectionEntityをSectionDtoに詰め替える
				BeanUtils.copyProperties(mSection, courseServiceSectionDto);

				courseServiceSectionDtoList.add(courseServiceSectionDto);
			}
			courseServiceCategoryDto.setCourseServiceSectionDtoList(courseServiceSectionDtoList);
			courseServiceCategoryDtoList.add(courseServiceCategoryDto);
		}

		// courseServiceCourseDtoに設定
		CourseServiceCourseDto courseServiceCourseDto = new CourseServiceCourseDto();
		BeanUtils.copyProperties(mCourse, courseServiceCourseDto);
		courseServiceCourseDto.setCourseServiceCategoryDtoList(courseServiceCategoryDtoList);

		return courseServiceCourseDto;
	}

	/**
	 * 
	 * 関数概要 数値変換チェック
	 * 
	 * @param val 数値変換を行う文字列
	 * @return true/false 変換可能の場合 true, 変換不可の場合 false
	 */
	private boolean isNumber(String val) {
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}

	/**
	 * 
	 * 関数概要 コースに紐付くセクション情報を取得
	 * 
	 * @param courseId コースID
	 * @return getSectionDtoList セクションのリスト
	 */
	public List<CourseServiceSectionDto> getSectionDtoList(Integer courseId) {

		// CourseServiceSectionDtoListの作成
		List<CourseServiceSectionDto> courseServiceSectionDtoList = new ArrayList<CourseServiceSectionDto>();

		// リポジトリでコース情報を取得
		MCourse mCourse = courseRepository.getCourseDetail(courseId);

		// セクション情報取得直後だとセクションの日付が整列されていないため、
		// コンパレータで日付の昇順にソートする
		List<MSection> mSectionList = mCourse.getMSectionList();
		Collections.sort(mSectionList, new Comparator<MSection>() {
			@Override
			public int compare(MSection t1, MSection t2) {
				return t1.getDate().compareTo(t2.getDate());
			}
		});

		for (MSection mSection : mSectionList) {
			CourseServiceSectionDto courseServiceSectionDto = new CourseServiceSectionDto();

			// SectionEntityをSectionDtoに詰め替える
			BeanUtils.copyProperties(mSection, courseServiceSectionDto);
			courseServiceSectionDtoList.add(courseServiceSectionDto);
		}
		return courseServiceSectionDtoList;
	}

	/**
	 * 関数概要 ログインユーザ取得
	 * 
	 * @param userId　企業ID
	 * @return errorMessege
	 */

	public CourseUserServiceDto getUser(Integer userId) {

		//ユーザ情報取得
		MUser Muser = muserRepository.findByUserId(userId);
		CourseUserServiceDto user=new CourseUserServiceDto();
		BeanUtils.copyProperties(Muser.getMLmsUser(), user);
		user.setCompanyId(Muser.getMLmsUser().getTUserCompany().getCompanyId());

		// ユーザ情報取得に失敗した場合
		if (Muser.equals(null)) {
			String[] values = { "courseId" };
			messageUtil.getMessage(Constants.VALID_KEY_ALREADYDELETE, values);
		}
		return user;
	}

	/**
	 * 関数概要 コース一覧取得
	 * 
	 * @param courseId　企業ID----------------------------------------------
	 */
	public List<CourseListDto> getCourseListByCompanyId(Integer companyId) {
		// コース情報取得
		List<TCompanyCourse> courselist = tcourseRepository.findByCompanyId(companyId);
		System.out.println(courselist.get(0).getmCourse().getCourseName());
		//コースのセット
		for(int i=0;i<courselist.size();i++) {
			System.out.println(courselist.get(i).getmCourse().getCourseName());
		}

		// CategoryEntityをCategoryDtoに詰め替える
		List<CourseListDto> list=new ArrayList<CourseListDto>();
		//BeanUtils.copyProperties(courselist,list);
		// コース情報取得に失敗した場合
		if (courselist == null) {
			String[] values = { "companyId" };
			messageUtil.getMessage(Constants.VALID_KEY_ALREADYDELETE, values);
		}
		return list;
	}


	public List<CourseListDto> getCourseListByAccountCompanyId(Integer accountId) {
		// コース情報取得
		List<MCourse> courselist = courseRepository.findByAccountId(accountId);
		// CategoryEntityをCategoryDtoに詰め替える
		List<CourseListDto> list=new ArrayList<CourseListDto>();

		//コースのセット
		for(MCourse mCourse:courselist) {
			CourseListDto courseList=new CourseListDto();
			BeanUtils.copyProperties(mCourse,courseList);
			List<CategoryListDto> cate=new ArrayList<CategoryListDto>();
			//カテゴリーのセット
			for(MCategory mCategory:mCourse.mCategoryList) {
				CategoryListDto categoryList=new CategoryListDto();
				BeanUtils.copyProperties(mCategory,categoryList);
				cate.add(categoryList);
			}
			courseList.setCategoryDtoList(cate);
			list.add(courseList);
		}
		// コース情報取得に失敗した場合
		if (courselist == null) {
			String[] values = { "courseId" };
			messageUtil.getMessage(Constants.VALID_KEY_ALREADYDELETE, values);
		} 
			return list;
	}

	public long countByteachingMaterialCount(Integer courseId) {
		// コース情報取得
		return tRepository.countByCourseId(courseId);
	}
}

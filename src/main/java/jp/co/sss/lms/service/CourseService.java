package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.CourseServiceCategoryDto;
import jp.co.sss.lms.dto.CourseServiceCourseDto;
import jp.co.sss.lms.dto.CourseServiceSectionDto;
import jp.co.sss.lms.entity.MCategory;
import jp.co.sss.lms.entity.MCourse;
import jp.co.sss.lms.entity.MSection;
import jp.co.sss.lms.repository.MCourseRepository;
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
	private MessageUtil messageUtil;

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
}

package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.CourseServiceCategoryDto;
import jp.co.sss.lms.dto.CourseServiceCourseDto;
import jp.co.sss.lms.dto.CourseServiceSectionDto;
import jp.co.sss.lms.dto.CourseServiceCourseListDto;
import jp.co.sss.lms.entity.MCategory;
import jp.co.sss.lms.entity.MCourse;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.MSection;
import jp.co.sss.lms.repository.MCourseRepository;
import jp.co.sss.lms.repository.MLmsUserRepository;
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
	TCompanyCourseRepository tCompanyCourseRepository;
	
	@Autowired
	MLmsUserRepository mLmsUserRepository;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private TCourseTeachingMaterialRepository tCourseTeachingMaterialRepository;
	

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
	 * 
	 * @param userId ユーザID
	 * @return courseViewDto コース情報リスト
	 */
	public List<CourseServiceCourseListDto> getCourseList(Integer userId) {
		//Ⅰ.現在ログインしているLMSユーザの情報を取得する
		MLmsUser mLmsUser = mLmsUserRepository.getUserWithCompany(userId);
		
		//コースリスト取得
		List<MCourse> mCourseList = new ArrayList<MCourse>();
		
		//Ⅱ.取得したLMSユーザの権限が企業担当者、または育成担当者である場合			
		//企業IDをパラメータとし、下記サービスを利用し、コース情報リストを取得する		
		if(mLmsUser.getRole().equals(Constants.CODE_VAL_ROLL_COMPANY)|| mLmsUser.getRole().equals(Constants.CODE_VAL_ROLL_TRAINING)) {
			mCourseList = courseRepository.findByCompanyId(mLmsUser.getTCourseUser().getMLmsUser().getTUserCompany().getCompanyId());
		}else{
			mCourseList = courseRepository.findByAccountId(mLmsUser.getAccountId());
		}
		
		//EntityをDTOに格納する
		List<CourseServiceCourseDto> courseDtoList = this.convertToCourseDto(mCourseList);
		
		//画面表示に必要なパラメータをDTOを格納する
		List<CourseServiceCourseListDto> courseListDtoList = new ArrayList<>();
		
		for(CourseServiceCourseDto courseDto: courseDtoList) {
			CourseServiceCourseListDto courseList = new CourseServiceCourseListDto();
			
			BeanUtils.copyProperties(courseDto, courseList);
			courseList.setCourseServiceCategoryDto(courseDto.getCourseServiceCategoryDtoList());
			courseList.setTeachingMaterialCount(tCourseTeachingMaterialRepository.countByCourseId(courseDto.getCourseId()));
			courseList.setOpenCourse(this.isOpen(courseDto.getOpenTime(),courseDto.getCloseTime()));
			
			courseListDtoList.add(courseList);
			}
		return courseListDtoList;
	}
	
	
	/**
	 * コース情報エンティティをコース情報DTOリストに変換するメソッド
	 * ※使用場所（コース一覧画面）
	 * @param mCourseList コース情報リスト
	 * @return courseDtoList コース情報DTOリスト
	 */
	public List<CourseServiceCourseDto> convertToCourseDto(List<MCourse> mCourseList) {
		
		List<CourseServiceCourseDto> courseDtoList = new ArrayList<>();
		
		for(MCourse mCourse : mCourseList) {
			
			CourseServiceCourseDto courseDto = new CourseServiceCourseDto();
			BeanUtils.copyProperties(mCourse, courseDto);
			
			List<CourseServiceCategoryDto> categoryDtoList = new ArrayList<>();
			for(MCategory mCategoy : mCourse.getMCategoryList()) {
				
				CourseServiceCategoryDto categoryDto = new CourseServiceCategoryDto();
				BeanUtils.copyProperties(mCategoy, categoryDto);
				categoryDtoList.add(categoryDto);
			}
			courseDto.setCourseServiceCategoryDtoList(categoryDtoList);
			courseDtoList.add(courseDto);
		}
		return courseDtoList;
	}
	/**
	 * 開催中であるか判断するメソッド
	 * @param start 開始日
	 * @param end 閉会日
	 * @return　boolean 開催可否
	 */
	private boolean isOpen(Date start, Date end) {
		if(start != null && end != null) {
			Calendar now = Calendar.getInstance();
			// 時分秒ミリ秒に0をセット
            now.set(Calendar.HOUR_OF_DAY, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.MILLISECOND, 0);
            
            Calendar openDate = Calendar.getInstance();
            openDate.setTime(start);

            Calendar closeDate = Calendar.getInstance();
            closeDate.setTime(end);

            boolean isAlreadyStart = now.after(openDate) || now.equals(openDate);
            boolean isNotOver = now.before(closeDate) || now.equals(closeDate);

            return isAlreadyStart && isNotOver;
		}
		return false;
	}
}

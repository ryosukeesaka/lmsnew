package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jp.co.sss.lms.dto.CompanyDto;
import jp.co.sss.lms.dto.CourseServiceCategoryDto;
import jp.co.sss.lms.dto.CourseServiceCourseDto;
import jp.co.sss.lms.dto.CourseServiceSectionDto;
import jp.co.sss.lms.dto.LmsUserDto;
import jp.co.sss.lms.dto.CourseServiceCourseListDto;
import jp.co.sss.lms.entity.MCategory;
import jp.co.sss.lms.entity.MCompany;
import jp.co.sss.lms.entity.MCourse;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.MSection;
import jp.co.sss.lms.form.CompanyAttendanceForm;
import jp.co.sss.lms.repository.MCompanyRepository;
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
 * @author 山木 翔
 */
@Service
public class CompanyService {
	
	@Autowired
	MLmsUserRepository mLmsUserRepository;
	@Autowired
	MCompanyRepository mCompanyRepository;
	
	/**
	 * 
	 * @param form form
	 * @return companyDtoList 企業情報リスト
	 */
	public List<CompanyDto> getCompanyDtoList(Integer userId) {
		List<CompanyDto> companyDtoList = new ArrayList<CompanyDto>();
		List<MCompany> mCompanyList = new ArrayList<MCompany>();
		MLmsUser mLmsUser = mLmsUserRepository.getUserWithCompany(userId);
        
        mCompanyList = mCompanyRepository.findByAccountId(mLmsUser.getAccountId());
        
        for (MCompany mCompany : mCompanyList) {
        	CompanyDto companyDto = new CompanyDto();
        	BeanUtils.copyProperties(mCompany, companyDto);
        	companyDto.setCompanyName(mCompany.getCompanyName());
        	
            companyDtoList.add(companyDto);
        }

        return companyDtoList;
	}
	
//	public List<CompanyDto> getCompanyDtoSerchList(List<LmsUserDto> userList) {
//		List<CompanyDto> companyNameList = new ArrayList<CompanyDto>();
//		
//        for (int i = 0; i >= userList.size(); i++) {
//        	companyNameList = userList.
//        }
//
//        return companyDtoList;
//	}
	
//	public List<CompanyDto> getCompanyDtoList(CompanyAttendanceForm form) {
//		MLmsUser mLmsUser = new MLmsUser();
//		List<CompanyDto> companyDtoList = new ArrayList<CompanyDto>();
//		List<MCompany> mCompanyList = new ArrayList<MCompany>();
//
//        MCompany conditions = new MCompany();
//        if (StringUtils.isNumeric(form.getCompanyId())) {
//        	conditions.setCompanyId(Integer.parseInt(form.getCompanyId()));
//        }
//        
//        if(!Objects.isNull(conditions.getCompanyId()) || !StringUtils.isBlank(conditions.getCompanyName())) {
//        	mCompanyList = mCompanyRepository.findByCondition(conditions.getCompanyId(), conditions.getCompanyName());
//        }else{
//        	mCompanyList = mCompanyRepository.findByAccountId(mLmsUser.getAccountId());
//        }
//        
//        for (MCompany mCompany : mCompanyList) {
//        	CompanyDto companyDto = new CompanyDto();
//        	
//        	BeanUtils.copyProperties(mCompany, companyDto);
//            companyDtoList.add(companyDto);
//        }
//
//        return companyDtoList;
//	}
	
//	public List<CompanyDto> getCompanyDtoList() {
//        return this.getCompanyDtoList(new CompanyForm());
//    }
	
	
}
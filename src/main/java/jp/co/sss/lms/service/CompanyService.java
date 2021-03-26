package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jp.co.sss.lms.dto.CompanyDto;
import jp.co.sss.lms.entity.MCompany;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.repository.MCompanyRepository;
import jp.co.sss.lms.repository.MLmsUserRepository;

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
		
		//ユーザー情報取得
		MLmsUser mLmsUser = mLmsUserRepository.getUserWithCompany(userId);
        
		//アカウントIDで企業情報取得
        mCompanyList = mCompanyRepository.findByAccountId(mLmsUser.getAccountId());
        
        //企業情報を企業情報リストに格納
        for (MCompany mCompany : mCompanyList) {
        	CompanyDto companyDto = new CompanyDto();
        	BeanUtils.copyProperties(mCompany, companyDto);
        	companyDto.setCompanyName(mCompany.getCompanyName());
        	
            companyDtoList.add(companyDto);
        }

        //企業情報リストを返す
        return companyDtoList;
	}
	

}
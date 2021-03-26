package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.SubsidyCompanyUpdateDto;
import jp.co.sss.lms.entity.MCompany;
import jp.co.sss.lms.entity.MFssGroup;
import jp.co.sss.lms.entity.TCompanyCourse;
import jp.co.sss.lms.entity.TCompanyFssGroup;
import jp.co.sss.lms.entity.TAgreementConsent;
import jp.co.sss.lms.form.SubsidyCompanyUpdateForm;
import jp.co.sss.lms.repository.MCompanyRepository;
import jp.co.sss.lms.repository.MFssGroupRepository;
import jp.co.sss.lms.repository.TCompanyCourseRepository;
import jp.co.sss.lms.repository.TCompanyFssGroupRepository;

/**
 * クラス名 SubsidyCompanyUpdateService
 *
 * クラス概要 企業情報編集サービス
 *
 * @author Shin
 */

@Service
public class SubsidyCompanyUpdateService {

	@Autowired
	TCompanyCourseRepository tCompanyCourseRepository;
	@Autowired
	MCompanyRepository mCompanyRepository;
	@Autowired
	TCompanyFssGroupRepository tCompanyFssGroupRepository;
	@Autowired
	MFssGroupRepository mFssGroupRepository;

	/**
	 * 関数概要 企業情報の取得
	 *
	 * @param companyId 企業ID
	 * @return SubsidyCompanyUpdateDto 企業更新情報DTO
	 */
	public SubsidyCompanyUpdateDto get(Integer companyId) {
		SubsidyCompanyUpdateDto subsidyCompanyUpdateDto = new SubsidyCompanyUpdateDto();
		// 1件目が該当のデータ
		List<TCompanyCourse> tCompanyCourseList = tCompanyCourseRepository.getCompanyCourseAndAgreementConsent(companyId);
		if (!tCompanyCourseList.isEmpty()) {
			MCompany mCompany = tCompanyCourseList.get(0).getmCompany();
			TAgreementConsent tAgreementConsen = tCompanyCourseList.get(0).gettAgreementConsent();
			
			// レスポンスの設定
			subsidyCompanyUpdateDto.setCompanyId(mCompany.getCompanyId());
			subsidyCompanyUpdateDto.setCompanyName(mCompany .getCompanyName());
			subsidyCompanyUpdateDto.setCompanyNameKana(mCompany.getCompanyNameKana());
			subsidyCompanyUpdateDto.setPostNumber1(mCompany.getPostNumber1());
			subsidyCompanyUpdateDto.setPostNumber2(mCompany.getPostNumber2());
			subsidyCompanyUpdateDto.setPrefecture(mCompany.getPrefecture());
			subsidyCompanyUpdateDto.setAddress(mCompany.getAddress());            
			subsidyCompanyUpdateDto.setPhoneNumber1(mCompany.getPhoneNumber1());
			subsidyCompanyUpdateDto.setPhoneNumber2(mCompany.getPhoneNumber2());
			subsidyCompanyUpdateDto.setPhoneNumber3(mCompany.getPhoneNumber3());
			subsidyCompanyUpdateDto.setRepresentativePost(mCompany.getRepresentativePost());
			subsidyCompanyUpdateDto.setRepresentativeName(mCompany.getRepresentativeName());
			subsidyCompanyUpdateDto.setCapital(mCompany.getCapital());
			subsidyCompanyUpdateDto.setWorkerAmount(mCompany.getWorkerAmount());
			subsidyCompanyUpdateDto.setWorkStartTime(mCompany.getWorkStartTime());
			subsidyCompanyUpdateDto.setWorkEndTime(mCompany.getWorkEndTime());
			subsidyCompanyUpdateDto.setRestStartTime(mCompany.getRestStartTime());
			subsidyCompanyUpdateDto.setRestEndTime(mCompany.getRestEndTime());
			subsidyCompanyUpdateDto.setHoliday(mCompany.getHoliday());
			subsidyCompanyUpdateDto.setSubsidyPhoneNumber1(mCompany.getSubsidyPhoneNumber1());
			subsidyCompanyUpdateDto.setSubsidyPhoneNumber2(mCompany.getSubsidyPhoneNumber2());
			subsidyCompanyUpdateDto.setSubsidyPhoneNumber3(mCompany.getSubsidyPhoneNumber3());	    
			subsidyCompanyUpdateDto.setConsentFlg(tAgreementConsen.getConsentFlg());
		}
		return subsidyCompanyUpdateDto;
	}
	
	/**
	 * 関数概要 企業情報の更新
	 *
	 * @param SubsidyCompanyUpdateForm 企業情報form
	 */
	public SubsidyCompanyUpdateDto put(SubsidyCompanyUpdateForm subsidyCompanyUpdateForm) {
		SubsidyCompanyUpdateDto subsidyCompanyUpdateDtoo = new SubsidyCompanyUpdateDto();
		
		// TODO:DB相関チェック
		
		Date nowDate = new Date();
		
		// 企業情報の更新
		MCompany mCompany = mCompanyRepository.getOne(subsidyCompanyUpdateForm.getCompanyId());
		mCompany.setCompanyId(subsidyCompanyUpdateForm.getCompanyId());
		mCompany.setCompanyName(subsidyCompanyUpdateForm.getCompanyName());
		mCompany.setCompanyNameKana(subsidyCompanyUpdateForm.getCompanyNameKana());
		mCompany.setPostNumber1(subsidyCompanyUpdateForm.getPostNumber1());
		mCompany.setPostNumber2(subsidyCompanyUpdateForm.getPostNumber2());
		mCompany.setPrefecture(subsidyCompanyUpdateForm.getPrefecture());
		mCompany.setAddress(subsidyCompanyUpdateForm.getAddress());
		mCompany.setPhoneNumber1(subsidyCompanyUpdateForm.getPhoneNumber1());
		mCompany.setPhoneNumber2(subsidyCompanyUpdateForm.getPhoneNumber2());
		mCompany.setPhoneNumber3(subsidyCompanyUpdateForm.getPhoneNumber3());
		mCompany.setRepresentativePost(subsidyCompanyUpdateForm.getRepresentativePost());
		mCompany.setRepresentativeName(subsidyCompanyUpdateForm.getRepresentativeName());
		mCompany.setCapital(subsidyCompanyUpdateForm.getCapital());
		mCompany.setWorkerAmount(subsidyCompanyUpdateForm.getWorkerAmount());
		mCompany.setWorkStartTime(subsidyCompanyUpdateForm.getWorkStartTime());
		mCompany.setWorkEndTime(subsidyCompanyUpdateForm.getWorkEndTime());
		mCompany.setRestStartTime(subsidyCompanyUpdateForm.getRestStartTime());
		mCompany.setRestEndTime(subsidyCompanyUpdateForm.getRestEndTime());
		mCompany.setHoliday(subsidyCompanyUpdateForm.getHoliday());
		mCompany.setSubsidyPhoneNumber1(subsidyCompanyUpdateForm.getSubsidyPhoneNumber1());
		mCompany.setSubsidyPhoneNumber2(subsidyCompanyUpdateForm.getSubsidyPhoneNumber2());
		mCompany.setSubsidyPhoneNumber3(subsidyCompanyUpdateForm.getSubsidyPhoneNumber3());
		mCompany.setLastModifiedUser(subsidyCompanyUpdateForm.getLmsUserId());
		mCompany.setLastModifiedDate(nowDate);
		mCompanyRepository.save(mCompany);
		
		// 企業共有グループ紐づけテーブルの更新
		List<TCompanyFssGroup> tCompanyFssGroupList = tCompanyFssGroupRepository.findByCompanyId(subsidyCompanyUpdateForm.getCompanyId());
		for (TCompanyFssGroup entity : tCompanyFssGroupList) {
			entity.setLastModifiedUser(subsidyCompanyUpdateForm.getLmsUserId());
			entity.setLastModifiedDate(nowDate);
		}
		tCompanyFssGroupRepository.saveAll(tCompanyFssGroupList);
		
		// 企業共有グループマスタの更新
		List<MFssGroup> mFssGroupList = new ArrayList<MFssGroup>();
		for (TCompanyFssGroup entity : tCompanyFssGroupList) {
			MFssGroup mFssGroup = mFssGroupRepository.getOne(entity.getFssGroupId());
			mFssGroup.setLastModifiedUser(subsidyCompanyUpdateForm.getLmsUserId());
			mFssGroup.setLastModifiedDate(nowDate);
			mFssGroupList.add(mFssGroup);
		}
		mFssGroupRepository.saveAll(mFssGroupList);
		
		return subsidyCompanyUpdateDtoo;
	}
}
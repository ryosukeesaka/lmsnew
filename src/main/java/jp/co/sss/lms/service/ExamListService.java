package jp.co.sss.lms.service;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.ExamDto;
import jp.co.sss.lms.dto.ExamServiceExamDto;
import jp.co.sss.lms.dto.ExamServiceExamResultDto;
import jp.co.sss.lms.dto.ExamServiceQuestionDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.entity.MExam;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.MQuestion;
import jp.co.sss.lms.entity.TDailyReportFb;
import jp.co.sss.lms.entity.TExamResult;
import jp.co.sss.lms.entity.TExamResultDetail;
import jp.co.sss.lms.form.ExamForm;
import jp.co.sss.lms.form.ExamPlayForm;
import jp.co.sss.lms.repository.MExamRepository;
import jp.co.sss.lms.repository.ExamRepository;
import jp.co.sss.lms.repository.TExamResultDetailRepository;
import jp.co.sss.lms.repository.TExamResultRepository;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.DateUtil;
import jp.co.sss.lms.util.SettingUtil;

/**
 * 試験情報一覧サービス<br>
 * 
 * @author 牛久悟
 *
 */
@Service
public class ExamListService {
	
	@Autowired
	MExamRepository mExamRepository;
	@Autowired
	TExamResultRepository tExamResultRepository;
	@Autowired
	TExamResultDetailRepository tExamResultDetailRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	DateUtil dateUtil;

	LoginUserDto loginUserDto;
	@Autowired
	LoginService loginService;
	
	/**
	 * 試験ＤＴＯリスト取得処理
	 *
	 * @return 試験ＤＴＯリスト
	 */
	
	public List<ExamDto>getExamDtoList(ExamForm examForm){
		List<ExamDto> ExamDtoList = new ArrayList<ExamDto>();
		MExam mExamInfo = new MExam();
		if((examForm.getExamId()) != null) {
			mExamInfo.setExamId(examForm.getExamId());
		}
		mExamInfo.setExamName(examForm.getExamName());
		mExamInfo.setExamDescription(examForm.getExamDescription());
		
		//試験情報サービス．findByCondition
		List<MExam> mExamList = mExamRepository.findByCondition(mExamInfo.getExamId(), 
				mExamInfo.getExamName(), mExamInfo.getExamDescription());
		for(MExam mex:mExamList){
			ExamDto examDto = new ExamDto();
			BeanUtils.copyProperties(mex, examDto);
			examDto.setPublicFlg(false);
			if(mex.getTExamSection().getPublicDate().getTime() < new Date().getTime()) {
				examDto.setPublicFlg(true);
			} 
			BeanUtils.copyProperties(mex.getTExamSection().getMExam(), examDto);
			examDto.setSectionId(mex.getTExamSection().getMSection().getSectionId());
			examDto.setCourseId(mex.getTExamSection().getMSection().getMCourse().getCourseId());
			ExamDtoList.add(examDto);					
		}
		
		return ExamDtoList;
	}
	
    public List<ExamDto> getStudentExamDtoList(ExamForm examForm) {
        List<ExamDto> ExamDtoList = new ArrayList<ExamDto>();
        List<MExam> mExamList = new ArrayList<MExam>();
//
        if (loginUserDto.getRole().equals(Constants.CODE_VAL_ROLL_COMPANY) || loginUserDto.getRole().equals(Constants.CODE_VAL_ROLL_TRAINING )) {
            Timestamp closeTime = null;
            String examName = examForm.getExamName();
            if (examForm.getPastFlg() == null) {
        		Integer pasttime = Integer.parseInt(SettingUtil.getProperty("setting.search.pastTime"));
        		Date added = dateUtil.addMonth(new Date(), -1 *(pasttime));
        		closeTime = (Timestamp) dateUtil.getDateWithoutTime(added);

            }
            mExamList = mExamRepository.findByCompanyId(loginUserDto.getCompanyId(), closeTime, examName);
        } else {
            mExamList = mExamRepository.findByAccountId(loginUserDto.getPlaceId(), loginUserDto.getAccountId());
        }
		for(MExam mex:mExamList){
			ExamDto examDto = new ExamDto();
			BeanUtils.copyProperties(mex, examDto);
			examDto.setPublicFlg(false);
			if(mex.getTExamSection().getPublicDate().getTime() < new Date().getTime()) {
				examDto.setPublicFlg(true);
			} 
			BeanUtils.copyProperties(mex.getTExamSection().getMExam(), examDto);
			examDto.setSectionId(mex.getTExamSection().getMSection().getSectionId());
			examDto.setCourseId(mex.getTExamSection().getMSection().getMCourse().getCourseId());
			ExamDtoList.add(examDto);	
		}	

        return ExamDtoList;
    }
    
    public List<ExamDto> getExamDtoListFromSession(LoginUserDto sessionDto) {
        List<ExamDto> ExamDtoList = new ArrayList<ExamDto>();
        List<MExam> mExamList = null;

        mExamList = mExamRepository.findUniqueByAccountId();
        
		for(MExam mex:mExamList){
			ExamDto examDto = new ExamDto();
			BeanUtils.copyProperties(mex, examDto);
			examDto.setPublicFlg(false);
			if(mex.getTExamSection().getPublicDate().getTime() < new Date().getTime()) {
				examDto.setPublicFlg(true);
			} 
			BeanUtils.copyProperties(mex.getTExamSection().getMExam(), examDto);
			examDto.setSectionId(mex.getTExamSection().getMSection().getSectionId());
			examDto.setCourseId(mex.getTExamSection().getMSection().getMCourse().getCourseId());
			ExamDtoList.add(examDto);	
		}	
        
        return ExamDtoList;
    }
	
}

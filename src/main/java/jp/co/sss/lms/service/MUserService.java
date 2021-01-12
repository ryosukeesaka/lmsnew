package jp.co.sss.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.entity.MUser;
import jp.co.sss.lms.repository.MUserRepository;
//import lombok.RequiredArgsConstructor;

/**
 * ユーザーマスタサービス
 * @author Shimizu Takaaki
 */
@Service
//@RequiredArgsConstructor
public class MUserService {
	
	@Autowired
	MUserRepository mUserRepository;
	@Autowired
	LoginUserDto loginUserDto;
	
	/**
	 * セキュリティ同意フラグをDBに登録
	 * @return success!! 
	 */
	public void update() {
		MUser mUser = mUserRepository.getOne(loginUserDto.getUserId());
		mUser.setSecurityAgreeFlg((short) 1);
		mUserRepository.save(mUser);
		loginUserDto.setSecurityAgreeFlg((short) 1);
	}
}
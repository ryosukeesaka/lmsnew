package jp.co.sss.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.entity.MUser;
import jp.co.sss.lms.repository.MUserRepository;
//import lombok.RequiredArgsConstructor;
import jp.co.sss.lms.util.Constants;

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
	public void update(Integer userId) {
		MUser mUser = mUserRepository.getOne(userId);
		mUser.setSecurityAgreeFlg((short) 1);
		mUserRepository.save(mUser);
		loginUserDto.setSecurityAgreeFlg((short) 1);
	}
	
	
	   /**
     * 識別子でエンティティを検索します。
     *
     * @param userId
     *            識別子
     * @return エンティティ
     */
    public MUser findById(Integer userId) {
        //Where where = eq(deleteFlg(), Constants.DB_FLG_FALSE);
       // return select().where(where).id(userId).getSingleResult()
    	MUser m=new MUser();
        		return m;
    }
	
}
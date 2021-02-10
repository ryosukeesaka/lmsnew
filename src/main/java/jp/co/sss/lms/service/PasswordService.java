package jp.co.sss.lms.service;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.MUser;
import jp.co.sss.lms.entity.TTemporaryPassStorage;
import jp.co.sss.lms.form.LoginForm;
import jp.co.sss.lms.repository.MCompanyRepository;
import jp.co.sss.lms.repository.MLmsUserRepository;
import jp.co.sss.lms.repository.MPlaceRepository;
import jp.co.sss.lms.repository.MUserRepository;
import jp.co.sss.lms.repository.TCourseUserRepository;
import jp.co.sss.lms.repository.TTemporaryPassStorageRepository;
import jp.co.sss.lms.repository.TUserCompanyRepository;
import jp.co.sss.lms.repository.TUserPlaceRepository;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.DateUtil;
import jp.co.sss.lms.util.PasswordUtil;

/**
 * パスワード情報サービス
 * 
 * @author endo
 */
@Service
public class PasswordService {
	@Autowired
	TTemporaryPassStorageRepository tTemporaryPassStorageRepository;
	@Autowired
	MLmsUserRepository mLmsUserRepository;
	@Autowired
	TUserCompanyRepository tUserCompanyRepository;
	@Autowired
	TUserPlaceRepository tUserPlaceRepository;
	@Autowired
	TCourseUserRepository tCourseUserRepository;
	@Autowired
	MPlaceRepository mPlaceRepository;
	@Autowired
	MCompanyRepository mCompanyRepository;
	@Autowired
	MUserRepository mUserRepository;
	@Autowired
	HttpSession session;
	@Autowired
	DateUtil dateUtil;
	
	public TTemporaryPassStorage getfindByChangeKey(String changekey) {
		return tTemporaryPassStorageRepository.findByChangeKey(changekey);
	}

	public MLmsUser getfindByUserId(String changekey) {
		Integer UserId = (tTemporaryPassStorageRepository.findByChangeKey(changekey)).getUserId();
		return mLmsUserRepository.findByUserId(UserId);
	}
	
	/** 再設定用の一時テーブル登録
	 * @param mailaddress
	 * 
	 * @author sasaki
	 */
	public void registTemporaryPassStorage(String mailaddress) {
		
		// メールアドレスからユーザー情報を取得
		MUser mUser = mUserRepository.findByMailAddress(mailaddress);
		TTemporaryPassStorage tTemporaryPassStorage = (TTemporaryPassStorage)tTemporaryPassStorageRepository.findByUserId(mUser.getUserId());		
		
		// 再設定用変更キーの生成
		String key = PasswordUtil.generatePassword();
		
		// 一時テーブルに登録なければ新規登録
		if (tTemporaryPassStorage == null) {	
			tTemporaryPassStorage = new TTemporaryPassStorage();
			tTemporaryPassStorage.setChangeKey(key);	 //変更キー 	
			tTemporaryPassStorage.setTimeLimit(dateUtil.addHour(new Date(), Constants.LIMIT_TIME)); // 変更期限
			tTemporaryPassStorage.setUserId(mUser.getUserId()); 
			tTemporaryPassStorage.setDeleteFlg(0);  //削除フラグ Integer
			tTemporaryPassStorage.setLastModifiedUser(mUser.getUserId());
			tTemporaryPassStorage.setLastModifiedDate(new Date());
			tTemporaryPassStorage.setFirstCreateDate(new Date());
			tTemporaryPassStorage.setFirstCreateUser(mUser.getUserId());
		} else {
			// 取得できた場合、初回作成日時と初回作成者以外の更新
			tTemporaryPassStorage.setChangeKey(key);	 //変更キー 	
			tTemporaryPassStorage.setTimeLimit(dateUtil.addHour(new Date(), Constants.LIMIT_TIME)); // 変更期限
			tTemporaryPassStorage.setUserId(mUser.getUserId()); 
			tTemporaryPassStorage.setDeleteFlg(0);  //削除フラグ Integer
			tTemporaryPassStorage.setLastModifiedUser(mUser.getUserId());
			tTemporaryPassStorage.setLastModifiedDate(new Date());
		}
		
		// パスワード変更情報を更新
		tTemporaryPassStorageRepository.save(tTemporaryPassStorage);
		
		// ユーザーマスタを更新
        mUser.setPasswordChangeDate(null);
        mUser.setLastModifiedDate(new Date());
        mUser.setLastModifiedUser(mUser.getUserId());
        mUserRepository.save(mUser);
	}

	/**
	 * パスワード再設定URL有効期限を確認
	 * @param tTemporaryPassStorage
	 */
	
	public boolean isTimeLimitExpired(TTemporaryPassStorage tTemporaryPassStorage) {
		Date now = new Date();
		Date timeLimit = tTemporaryPassStorage.getTimeLimit();
		
		// 期限切れであれば一時テーブル情報を削除
		if (now.before(timeLimit) == false) {
		tTemporaryPassStorageRepository.delete(tTemporaryPassStorage);
		}
		
		return now.before(timeLimit);
	}
	
	/**
	 * パスワード変更情報（一時テーブル）を削除
	 * @param loginForm
	 */
	public void deleteByTemporaryPassStorageId(LoginForm loginForm) {
		
		// 一時テーブルの情報を取得
		int userId = loginForm.getUserId();
		TTemporaryPassStorage tTemporaryPassStorage = tTemporaryPassStorageRepository.findByUserId(userId);
		
		if (tTemporaryPassStorage != null) {
			tTemporaryPassStorageRepository.delete(tTemporaryPassStorage);
		}
	}
};
package jp.co.sss.lms.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.TTemporaryPassStorage;
import jp.co.sss.lms.repository.MCompanyRepository;
import jp.co.sss.lms.repository.MLmsUserRepository;
import jp.co.sss.lms.repository.MPlaceRepository;
import jp.co.sss.lms.repository.MUserRepository;
import jp.co.sss.lms.repository.TCourseUserRepository;
import jp.co.sss.lms.repository.TTemporaryPassStorageRepository;
import jp.co.sss.lms.repository.TUserCompanyRepository;
import jp.co.sss.lms.repository.TUserPlaceRepository;

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
	
	// TODO 全体的に処理の見直しが必要

	public TTemporaryPassStorage getfindByChangeKey(String changekey) {

		return tTemporaryPassStorageRepository.findByChangeKey(changekey);
	}

	public MLmsUser getfindByUserId(String changekey) {

		Integer UserId = (tTemporaryPassStorageRepository.findByChangeKey(changekey)).getUserId();

		return mLmsUserRepository.findByUserId(UserId);
	}

	public void getfindByUserId5(MLmsUser mLmsUser) {

		// TODO NG回数の処理はフロント側で実装する
//		// ログインLMSユーザーDTOを作成
//		LoginLmsUserDto loginLmsUserDto = new LoginLmsUserDto();
//
//		// ユーザーマスタ情報が取得できなかった場合ログインLMSユーザーDTO．パスワードNG回数 += 1
//		if (mLmsUser == null) {
//			loginLmsUserDto.setPasswordNgCount(+1);
//
//			return;
//
//		}

//		// ロールが受講生の場合
//		if (mLmsUser.getRole() == "0001") {
//
//			Integer userId = mLmsUser.getUserId();
//			Integer lmsUserId = (tTemporaryPassStorageRepository.findByUserId(userId)).getUserId();
//
//			Integer companyId = (tUserCompanyRepository.findByLmsUserId(lmsUserId)).getUserCompanyId();
//			Integer placeId = (tUserPlaceRepository.findByLmsUserId(lmsUserId)).getUserPlaceId();
//			Integer courseId = (tCourseUserRepository.findByLmsUserId(lmsUserId)).getMCourse().getCourseId();
//			Short supportAvailable = (mPlaceRepository.findByPlaceId(placeId)).getSupportAvailable();
//
//			loginLmsUserDto.setCompanyId(companyId);
//			loginLmsUserDto.setPlaceId(placeId);
//			loginLmsUserDto.setCourseId(courseId);
//			loginLmsUserDto.setSupportAvailable(supportAvailable);
//
//		}
//
//		// 管理者権限以外かつ講師権限以外の場合
//		if (mLmsUser.getRole().equals("0003") || mLmsUser.getRole().equals("0005")) {
//
//			Integer userid = mLmsUser.getUserId();
//
//			Integer lmsUserId = (tUserCompanyRepository.findByLmsUserId(userid)).getLmsUserId();
//			Integer companyId = (tUserCompanyRepository.findByLmsUserId(lmsUserId)).getUserCompanyId();
//			Short fileShareFlg = (mCompanyRepository.findByCompanyId(companyId)).getFileShareFlg();
//
//			loginLmsUserDto.setFileShareFlg(fileShareFlg);
//
//		}
//		return;

	}

	public void deleteByTemporaryPassStorageId() {

		// ログインIDを元にユーザーマスタ情報を取得
		Integer userId = (Integer) session.getAttribute("loginId");

		TTemporaryPassStorage tTemporaryPassStorage = tTemporaryPassStorageRepository.findByUserId(userId);

		if (tTemporaryPassStorage == null) {

			// ログイン画面を表示
			return;

		} else {

			tTemporaryPassStorageRepository
					.deleteByTemporaryPassStorageId(tTemporaryPassStorage.getTemporaryPassStorageId());

		}

	}

}
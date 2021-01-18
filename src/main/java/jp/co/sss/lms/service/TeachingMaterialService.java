package jp.co.sss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.entity.MCourse;
import jp.co.sss.lms.entity.TCourseTeachingMaterial;
import jp.co.sss.lms.entity.TCourseUser;
import jp.co.sss.lms.repository.MCourseRepository;
import jp.co.sss.lms.repository.TCourseTeachingMaterialRepository;
import jp.co.sss.lms.repository.TCourseUserRepository;

/**
 * 教材ダウンロードサービス
 * 
 * @author otake
 */

@Service
public class TeachingMaterialService {

	@Autowired
	TCourseTeachingMaterialRepository tCourseTeachingMaterialRepository;
	@Autowired
	MCourseRepository mCourseRepository;
	@Autowired
	TCourseUserRepository tCourseUserRepository;
	@Autowired
	LoginUserDto loginUserDto;

	/**
	 * コースIDの改竄チェック
	 * 
	 * @return true/false ログイン情報のコースIDとDBのコースIDが等しいならtrue,等しくないならfalse
	 */
	public boolean checkCourseId(Integer courseId, Integer lmsUserId) {

		// ユーザIDからDBに登録してあるコースIDを取得
		TCourseUser tCourseUser = tCourseUserRepository.findByLmsUserId(lmsUserId);
		Integer dbCourseId = tCourseUser.getMCourse().getCourseId();

		// ログイン情報のコースIDとDBのコースIDのチェック
		if (courseId != dbCourseId) {
			return false;
		}
		return true;
	}

	/**
	 * ログイン情報のコースIDからコース情報を取得
	 * 
	 * @return mCourseRepository.getOne(courseId)
	 */
	public MCourse getCourseId(Integer courseId) {
		return mCourseRepository.getOne(courseId);
	}

	/**
	 * ログイン情報のコースIDから教材情報を取得
	 * 
	 * @return tCourseTeachingMaterialRepository.findByCourseId(courseId)
	 */
	public List<TCourseTeachingMaterial> getCourseTeachingMaterialDownloadUrlDtoList(Integer courseId) {
		return tCourseTeachingMaterialRepository.findByCourseId(courseId);
	}

//	    /**教材：ファイルの存在チェックの結果だけを取得(納期に間に合わないためファイルの存在チェックは未実装)*/
//	    public boolean isFileExist() throws IOException {
//	    	    
//	    	boolean result = true;
//	    	return result;
//	    	
//	    }

	/** 現段階（2020年12月25日）でAWSが動いていないため、AWS関連の実装が不可（保留） */

}

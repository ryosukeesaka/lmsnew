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
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

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
	
	@Autowired
	AmazonS3 amazonS3;
	
	@Value("${app.awsServices.bucketName}")
	private String bucketName;

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
	/**
	 * リクエストされた教材ファイル名を取得、ダウンロード
	 */
	public byte[]  downloadFile(String fileName) {
		S3Object s3Object = amazonS3.getObject(bucketName,fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		try {
			byte[] content = IOUtils.toByteArray(inputStream);
			return content;
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

//	    /**教材：ファイルの存在チェックの結果を取得(納期に間に合わないためファイルの存在チェックは未実装)*/
//	    public boolean isFileExist() throws IOException {
//	    	    
//	    	boolean result = true;
//	    	return result;
//	    	
//	    }

	/** 現段階（2021年02月26日） */

}

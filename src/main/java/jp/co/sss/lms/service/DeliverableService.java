package jp.co.sss.lms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sss.lms.dto.DeliverableServiceDeliverablesWithSubmissionFlgDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.TDeliverablesResult;
import jp.co.sss.lms.entity.TDeliverablesSection;
import jp.co.sss.lms.repository.TDeliverablesResultRepository;
import jp.co.sss.lms.repository.TDeliverablesSectionRepository;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.LoggingUtil;
import jp.co.sss.lms.util.MessageUtil;

/**
 * 成果物情報サービス
 * 
 * @author 橋爪　優哉
 */
@Service
public class DeliverableService {
	
	@Autowired
	LoginUserDto loginUserDto;

	@Autowired
	TDeliverablesSectionRepository tDeliverablesSectionRepository;
	
	@Autowired
	TDeliverablesResultRepository tDeliverablesResultRepository;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	MessageUtil messageUtil;
	
	@Autowired
	LoggingUtil loggingUtil;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Entity情報をBeanに入れ替えるメソッド(成果物用)
	 * 
	 * @param tDeliverablesSection セクションエンティティ
	 * @return deliverableServiceDeliverableDto 成果物Dto
	 */
	public List<DeliverableServiceDeliverablesWithSubmissionFlgDto> getDeliverableWithSubmissionFlgDto(Integer sectionId,
			Integer lmsUserId) {

		List<TDeliverablesSection> mDeliverablesSectionList = tDeliverablesSectionRepository
				.getDeliverablesSubmissionFlg(sectionId, lmsUserId);

		// deliverableWithSubmissionFlgDtoListの作成
		List<DeliverableServiceDeliverablesWithSubmissionFlgDto> deliverableWithSubmissionFlgDtoList = new ArrayList<DeliverableServiceDeliverablesWithSubmissionFlgDto>();

		for (TDeliverablesSection tds : mDeliverablesSectionList) {

			DeliverableServiceDeliverablesWithSubmissionFlgDto deliverableWithSubmissionFlgDto = new DeliverableServiceDeliverablesWithSubmissionFlgDto();

			// SubmissionFlgの設定
			deliverableWithSubmissionFlgDto.setSubmissionFlg(Constants.DB_FLG_FALSE);
			for (int i = 0; i < tds.getTDeliverablesResultList().size(); i++) {
				if(tds.getTDeliverablesResultList().get(i).getMLmsUser().getLmsUserId() == lmsUserId) {
					deliverableWithSubmissionFlgDto.setSubmissionFlg(Constants.DB_FLG_TRUE);
				}
			}

			deliverableWithSubmissionFlgDto.setSubmissionDeadLine(tds.getSubmissionDeadline());
			BeanUtils.copyProperties(tds, deliverableWithSubmissionFlgDto);
			BeanUtils.copyProperties(tds.getMDeliverables(), deliverableWithSubmissionFlgDto);

			deliverableWithSubmissionFlgDtoList.add(deliverableWithSubmissionFlgDto);
		}

		return deliverableWithSubmissionFlgDtoList;
	}
	
	/**
	 * 成果物提出情報登録
	 * @param DeliverablesForm 成果物を格納しているフォーム
	 * @return 成果物登録成功の場合True 失敗の場合False
	 */
	public boolean deliverableUpload(MultipartFile multipartFile,String sectionId, String deliverableId,String lmsUserId,String accountId,String firstCreateUser,String lastModifiedUser) {
		
		//formの情報をEntityに格納する
		TDeliverablesResult tDeliverablesResult = covertFormToEntity(multipartFile,sectionId,deliverableId,lmsUserId,accountId,firstCreateUser,lastModifiedUser);
		
		//二重アップロード検証
		boolean isUpload = isDoubleTransmission(tDeliverablesResult);
		
		if (!isUpload) {
			try {
				//TODO AWSへのファイルアップロード　※AWS構築後実装
				//AWSS3Util.upload(uploadFile.getUploadFile(),tDeliverablesResult.getFilePath());
			}catch(Exception e) {
				throw e;
			}
			//データベースに成果物情報を登録
			tDeliverablesResultRepository.save(tDeliverablesResult);
		}
		return !isUpload;
	}
	
	/**
	 * Form情報をEntityに詰め替えるメソッド（成果物）
	 * @param sectionForm アップロードファイル情報
	 * @param loginUser ログイン中のユーザー情報
	 * @return 成果物Entityを返す
	 */
	private TDeliverablesResult covertFormToEntity(MultipartFile file, String sectionId, String deliverableId,String lmsUserId,String accountId,String firstCreateUser,String lastModifiedUser) {
		
		TDeliverablesResult tDeliverablesResult = new TDeliverablesResult();
		TDeliverablesSection tDeliverablesSection = new TDeliverablesSection();
		
		//成果物・セクション紐づけIDを設定
		tDeliverablesSection.setDeliverablesSectionId(Integer.parseInt(deliverableId));
		tDeliverablesResult.settDeliverablesSection(tDeliverablesSection);
		//ファイルパスを設定
		tDeliverablesResult.setFilePath("deliverablesFiles/" + tDeliverablesSection.getDeliverablesSectionId()+ "/" + lmsUserId +"/"+ file.getOriginalFilename());
		//ファイルサイズを設定
		tDeliverablesResult.setFileSize(file.getSize());
		//LMSユーザーIDを設定
		MLmsUser mLmsUser = new MLmsUser();
		mLmsUser.setLmsUserId(Integer.parseInt(lmsUserId));
		tDeliverablesResult.setMLmsUser(mLmsUser);
		//提出時間を設定
		tDeliverablesResult.setSubmissionTime(new Timestamp(new Date().getTime()));
		//企業アカウントを設定
		tDeliverablesResult.setAccountId(Integer.parseInt(accountId));
		//削除フラグを設定
		tDeliverablesResult.setDeleteFlg(Constants.DB_FLG_FALSE);
		//初回作成者を設定
		tDeliverablesResult.setFirstCreateUser(Integer.parseInt(firstCreateUser));
		//初回作成日を設定
		tDeliverablesResult.setFirstCreateDate(new Timestamp(new Date().getTime()));
		//最終更新者を設定
		tDeliverablesResult.setLastModifiedUser(Integer.parseInt(lastModifiedUser));
		//最終更新日を設定
		tDeliverablesResult.setLastModifiedDate(new Timestamp(new Date().getTime()));
	
		return tDeliverablesResult; 
	}
	
	/**
	 * 二重アップロード対策
	 * @param tDeliverablesResult
	 * @return 検索結果が空であればfalse, 検索結果があればTrue
	 */
	private boolean isDoubleTransmission(TDeliverablesResult tDeliverablesResult) {
		List<TDeliverablesResult> uploadedFiles = tDeliverablesResultRepository.findByFilePath(tDeliverablesResult.getFilePath());
		if(uploadedFiles.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 関数概要 成果物のエラーチェック
	 *
	 * @param deliverablesSectionId 成果物ID
	 * @return errorMessege エラーメッセージ
	 */
	public String checkDeliverablesInfo(MultipartFile file,String deliverableId,String deliverablesName,String lmsUserId) {
		
		//ファイルが未入力、アップロードしたファイルサイズが0である場合
		if(file.getSize() == 0 || file == null) {
			String[] values = { deliverablesName };
			return messageUtil.getMessage(Constants.VALID_KEY_REQUIRED, values);
		}
		//最大ファイルサイズをアップロードされたファイルが超過した場合
		else if(file.getSize() > Integer.parseInt(Constants.DELIVERABLES_UPLOAD_MAX_SIZE)) {
			String[] values = { Constants.DELIVERABLES_UPLOAD_MAX_SIZE };
			return messageUtil.getMessage(Constants.VALID_KEY_UPLOAD_SIZE , values);
		}
		
		TDeliverablesResult tDeliverablesResult = new TDeliverablesResult();
		// 成果物情報取得
		tDeliverablesResult = tDeliverablesResultRepository.findByDeliverablesSectionIdAndLmsUserId(Integer.parseInt(deliverableId),Integer.parseInt(lmsUserId));
		//提出済みの成果物がない場合
		if(tDeliverablesResult != null) {
			String[] values = { deliverablesName };
			return messageUtil.getMessage(Constants.VALID_KEY_ALREADY_EXISTS, values);
		}
		return "";
	}
	/**
	 * 成果物アップロードに失敗した場合にログ出力をするメソッド
	 */
	public String failUpload() {
		String[] values = {"deliverables"};
		//メッセージ生成
		StringBuffer sb = new StringBuffer(messageUtil.getMessage(Constants.VALID_KEY_ALREADY_EXISTS, values));
		loggingUtil.appendLog(sb);
		logger.info(sb.toString());
		
		return sb.toString();
	}
}

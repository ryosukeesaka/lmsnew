package jp.co.sss.lms.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sss.lms.util.DateUtil;
import jp.co.sss.lms.util.FileUtil;
import jp.co.sss.lms.entity.MFssUser;
import jp.co.sss.lms.entity.TFssFile;

import jp.co.sss.lms.dto.FileShareDto;
import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.repository.MFssGroupRepository;
import jp.co.sss.lms.repository.MFssUserRepository;
import jp.co.sss.lms.repository.TFssFileRepository;
import jp.co.sss.lms.repository.TFssShareAvailableRepository;
import jp.co.sss.lms.repository.TFssUserGroupRepository;

/**
 * ファイル一覧サービス ファイル共有画面のファイル一覧を取得するサービス ファイルアップロード、ダウンロードなど未実装
 * 
 * @author 田中 和希
 */
@Service
public class FileShareService {

	@Autowired
	MFssUserRepository mFssUserRepository;
	@Autowired
	TFssFileRepository tFssFileRepository;
	@Autowired
	MFssGroupRepository mFssGroupRepository;
	@Autowired
	TFssUserGroupRepository tFssUserGroupRepository;
	@Autowired
	TFssShareAvailableRepository tFssShareAvailableRepository;
	@Autowired
	DateUtil dateUtil;
	@Autowired
	FileUtil fileUtil;
	@Autowired
	LoginUserDto loginUserDto;

	/**
	 * ユーザーIDから共有ユーザーIDを取得し、セッションに設定する
	 * 
	 * @param UserId
	 * @return Integer
	 */
	public Integer getFssUserId(Integer UserId) {
		MFssUser mFssUser = (MFssUser) mFssUserRepository.findByUserId(UserId);
		return (Integer) mFssUser.getFssUserId();
	}

	/**
	 * ファイルのアップロードを行う 現在未完成
	 * 
	 * @param uploadFile
	 * @return boolean
	 */
	public boolean uploadFile(MultipartFile uploadFile){
		
		 //List<MFssUser> mFssUser = mFssUserRepository.findByUserId(loginUserDto.getLmsUserId());

	     // 二重アップロード対策（共有ユーザーを行ロック）
		 //mFssUserRepository.saveAll(mFssUserRepository.findByFssUserId(((MFssUser) mFssUser).getFssUserId()));

	     TFssFile tFssFile = new TFssFile();
	     
	     //ファイル名
	     //tFssFile.setFilePath(((MFssUser) mFssUser).getFssUserId() + "/" + uploadFile.getOriginalFilename());
	     tFssFile.setFilePath(uploadFile.getOriginalFilename());
	     
	     //サイズ
	     tFssFile.setFileSize((int) uploadFile.getSize());
	     
	     //所有者
	     //tFssFile.setOwnerFssUserId(((MFssUser) mFssUser).getFssUserId());
	     tFssFile.setOwnerFssUserId(25);

	     //削除フラグ
	     tFssFile.setDeleteFlg((short) 0);
	     
	     //共有ファイルID
	     tFssFile.setFssFileId(14);
	     
	     //現在日時取得
	     Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	     
	     //作成日付
	     tFssFile.setFirstCreateDate(timestamp);
	     
	     //更新日付
	     tFssFile.setLastModifiedDate(timestamp);

	     // 二重アップロード対策
	     List<TFssFile> alreadyExistsCheck = tFssFileRepository.findByFilePath(tFssFile.getFilePath());
	     if (alreadyExistsCheck != null == !alreadyExistsCheck.isEmpty()) {
	         return false;
	     }
	     //DB更新
	     tFssFileRepository.save(tFssFile);
	     
	     //ファイルアップロード
	     //AWSのUtilクラスに関しては時間が足りなかったため未実装
	     //AWSS3Util.upload(uploadFile, tFssFile.getFilePath());
	     return true;
	 }

	/**
	 * ファイルのリストを取得する
	 * 
	 * @param fssUserId
	 * @return List<FileShareDto>
	 */
	public List<FileShareDto> getFileList(Integer fssUserId) {
		List<FileShareDto> fileList = new ArrayList<>();

		Optional<MFssUser> mFssUser = mFssUserRepository.findById(fssUserId);
		List<TFssFile> tFssFileList = mFssUser.get().getTFssFileOwnerFileList();

		TFssFile bfrTFssFile = new TFssFile();
		for (TFssFile tFssFile : tFssFileList) {
			if (tFssFile.getFilePath().equals(bfrTFssFile.getFilePath())
					&& tFssFile.getMFssUserSharedFssUser() == null) {
				continue;
			}
			FileShareDto dto = new FileShareDto();

			File file = new File(tFssFile.getFilePath());

			// ◆ファイルID
			dto.setFileId(Integer.toString(tFssFile.getFssFileId()));

			// ◆ファイル名
			dto.setFileName(file.getName());

			// ◆変更日
			dto.setModifiedDate(dateUtil.dateToString(tFssFile.getLastModifiedDate(), "yyyy年MM月dd日 HH:mm"));

			// ◆ファイルタイプ
			dto.setFileType(fileUtil.getSuffix(file.getName()) + "ファイル");

			// ◆サイズ
			dto.setFileSize(fileUtil.parseFileSize(tFssFile.getFileSize()));

			// ◆所有者
			dto.setOwner(tFssFile.getMFssUserOwnerFssUser().getNickname());
			if (StringUtils.isEmpty(dto.getOwner())) {
				dto.setOwner(tFssFile.getMFssUserOwnerFssUser().getTUserFssUserList().get(0).getMUser().getUserName());
			}

			if (!tFssFile.getMFssUserOwnerFssUser().getFssUserId().equals(mFssUser.get().getFssUserId())) {
				dto.setSharedPerson(dto.getOwner());
			}

			// ◆共有者（共有されたユーザー）
			if (tFssFile.getMFssUserSharedFssUser() == null
					|| tFssFile.getMFssUserSharedFssUser().getFssUserId().equals(mFssUser.get().getFssUserId())) {
				dto.setSharePerson("");
			} else {
				dto.setSharePerson(tFssFile.getMFssUserSharedFssUser().getNickname());
				if (StringUtils.isEmpty(dto.getSharePerson())) {
					dto.setSharePerson(
							tFssFile.getMFssUserSharedFssUser().getTUserFssUserList().get(0).getMUser().getUserName());
				}
			}
			fileList.add(dto);

			bfrTFssFile = tFssFile;
		}
		return fileList;
	}

	/**
	 * フォルダの一覧を取得する
	 * 
	 * @param fssUserId
	 * @param groupName
	 * @return List<ShareUserDto>
	 */
	/*
	 * public List<ShareUserDto> getFolderList(Integer fssUserId, String groupName){
	 * List<ShareUserDto> shareUserDtoList = new ArrayList<>();
	 * 
	 * List<MFssUser> shareMFssUserList = new ArrayList<>();
	 * 
	 * shareMFssUserList.addAll(getGroupMember(groupName, fssUserId));
	 * 
	 * // ◆ユーザーリストからDTOにセットする for (MFssUser shareMFssUser : shareMFssUserList) {
	 * 
	 * ShareUserDto dto = new ShareUserDto();
	 * dto.setFssUserId(shareMFssUser.getFssUserId().toString());
	 * 
	 * dto.setFssGroupName(""); for (TFssUserGroup tFssUserGroup :
	 * shareMFssUser.gettFssUserGroupList()) { if
	 * (StringUtils.isEmpty(tFssUserGroup.getmFssGroup().getGroupName())) {
	 * continue; } if (!StringUtils.isEmpty(dto.getFssGroupName())) {
	 * dto.setFssGroupName(dto.getFssGroupName() + " | "); }
	 * dto.setFssGroupName(dto.getFssGroupName() +
	 * tFssUserGroup.getmFssGroup().getGroupName()); }
	 * 
	 * dto.setUserName(shareMFssUser.getNickname()); if
	 * (StringUtils.isEmpty(dto.getUserName())) {
	 * dto.setUserName(shareMFssUser.gettUserFssUserList().get(0).getmUser().
	 * getUserName()); }
	 * 
	 * if (!shareUserDtoList.contains(dto)) { shareUserDtoList.add(dto); } } return
	 * shareUserDtoList; }
	 */

	/**
	 * 共有可能なユーザーIDを取得する
	 * 
	 * @param fssUserId
	 * @return List<ShareUserDto>
	 */
	/*
	 * public List<ShareUserDto> getShareUserList(Integer fssUserId){
	 * List<ShareUserDto> shareUserDtoList = new ArrayList<>();
	 * 
	 * Optional<MFssUser> mFssUser = mFssUserRepository.findById(fssUserId);
	 * 
	 * List<MFssUser> shareMFssUserList = new ArrayList<>();
	 * 
	 * // ◆ユーザーが所属しているグループを取得
	 * shareMFssUserList.addAll(this.getGroupMember(fssUserId));
	 * 
	 * // ◆共有テーブルにあるユーザーを取得 List<TFssShareAvailable> tFssShareAvailableList =
	 * tFssShareAvailableRepository.findByFssUserId(mFssUser.get().getFssUserId());
	 * for (TFssShareAvailable tFssShareAvailable : tFssShareAvailableList) {
	 * shareMFssUserList.add(tFssShareAvailable.mFssUserShareFssUser); }
	 * 
	 * // ◆ユーザーリストからDTOにセットする for (MFssUser shareMFssUser : shareMFssUserList) { if
	 * (shareMFssUser.getFssUserId().equals(fssUserId)) { continue; } ShareUserDto
	 * dto = new ShareUserDto();
	 * 
	 * dto.setFssUserId(shareMFssUser.getFssUserId().toString());
	 * 
	 * dto.setFssGroupName(""); for (TFssUserGroup tFssUserGroup :
	 * shareMFssUser.gettFssUserGroupList()) { if
	 * (StringUtils.isEmpty(tFssUserGroup.getmFssGroup().getGroupName())) {
	 * continue; } if (!StringUtils.isEmpty(dto.getFssGroupName())) {
	 * dto.setFssGroupName(dto.getFssGroupName() + " | "); }
	 * dto.setFssGroupName(dto.getFssGroupName() +
	 * tFssUserGroup.getmFssGroup().getGroupName()); }
	 * 
	 * dto.setUserName(shareMFssUser.getNickname()); if
	 * (StringUtils.isEmpty(dto.getUserName())) {
	 * dto.setUserName(shareMFssUser.gettUserFssUserList().get(0).getmUser().
	 * getUserName()); }
	 * 
	 * if (!shareUserDtoList.contains(dto)) { shareUserDtoList.add(dto); } } return
	 * shareUserDtoList; }
	 */

	/**
	 * グループメンバーの取得
	 * 
	 * @param fssUserId
	 * @return List<MFssUser>
	 */
	/*
	 * private List<MFssUser> getGroupMember(Integer fssUserId){ return
	 * this.getGroupMember(null, fssUserId); }
	 */

	/**
	 * グループメンバーの取得
	 * 
	 * @param groupName
	 * @param fssUserId
	 * @return List<MFssUser>
	 */
	/*
	 * private List<MFssUser> getGroupMember(String groupName, Integer fssUserId) {
	 * //◆ユーザーが所属していて管理権限を持っているグループを取得 List<TFssUserGroup> adminTFssUserGroupList =
	 * tFssUserGroupRepository.findByFssUserIdAndAuth(fssUserId,
	 * Constants.CODE_VAL_GROUP_AUTH_ADMIN); if (adminTFssUserGroupList.size() == 0)
	 * { return new ArrayList<MFssUser>(); } Integer[] adminFssGroupIdArr = new
	 * Integer[adminTFssUserGroupList.size()]; for (int i = 0; i <
	 * adminFssGroupIdArr.length; i ++) { adminFssGroupIdArr[i] =
	 * adminTFssUserGroupList.get(i).getFssGroupId(); }
	 * 
	 * //◆管理者として所属しているグループのユーザーIDを全て取得 List<TFssUserGroup> memberTFssUserGroupList =
	 * tFssUserGroupRepository.findByFssGroupId(adminFssGroupIdArr); Integer[]
	 * memberFssUserIdArr = new Integer[memberTFssUserGroupList.size()]; for (int i
	 * = 0; i < memberFssUserIdArr.length; i++) { memberFssUserIdArr[i] =
	 * memberTFssUserGroupList.get(i).getFssUserId(); }
	 * 
	 * //◆検索条件に一致するユーザーを取得 Integer[] searchFssUserIdArr = new Integer[0];
	 * List<TFssUserGroup> searchTFssUserGroupList =
	 * tFssUserGroupRepository.findByFssGroupName(groupName);
	 * 
	 * int uidLoopSize = searchTFssUserGroupList.size(); for (int i = 0; i <
	 * uidLoopSize; i++) { if
	 * (Arrays.asList(memberFssUserIdArr).contains(searchTFssUserGroupList.get(i).
	 * getFssUserId())) { searchFssUserIdArr =
	 * (Integer[])ArrayUtils.addAll(searchFssUserIdArr,
	 * searchTFssUserGroupList.get(i).getFssUserId()); } } return
	 * mFssUserRepository.findByFssGroupIdArr(searchFssUserIdArr); }
	 */

	/**
	 * ファイルダウンロードのURLを取得 初期表示機能ではないためコメントアウト
	 * 
	 * @param fileId
	 * @param fssUserId
	 * @return String
	 */
	/*
	 * public String getDownloadUrl(String fileId, Integer fssUserId){ if (fssUserId
	 * == null) { return null; }
	 * 
	 * Optional<MFssUser> mFssUser = mFssUserRepository.findById(fssUserId);
	 * TFssFile tFssFile =
	 * tFssFileRepository.findByOwnerAndSharedUserAndId(Integer.parseInt(fileId),
	 * mFssUser.get().getFssUserId());
	 * 
	 * if (tFssFile == null) { return null; }
	 * 
	 * return AWSS3Util.makeDowloadUrl(tFssFile.getFilePath()); }
	 */

	/**
	 * 削除されているかチェック 現在未完成
	 * 
	 * @param fileIdArr
	 * @param fssUserId
	 * @return boolean
	 */
	public boolean isDeleteValid(String[] fileIdArr, Integer fssUserId){
		List<TFssFile> tFssFileList = tFssFileRepository.findByFssUserId(fssUserId);
		if (tFssFileList == null) { 
			return false;
		}
		Set<String> validFssFileIdSet = new HashSet<>();
		for (TFssFile tFssFile : tFssFileList){ 
			validFssFileIdSet.add(tFssFile.getFssFileId().toString());
		}
		for (String fileId : fileIdArr) {
			if (!validFssFileIdSet.contains(fileId)) {
				return false;
			} 
		} 
		return true;
	}
	 

	/**
	 * ファイルを削除する　現在未完成
	 * 
	 * @param fileIdArr
	 */
	public void delete(String[] fileIdArr, Integer fssUserId){ 
		if (fileIdArr == null || fileIdArr.length == 0) { 
			  return ; 
		} 
	    //List<String> deleteFilePathList = new ArrayList<>();
	    //◆共有ファイルIDを取得する
	    for (String fileId : fileIdArr) { 
	    	Optional<TFssFile> tFssFile =
	    			tFssFileRepository.findById(Integer.parseInt(fileId));
	    	//◆取得したファイルパスを基に共有ファイル情報リストを取得する
			List<TFssFile> deleteTFssFileList =
					tFssFileRepository.findByFilePath(tFssFile.get().getFilePath());
			//◆ファイルの削除を実行する
			for(TFssFile deleteTFssFile : deleteTFssFileList) {
				tFssFileRepository.delete(deleteTFssFile.getFssFileId(), fssUserId); 
			}
			//deleteFilePathList.add(tFssFile.get().getFilePath());
		}
	}

	/**
	 * グループを登録する 初期表示機能ではないためコメントアウト
	 * 
	 * @param groupName
	 * @param description
	 * @return integer
	 */
	/*
	 * public Integer insertGroup(String groupName, String description){ MFssGroup
	 * mFssGroup = new MFssGroup(); mFssGroup.setGroupName(groupName);
	 * mFssGroup.setDescription(description);
	 * 
	 * mFssGroupRepository.findByFssGroupId(mFssGroup.getFssGroupId()); return
	 * mFssGroup.getFssGroupId(); }
	 */

	/**
	 * グループを削除する 初期表示機能ではないためコメントアウト
	 * 
	 * @param groupId
	 * @return integer
	 */
	/*
	 * public Integer deleteGroup(Integer groupId){ Optional<MFssGroup> mFssGroup =
	 * mFssGroupRepository.findById(groupId); return
	 * mFssGroupRepository.deleteWithCommonField(mFssGroup); }
	 */

	/**
	 * グループを更新する 初期表示機能ではないためコメントアウト
	 * 
	 * @param groupId
	 * @param groupName
	 * @param description
	 * @return integer
	 */
	/*
	 * public Integer updateGroup(Integer groupId, String groupName, String
	 * description){ MFssGroup mFssGroup =
	 * mFssGroupRepository.findById(groupId).get();
	 * mFssGroup.setGroupName(groupName); mFssGroup.setDescription(description);
	 * return mFssGroupRepository.updateWithCommonField(mFssGroup); }
	 */

	/**
	 * ファイル共有ユーザーを登録する 初期表示機能ではないためコメントアウト
	 * 
	 * @param nickname
	 * @param maxAmount
	 * @param groupIdArr
	 * @param authArr
	 * @return integer
	 */
	/*
	 * public Integer insertFssUser(String nickname, Integer maxAmount, Integer[]
	 * groupIdArr, Short[] authArr){ // ◆ユーザーテーブルの登録 MFssUser mFssUser = new
	 * MFssUser(); mFssUser.setNickname(nickname);
	 * mFssUser.setMaxFileAmount(maxAmount);
	 * mFssUserRepository.insertWithCommonField(mFssUser);
	 * 
	 * // ◆グループ紐付けテーブルの登録（グループ n × 1 ユーザー） integer loopSize = groupIdArr.length; for
	 * ( integer i = 0; i < loopSize; i++ ) { TFssUserGroup tFssUserGroup = new
	 * TFssUserGroup(); tFssUserGroup.setFssUserId(mFssUser.getFssUserId());
	 * tFssUserGroup.setFssGroupId(groupIdArr[i]);
	 * tFssUserGroup.setAuth(authArr[i]);
	 * tFssUserGroupRepository.insertWithCommonField(tFssUserGroup); } return
	 * mFssUser.getFssUserId(); }
	 */

	/**
	 * ファイル共有ユーザーを削除する 初期表示機能ではないためコメントアウト
	 * 
	 * @param fssUserId
	 * @return integer
	 */
	/*
	 * public integer deleteFssUser(Integer fssUserId){ MFssUser deleteFssUser =
	 * mFssUserRepository.findByAllFssUserRelation(fssUserId).get(0);
	 * 
	 * // ◆グループとの紐付き削除
	 * tFssUserGroupRepository.deleteAll(deleteFssUser.gettFssUserGroupList());
	 * 
	 * // ◆共有可能ユーザー削除 tFssShareAvailableRepository.deleteAll(deleteFssUser.
	 * gettFssShareAvailableList());
	 * 
	 * // ◆被共有可能ユーザー削除 tFssShareAvailableRepository.deleteAll(deleteFssUser.
	 * tFssShareAvailableSharedList);
	 * 
	 * // ◆共有されたファイル削除（DBのみ）
	 * tFssFileRepository.deleteAll(deleteFssUser.gettFssFileSharedFileList());
	 * 
	 * // ◆作成したファイル削除(データも) String[] fileIdArr = new
	 * String[deleteFssUser.gettFssFileOwnerFileList().size()];
	 * tFssFileRepository.deleteAll(deleteFssUser.gettFssFileOwnerFileList());
	 * 
	 * for (integer i=0; i < fileIdArr.length; i++) { fileIdArr[i] =
	 * deleteFssUser.gettFssFileOwnerFileList().get(i).getFssFileId().toString(); }
	 * this.delete(fileIdArr);
	 * 
	 * // ◆共有ユーザー削除 return mFssUserRepository.deleteWithCommonField(deleteFssUser);
	 * }
	 */

	/**
	 * 共有できるかチェック 初期表示機能ではないためコメントアウト
	 * 
	 * @param shareFssFileIdArr
	 * @param toFssUserIdArr
	 * @param fssUserId
	 * @return boolean
	 */
	/*
	 * public boolean isShareValid(String[] shareFssFileIdArr, String[]
	 * toFssUserIdArr, Integer fssUserId){ // ◆共有可能ユーザー以外に共有しようとした場合
	 * List<ShareUserDto> shareUserDtoList = this.getShareUserList(fssUserId);
	 * Set<String> shareAbleUserIdSet = new HashSet<>(); for (ShareUserDto
	 * shareUserDto :shareUserDtoList) {
	 * shareAbleUserIdSet.add(shareUserDto.getFssUserId()); } for (String
	 * toFssUserId : toFssUserIdArr) { if (toFssUserId == null) { continue; } if
	 * (!shareAbleUserIdSet.contains(toFssUserId)) { return false; } }
	 * 
	 * // ◆所有者でないファイルを共有しようとした場合、 Integer[] intShareFssFileIdArr = new
	 * Integer[shareFssFileIdArr.length]; for (integer i = 0; i <
	 * intShareFssFileIdArr.length ; i ++) { intShareFssFileIdArr[i] =
	 * Integer.parseInt(shareFssFileIdArr[i]); }
	 * 
	 * List<TFssFile> tFssFileList =
	 * tFssFileRepository.findByManyId(intShareFssFileIdArr); for (TFssFile tFssFile
	 * :tFssFileList) { if (!tFssFile.getOwnerFssUserId().equals(fssUserId)) {
	 * return false; } } return true; }
	 */

	/**
	 * ファイルを共有する 初期表示機能ではないためコメントアウト
	 * 
	 * @param shareFssFileIdArr
	 * @param toFssUserIdArr
	 * @param fromFssUserId
	 */
	/*
	 * public void shareFileToOtherUser(String[] shareFssFileIdArr, String[]
	 * toFssUserIdArr, Integer fromFssUserId){ List<MFssUser> fromMFssUser =
	 * mFssUserRepository.findByUserId(fromFssUserId);
	 * 
	 * for (String shareFssFileId : shareFssFileIdArr) { boolean checknumSFFI =
	 * true; try { Integer.parseInt(shareFssFileId); }catch(Exception e){
	 * checknumSFFI = false; } if(checknumSFFI == false){ continue; }
	 * 
	 * Optional<TFssFile> fromTFssFile =
	 * tFssFileRepository.findById(Integer.parseInt(shareFssFileId));
	 * 
	 * for (String toFssUserId : toFssUserIdArr) { boolean checknumTFUI = true; try
	 * { Integer.parseInt(toFssUserId); }catch(Exception e){ checknumTFUI = false; }
	 * if(checknumTFUI == false){ continue; }
	 * 
	 * TFssFile toTFssFile = new TFssFile();
	 * toTFssFile.setOwnerFssUserId(((MFssUser) fromMFssUser).getFssUserId());
	 * toTFssFile.setSharedFssUserId(Integer.parseInt(toFssUserId));
	 * toTFssFile.setFilePath(fromTFssFile.get().getFilePath());
	 * toTFssFile.setFileSize(fromTFssFile.get().getFileSize());
	 * tFssFileRepository.insertWithCommonField(toTFssFile); } } }
	 */

	/**
	 * ファイル所有者であるかチェック 初期表示機能ではないためコメントアウト
	 * 
	 * @param fssUserId
	 * @param fssFileIdArr
	 * @return boolean
	 */
	/*
	 * public boolean isOwner(Integer fssUserId, String... fssFileIdArr){ Integer[]
	 * intFssFileIdArr = new Integer[0]; for (integer i = 0; i <
	 * fssFileIdArr.length; i++) { if (fssFileIdArr[i] == null) { continue; }
	 * boolean checknumFFIA = true; try { Integer.parseInt(fssFileIdArr[i]);
	 * }catch(Exception e){ checknumFFIA = false; } if(checknumFFIA == false){
	 * return false; }
	 * 
	 * intFssFileIdArr = (Integer[]) ArrayUtil.add(intFssFileIdArr,
	 * Integer.parseInt(fssFileIdArr[i])); } List<TFssFile> tFssFileList =
	 * tFssFileRepository.findByManyId(intFssFileIdArr); for (TFssFile tFssFile :
	 * tFssFileList) { if (!tFssFile.getOwnerFssUserId().equals(fssUserId)) { return
	 * false; } } return true; }
	 */

	/**
	 * ファイルの共有を削除する 初期表示機能ではないためコメントアウト
	 * 
	 * @param fssFileIdArr
	 */
	/*
	 * public void shareRelease(String[] fssFileIdArr){ for (String fssFileId :
	 * fssFileIdArr) { boolean checknumFFI = true; try {
	 * Integer.parseInt(fssFileId); }catch(Exception e){ checknumFFI = false; }
	 * if(checknumFFI == false){ continue; }
	 * 
	 * List<TFssFile> tFssFile =
	 * tFssFileRepository.findByFssFileId(Integer.parseInt(fssFileId));
	 * tFssFileRepository.deleteAll(tFssFile); } }
	 */
}

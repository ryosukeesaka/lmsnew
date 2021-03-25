package jp.co.sss.lms.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.PlaceDto;
import jp.co.sss.lms.dto.UserDetailDto;

import jp.co.sss.lms.dto.UserCourseCompanyPlaceBasicInfoDto;

import jp.co.sss.lms.service.PlaceService;
import jp.co.sss.lms.service.UserService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.LoggingUtil;
import jp.co.sss.lms.util.MessageUtil;

/**
 * @author 梶山
 * 
 * */

@RestController
@RequestMapping("/user/list")
public class UserListController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PlaceService placeService;
	@Autowired
	LoggingUtil loggingUtil;
	@Autowired
	MessageUtil messageUtil;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * lmsUserId、入力パラメーターから紐づくユーザー情報リストを取得する
	 * @author 梶山
	 * @param lmsuserId Integer
	 * @param String placeId
	 * @param String UserName
	 * @param String courseName
	 * @param String companyName
	 * @return ResponseEntity<>
	 * */
	@RequestMapping("")
	public ResponseEntity<Map<String, Object>> userList(@RequestParam("lmsUserId") Integer lmsUserId,
			@RequestParam("placeId") String placeId,
			@RequestParam("userName") String userName,
			@RequestParam("courseName") String courseName,
			@RequestParam("companyName") String companyName) {
		Map <String,Object> map = new HashMap<>();
		
		//placeIdが数値かどうか判定
		boolean placeIdIsNumber = true;
		try {
			Integer.parseInt(placeId);
		}catch(NumberFormatException e){
			placeIdIsNumber = false;
			String arr[] = {"placeName"};
			String message = messageUtil.getMessage(Constants.VALID_KEY_INVALID,arr);
			StringBuffer sb = new StringBuffer(message);
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
		}
		
		//placeIdが数値でなかった場合、ログインユーザーの会場情報から取得する
		if(! placeIdIsNumber) {
			//lmsUserIDをパラメータとしてログインユーザー情報を取得	
			UserDetailDto loginUser = userService.getUserDetailDto(lmsUserId);
			
			//placeIdにログインユーザーのplaceIdをセット
			try {
				placeId = ""+loginUser.getPlaceId();
				Integer.parseInt(placeId);
			}
			catch(NumberFormatException e) {
				//ログインユーザーの会場Idがなかった場合,
				return new ResponseEntity<>(map, HttpStatus.OK);
			}
		}
		
		//会場情報の取得
			PlaceDto placeDto = placeService.getPlaceDto(Integer.parseInt(placeId) );
			map.put("placeDto", placeDto);
		
		//検索条件からユーザー情報を取得
			Map<String,String> searchMap= new HashMap<>();
			map.put("placeId",placeId);
			map.put("userName",userName);
			map.put("companyName",companyName);
			map.put("courseName",courseName);
		List <UserCourseCompanyPlaceBasicInfoDto> userCourseCompanyPlaceBasicInfoDtoList = userService.getList(searchMap );
		map.put("userCourseCompanyPlaceBasicInfoDto", userCourseCompanyPlaceBasicInfoDtoList);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}

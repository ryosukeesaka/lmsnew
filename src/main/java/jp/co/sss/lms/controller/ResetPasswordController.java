package jp.co.sss.lms.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.MUser;
import jp.co.sss.lms.entity.TTemporaryPassStorage;
import jp.co.sss.lms.form.LoginForm;
import jp.co.sss.lms.form.MailAddressForm;
import jp.co.sss.lms.repository.MUserRepository;
import jp.co.sss.lms.repository.TTemporaryPassStorageRepository;
import jp.co.sss.lms.service.InfoService;
import jp.co.sss.lms.service.PasswordService;
import jp.co.sss.lms.service.UserService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.LoggingUtil;
import jp.co.sss.lms.util.MailUtil;

/**
 * パスワード再設定用コントローラークラス
 *
 * @author Yurina Otsuki
 */
@RestController
@RequestMapping("/password")
public class ResetPasswordController {

	@Autowired
	UserService userService;
	@Autowired
	MUserRepository repository;
	@Autowired
	LoggingUtil loggingUtil;
	@Autowired
	InfoService infoService;
	@Autowired
	HttpSession session;
	@Autowired
	MUserRepository mUserRepository;
	@Autowired
	TTemporaryPassStorageRepository tTemporaryPassStorageRepository;
	@Autowired
	protected PasswordService passwordService;
	@Autowired
	MailUtil mailUtil;
	@Autowired
	MessageSource messagesource;
		
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * パスワード再設定用メールアドレス送信画面（初期表示）
	 * 設定ファイル(Constants)のメール送信フラグの値を取得
	 */
	@RequestMapping(value="/getSendFlg", method = RequestMethod.POST)
	public ResponseEntity<Short> getSendFlg(){		
		Short sendFlg = Constants.SEND_FLG;
		return new ResponseEntity<>(sendFlg, HttpStatus.OK);
		}
		
	/**
	 * パスワード再設定用メールアドレス送信
	 * @param mailAddressForm
	 * @return userName, timeLimit 
	 * 
	 * @author sasaki
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> send(@RequestBody MailAddressForm mailAddressForm, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {

		// ユーザーマスタ情報の取得
		String mailaddress = mailAddressForm.getMailAddress();
		MUser mUser = userService.getMUser(mailaddress);
		Map<String, Object> map = new HashMap<>();
		
		// 入力されたメールアドレスが存在しない場合
		if (mUser == null) {
			StringBuffer sb = new StringBuffer("パスワードリセット画面で入力されたメールアドレスは存在しません。[" + mailaddress + "]");
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());

			map.put("message", "そのメールアドレスは存在しません");
			return new ResponseEntity<>(map, HttpStatus.OK);
		} 
				
		// パスワード一時テーブルの作成・変更
		passwordService.registTemporaryPassStorage(mailaddress);
			
		// 一時テーブルから変更キーの取得
		TTemporaryPassStorage tTemporaryPassStorage = (TTemporaryPassStorage)tTemporaryPassStorageRepository.findByUserId(mUser.getUserId());	
		if (tTemporaryPassStorage == null) {
			StringBuffer sb = new StringBuffer("パスワード再設定で不正なアクセスがありました。");
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		String changeKey = tTemporaryPassStorage.getChangeKey();

		// 変更キーのクエリ付き遷移先URLの作成
		String pathroot = request.getHeader("REFERER");
		String path = pathroot.replace("MailSend", "ChangePassword");
		String url = path + "?key=" + changeKey;
		
		// メール送信		
		String text = messagesource.getMessage("mail.resetpass.body", new String[] {url}, null);
		String subject = messagesource.getMessage("mail.resetpass.subject", null, null);
		mailUtil.sendMail(mailaddress, subject, text);
	
		// 取得した値をフロントに返却
		map.put("userName", mUser.getUserName());
		map.put("timeLimit", Constants.LIMIT_TIME);
				
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	/**
	 * パスワード再設定パスワード変更画面 初期表示
	 * 取得したchangeKeyからログインIDを取得する処理
	 * 
	 * @param changeKey
	 * @return map(loginUserDto, loginId)
	 */
	@RequestMapping(value = "/resetPasswordChangePasswordIndex", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> resetPasswordChangePasswordIndex(@RequestParam("changeKey") String changeKey){		
		
		Map<String, Object> map = new HashMap<>();
		
		// 変更キーからパスワード変更情報を取得
		TTemporaryPassStorage tTemporaryPassStorage = passwordService.getfindByChangeKey(changeKey);
		
		// パスワード変更情報が取得できなかった場合、ログ出力
		if (tTemporaryPassStorage == null) {
			StringBuffer sb = new StringBuffer("パスワード再設定で不正なアクセスがありました。");
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		// URLの有効期限を確認
		if (passwordService.isTimeLimitExpired(tTemporaryPassStorage) == false) {
			// 旧設計書ではURL無効エラー画面に遷移していたので、暫定エラーログ＋ログイン画面リダイレクトで対応
			StringBuffer sb = new StringBuffer("URL無効エラー");
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		// LMSユーザー情報を取得
		MLmsUser mLmsUser = passwordService.getfindByUserId(changeKey);
		
		// LMSユーザー情報が取得できなかった場合、フロント側でパスワードNGをカウント
		if (mLmsUser == null) {	
			// フロント側で処理するため、バックエンドは処理なしでOK（2020/2/3）
			return new ResponseEntity<>(map, HttpStatus.OK);
		}

		// ログインLMSユーザーDTOに値をセット
		LoginUserDto loginUserDto = userService.setLoginUserDto(mLmsUser);
		map.put("loginUser", loginUserDto);

		// ログインIDを取得
		MUser mUser = mUserRepository.getOne(tTemporaryPassStorage.getUserId());
		String loginId = mUser.getLoginId();
		map.put("loginId", loginId);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	/**
	 * 再設定用パスワード変更
	 * @param loginForm
	 */
	
	@RequestMapping(value = "/resetPasswordChangePassword", method = RequestMethod.POST)
	public void resetPasswordChangePassword(@RequestBody LoginForm loginForm) {
		
		// パスワード変更
		userService.changePasswordOfResetPassword(loginForm);
		
		// 一時テーブル情報の削除		
		passwordService.deleteByTemporaryPassStorageId(loginForm);

	}
}; 
package jp.co.sss.lms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.entity.MUser;
import jp.co.sss.lms.entity.TTemporaryPassStorage;
import jp.co.sss.lms.form.MailAddressForm;
import jp.co.sss.lms.repository.MUserRepository;
import jp.co.sss.lms.repository.TTemporaryPassStorageRepository;
import jp.co.sss.lms.service.InfoService;
import jp.co.sss.lms.service.PasswordService;
import jp.co.sss.lms.service.UserService;
import jp.co.sss.lms.util.LoggingUtil;
import jp.co.sss.lms.util.MessageUtil;

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
	private MessageUtil messageUtil;
	@Autowired
	HttpSession session;
	@Autowired
	MUserRepository mUserRepository;
	@Autowired
	TTemporaryPassStorageRepository tTemporaryPassStorageRepository;
	@Autowired
	protected PasswordService passwordService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	// TODO 全体的に処理の見直しが必要。PasswordServiceについても同様
	/**
	 * resetPasswordからcompleteへの遷移 ※送信ボタン押下時
	 * 
	 * @return completeへの遷移
	 */
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> complete(@RequestBody MailAddressForm mailAddressForm) {

		// ユーザーマスタ情報の取得
		String mailaddress = mailAddressForm.getMailAddress();
		MUser mUser = userService.getMUser(mailaddress);
		Map<String, Object> map = new HashMap<>();

		// 入力されたメールアドレスが存在しない場合、次の処理を行わず、パスワード再設定画面1へ遷移。
		if (mUser == null) {
			StringBuffer sb = new StringBuffer("パスワードリセット画面で入力されたメールアドレスは存在しません。");
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());

			map.put("message", "そのメールアドレスは存在しません");

			return new ResponseEntity<>(map, HttpStatus.OK);
		}

		// 取得した情報パラメータとしてサービスを呼び出しパスワード変更情報を取得する｡
		TTemporaryPassStorage tTemporaryPassStorage = userService.getPassStorage(mailaddress);

		// パスワード変更情報の登録・更新
		userService.insertTTemporaryPassStorage(tTemporaryPassStorage);

		// ユーザーマスタ情報を更新
		userService.setMUser(mUser);

		// 更新した状態でのユーザーマスタ情報を取得
		MUser mUserUpdate = userService.getMUser(mailaddress);

		// ユーザーマスタ情報が取得できなかった場合、ログ出力
		if (mUserUpdate == null) {
			StringBuffer sb = new StringBuffer("パスワードリセット画面で入力されたメールアドレスは存在しません。");
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());

			map.put("message", "そのメールアドレスは存在しません");
			
			return new ResponseEntity<>(map, HttpStatus.OK);
		}

		// 更新した状態でのパスワード変更情報を取得
		TTemporaryPassStorage tTemporaryPassStorageUpdate = userService.getPassStorage(mailaddress);

		// パスワード変更情報が取得できなかった場合、ログ出力しパスワード再設定画面1へ遷移
		if (tTemporaryPassStorageUpdate == null) {
			StringBuffer sb = new StringBuffer("パスワード再設定で不正なアクセスがありました。");
			loggingUtil.appendLog(sb);
			logger.info(sb.toString());
			
			map.put("message", "パスワード再設定で不正なアクセスがありました。");

			return new ResponseEntity<>(map, HttpStatus.OK);
		}

		// 宛先の設定
		// String to = mailaddress;

		// 件名の設定
		// String subject = messageUtil.getMessage(Constants.PROP_KEY_MAIL_RESETPASS_SUBJECT);

		// 本文の設定
		// ※本文の設定の処理は時間がなかったため、未実装。
		//   本機能実装時にはメールが送信できる環境が整ってないため、それまでに実装すること。
		//   2021/01/04 naraoka

		// メール送信
		// 現在は飛ばない為、コメントアウト
		//	MailUtil.sendResetPasswordMail(to, subject, body);

		// パスワード再設定画面2を表示
		map.put("userName", mUserUpdate.getUserName());
		map.put("timeLimit", tTemporaryPassStorageUpdate.getTimeLimit());

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

//	/**
//	 * パスワード再設定用メールに添付されているアドレスをクリック後
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "changePassword")
//	public String changePassword(Model model, @ModelAttribute CheckPasswordForm form) {
//
//		// 変更キー取得（※設定箇所が無い為、保留）
//		String changekey = "123";
//
//		// 変更キーをパラメータとしてパスワード変更情報を取得
//		TTemporaryPassStorage tTemporaryPassStorage3 = passwordService.getfindByChangeKey(changekey);
//
//		// パスワード変更情報が取得できなかった場合、ログ出力
//		// ログの出力は出来ない為、コメントアウト
//		if (tTemporaryPassStorage3 == null) {
//		//	logger.warning("パスワード再設定で不正なアクセスがありました。");
//			return "/password/changePassword";
//		}
//
//		// LMSユーザーマスタ情報を取得
//		MLmsUser mLmsUser = passwordService.getfindByUserId(changekey);
//
//		// LMSユーザーマスタ情報の登録・更新
//		passwordService.getfindByUserId5(mLmsUser);
//
//		// パスワード再設定用パスワード変更画面の表示
//		model.addAttribute("resetPasswordUserId", mLmsUser.getUserId());
//		return "/password/changePassword";
//	}
//
//	/**
//	 * changePasswordモーダルからの遷移
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "index5", method = RequestMethod.POST)
//	public String index(@ModelAttribute CheckPasswordForm form, BindingResult result, Model model) {
//
//		// パスワード照合
//		String newPass = form.getNewPassword();
//		String checkPass = form.getCheckPassword();
//		if (!newPass.equals(checkPass)) {
//			model.addAttribute("password", "新しいパスワードと確認パスワードが一致しません。");
//			return "/password/changePassword";
//		}
//
//		// ユーザーマスタ情報更新
//		userService.getUserId(newPass);
//
//		// パスワード情報サービスの削除
//		passwordService.deleteByTemporaryPassStorageId();
//
//		// セッション情報の削除
//		session.removeAttribute("loginId");
//
//		// ログイン画面を表示
//		return "redirect:/";
//	}
//	
//	/**
//	 * パスワード再設定用パスワード変更画面で変更ボタン押下
//	 * 
//	 * @return コース詳細画面
//	 */
//	@RequestMapping(value = "/changeOfResetPassword", method = RequestMethod.POST)
//	public String changeOfResetPassword(@ModelAttribute LoginForm loginForm,
//			BindingResult result, Model model) {
//		// 変更後のパスワードと確認パスワードが一致してるかチェック
//		// 不一致ならエラーにする。
//
//		// 相関チェック
//		result = userService.changePasswordOfResetPassword(loginForm, result);
//		if (result.hasErrors()) {
//			return "/password/changePassword";
//		}
//
//		return "redirect:/course/detail";
//	}
}

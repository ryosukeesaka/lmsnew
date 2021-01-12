package jp.co.sss.lms.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {
	@Autowired
	private MessageSource messageSource;
	
	// 第二引数なし
	public String getMessage(String messageId) {
		String message = messageSource.getMessage(messageId, null, Locale.getDefault());
		if (message == null || message.equals("")) {
			return "";
		}
		return message;
	}
	
	// 第二引数あり
	public String getMessage(String messageId, String[] values) {
		String message = messageSource.getMessage(messageId, values, Locale.getDefault());
		if (message == null || message.equals("")) {
			return "";
		}
		return message;
	}

}

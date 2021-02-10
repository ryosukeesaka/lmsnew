package jp.co.sss.lms.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Component
public class MailUtil {
	@Autowired
	private MailSender mailSender;
	
	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private String port;
	@Value("${spring.mail.password}")
	private String senderPassword;
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String auth;
	@Value("${spring.mail.sendername}")
	private String senderName;
	@Value("${spring.mail.username}")
	private String senderAddress;
	
	/**
	 * 宛先、件名、本文を設定してメール送信
	 * @param to  // 宛先(単一)
	 * @param subject  // 件名
	 * @param text  // 本文
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException
	 */
	@Async
	public void sendMail(String to, String subject, String text) throws MessagingException, UnsupportedEncodingException {
		
		// メールプロパティとセッション作成
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", port);
		props.setProperty("mail.smtp.auth", auth);
		Session session = Session.getInstance(props);
		
        MimeMessage mimeMessage = new MimeMessage(session);
        Transport transport = null;

		try {
			// 送信者設定
	        InternetAddress from = new InternetAddress(senderAddress, senderName);
	        mimeMessage.setFrom(from);
	        
	        mimeMessage.setRecipients(Message.RecipientType.TO, to);
	        mimeMessage.setSubject(subject);
	        mimeMessage.setText(text);
	        
	        // SMTPでメール送信
	        transport = session.getTransport("smtp");
            transport.connect(null, senderAddress, senderPassword); // 送信アドレスとパスワードを指定           
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            if (transport != null) {
                transport.close();
            }
		}
	}

	@Async
	public void sendMail(String to, String[] cc, String subject, String text) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setCc(cc);
		msg.setSubject(subject);
		msg.setText(text);
		mailSender.send(msg);
	}
}

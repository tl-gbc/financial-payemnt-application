
package utilities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgotPasswordEmail {
	public static void sendPassword(String fromEmail, String password_email, String toEmail, String password) {
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", "smntp.gmail.com");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(true);

			Message mailMessage = new MimeMessage(mailSession);
			mailMessage.setFrom(new InternetAddress(fromEmail));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mailMessage.setSubject("Your password from CT Financial Company");
			
			String confirmation_message = "Hi," + "<br><br>"
					+ "This is your password: " + password + "." + "<br><br>" 
					+ "Please " + "<a href=http://localhost:8080/COMP3095_V/login.jsp>click here</a>" + " to login." + "<br><br>"
					+ "Sincerely, " + "<br><br>" 
					+ "CT Financial Company"; 
			mailMessage.setContent(confirmation_message, "text/html");
			
			Transport transport = mailSession.getTransport("smtp");
			transport.connect("smtp.gmail.com", fromEmail, password_email);
			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}			
	}
}


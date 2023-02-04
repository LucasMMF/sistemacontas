package br.com.cotiinformatica.messages;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import br.com.cotiinformatica.dtos.EmailDTO;

public class EmailMessage {

	// Atributos
	private static final String ACCOUNT = "cotiaulajava@outlook.com";
	private static final String PASSWORD = "@Admin123456";
	private static final String SMTP = "smtp-mail.outlook.com";
	private static final Integer PORT = 587;
	
	// Método estático para envio de emails
	public static void sendMessage(final EmailDTO emailDTO) throws Exception {
		
		// Conta que irá fazer o envio dos emails deste sistema
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setUsername(ACCOUNT);
		sender.setPassword(PASSWORD);
		sender.setHost(SMTP);
		sender.setPort(PORT);
		
		// Configurações necessárias para envio do email
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.transport.protocol", "smtp");
		
		sender.setJavaMailProperties(properties);
		
		// Montando o conteúdo do email
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
				helper.setFrom(ACCOUNT);
				helper.setTo(emailDTO.getMailTo());
				helper.setSubject(emailDTO.getSubject());
				helper.setText(emailDTO.getBody());
			}
		};
		
		// Enviando o email
		sender.send(preparator);
	}
	
}

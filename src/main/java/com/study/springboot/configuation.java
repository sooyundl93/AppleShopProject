package com.study.springboot;
import java.text.SimpleDateFormat;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Configuration
public class configuation {
	
	@Autowired
	JavaMailSender mailSender;
	
	@Bean
	public MimeMessage message() {
		return mailSender.createMimeMessage();
	}

	@Bean
	public MimeMessageHelper messageHelper(MimeMessage message) throws MessagingException {
		return new MimeMessageHelper(message, true, "UTF-8");
	}
	
	@Bean
	public SimpleDateFormat dateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
//	@Bean
//	public DecimalFormat thousandsSeparator() {
//		return new DecimalFormat("#,##0");
//	}
//	
//	@Bean
//	public SimpleDateFormat yearFormat() {
//		return new SimpleDateFormat("yyyy");
//	}
//	
//	@Bean
//	public SimpleDateFormat memberSinceFormat() {
//		return new SimpleDateFormat("yyyy년 MM월 dd일 yy시 mm분");
//	}
}

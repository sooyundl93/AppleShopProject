package com.study.springboot;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	
	private String from;				// 보내는 이메일 주소
	private String to;					// 받는 이메일 주소
	private String subject;				// 이메일 제목
	private String contents;			// 이메일 내용
	
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	MimeMessage message;
	@Autowired
	MimeMessageHelper messageHelper;
	
//	##### 메일 설정 #####
	public void setSendMail(
			String from,
			String to,
			String subject,
			String contents
		) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.contents = contents;
	}

//	##### 메일 전송 #####
	public Boolean sendMail() {
			try {
	    		messageHelper.setFrom(from);  		// 보내는 사람 설정
	    		messageHelper.setTo(to); 			// 받는 사람 설정
	    		messageHelper.setSubject(subject); 	// 메일 제목 설정
	    		messageHelper.setText(contents);  	// 메일 내용 설정
				mailSender.send(message);			// 메일 송신
				return true;
			} catch (MessagingException e) {
				e.printStackTrace();
				return false;
			}
	}
	
//	##### 전송된 내용 체크 #####
	public String getContents() {
		return contents;
	}
}

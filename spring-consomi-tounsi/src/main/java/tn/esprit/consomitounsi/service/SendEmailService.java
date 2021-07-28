package tn.esprit.consomitounsi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public ResponseEntity<?> sendEmail(String to, String body, String topic){
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setFrom("consomitounsi.test@gmail.com");
		simpleMailMessage.setSubject(topic);
		simpleMailMessage.setText(body);
		
		javaMailSender.send(simpleMailMessage);
		System.out.println("email sendedgbfdgbd");
		return new ResponseEntity<>(body,HttpStatus.OK);
	}

}

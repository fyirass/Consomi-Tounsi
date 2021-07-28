package tn.esprit.consomitounsi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.consomitounsi.service.SendEmailService;


@RestController
@RequestMapping("mail") 
public class SendEmailController {
	@Autowired
	private SendEmailService sendEmailService;
	@GetMapping(value="/sendmail")
	public ResponseEntity<?> sendmail(){
		return sendEmailService.sendEmail("firas.mansour@esprit.tn",  "mail successfully sended", "Test");
		 
	}
	
}

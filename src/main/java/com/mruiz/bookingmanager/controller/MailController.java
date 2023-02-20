package com.mruiz.bookingmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mruiz.bookingmanager.payload.EmailDetails;
import com.mruiz.bookingmanager.service.EmailService;

@RestController
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send")
	public ResponseEntity<?> send(@RequestBody EmailDetails emailDetails){
		emailService.sendSimpleMessage(emailDetails);
		return ResponseEntity.ok(null);
	}
}

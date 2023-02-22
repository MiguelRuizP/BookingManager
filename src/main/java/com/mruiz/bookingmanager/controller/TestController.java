package com.mruiz.bookingmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mruiz.bookingmanager.entity.User;
import com.mruiz.bookingmanager.payload.SimpleMessageDto;
import com.mruiz.bookingmanager.service.TokenService;

@RestController
public class TestController {
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/test")
	public ResponseEntity<?> test(Authentication authentication){
		User user = tokenService.getUserFromAuthorities(authentication);
		return ResponseEntity.ok(new SimpleMessageDto("test " + user.getUsername()));
	}
}

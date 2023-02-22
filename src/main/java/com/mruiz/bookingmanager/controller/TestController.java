package com.mruiz.bookingmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mruiz.bookingmanager.payload.SimpleMessageDto;

@RestController
public class TestController {
	
	@PostMapping("/test")
	public ResponseEntity<?> test(){
		return ResponseEntity.ok(new SimpleMessageDto("test"));
	}
}

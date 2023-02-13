package com.mruiz.bookingmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@PostMapping("/test")
	public ResponseEntity<?> test(){
		return ResponseEntity.ok("test ok");
	}
}

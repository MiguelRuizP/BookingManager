package com.mruiz.bookingmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mruiz.bookingmanager.payload.SimpleMessageDto;
import com.mruiz.bookingmanager.repository.BookingRepository;

@RestController
public class TestController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@PostMapping("/test")
	public ResponseEntity<?> test(){
		int id = bookingRepository.findUnnotified().get(0).getId();
		return ResponseEntity.ok(new SimpleMessageDto("test " + id));
	}
}

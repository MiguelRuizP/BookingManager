package com.mruiz.bookingmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mruiz.bookingmanager.entity.Booking;
import com.mruiz.bookingmanager.entity.User;
import com.mruiz.bookingmanager.payload.CreateBookingDto;
import com.mruiz.bookingmanager.payload.BookingListDto;
import com.mruiz.bookingmanager.payload.SimpleMessageDto;
import com.mruiz.bookingmanager.repository.BookingRepository;
import com.mruiz.bookingmanager.service.TokenService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/book")
	public ResponseEntity<?> book(Authentication authentication, 
			@RequestBody CreateBookingDto bookingDto){
		Booking booking = new Booking();
		User user = tokenService.getUserFromAuthorities(authentication);
		
		booking.setUserId(user.getId());
		booking.setDate(bookingDto.getDate());
		booking.setActive(true);
		booking.setNotified(false);
		
		bookingRepository.save(booking);
		
		return ResponseEntity.ok(new SimpleMessageDto("Reserva creada con éxito!!!"));
	}
	
	@PostMapping("/getBookings")
	public ResponseEntity<?> getBookings(Authentication authentication){
		User user = tokenService.getUserFromAuthorities(authentication);
		List<Booking> bookings = bookingRepository.findByUserId(user.getId());
		return ResponseEntity.ok(new BookingListDto(bookings));
	}
}

package com.mruiz.bookingmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mruiz.bookingmanager.Scheduler;
import com.mruiz.bookingmanager.entity.Booking;
import com.mruiz.bookingmanager.entity.User;
import com.mruiz.bookingmanager.payload.BookingDateDto;
import com.mruiz.bookingmanager.payload.BookingListDto;
import com.mruiz.bookingmanager.payload.ChangeBookingActiveDto;
import com.mruiz.bookingmanager.payload.SimpleMessageDto;
import com.mruiz.bookingmanager.repository.BookingRepository;
import com.mruiz.bookingmanager.service.TokenService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private Scheduler scheduler;
	
	@PostMapping("/book")
	public ResponseEntity<?> book(Authentication authentication, 
			@RequestBody BookingDateDto bookingDto){
		Booking booking = new Booking();
		User user = tokenService.getUserFromAuthorities(authentication);
		
		booking.setUserId(user.getId());
		booking.setDate(bookingDto.getDate());
		booking.setActive(true);
		booking.setNotified(false);
		
		bookingRepository.save(booking);
		
		scheduler.emailAlertJob();
		
		return ResponseEntity.ok(new SimpleMessageDto("Reserva creada con éxito!!!"));
	}
	
	@PostMapping("/getBookings")
	public ResponseEntity<?> getBookings(Authentication authentication){
		User user = tokenService.getUserFromAuthorities(authentication);
		List<Booking> bookings = bookingRepository.findByUserId(user.getId());
		return ResponseEntity.ok(new BookingListDto(bookings));
	}
	
	@PostMapping("/getBookingsDate")
	public ResponseEntity<?> getBookingsDate(Authentication authentication,
			@RequestBody BookingDateDto createBookingDto){
		User user = tokenService.getUserFromAuthorities(authentication);
		List<Booking> bookings = bookingRepository.findByUserIdAndDate(user.getId(), createBookingDto.getDate());
		return ResponseEntity.ok(new BookingListDto(bookings));
	}
	
	@Transactional
	@PostMapping("/setActive")
	public ResponseEntity<?> setActive(@RequestBody ChangeBookingActiveDto dto){
		bookingRepository.setActive(dto.getId(), dto.isActive());
		
		return ResponseEntity.ok(new SimpleMessageDto("Reserva " + dto.getId() + " actualizada correctamente!!!"));
	}
	
	@Transactional
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody ChangeBookingActiveDto dto){
		bookingRepository.deleteById(dto.getId());
		
		return ResponseEntity.ok(new SimpleMessageDto("Reserva " + dto.getId() + " borrada correctamente!!!"));
	}
	
}

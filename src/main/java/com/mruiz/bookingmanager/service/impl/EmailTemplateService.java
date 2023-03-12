package com.mruiz.bookingmanager.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mruiz.bookingmanager.entity.Booking;
import com.mruiz.bookingmanager.entity.User;
import com.mruiz.bookingmanager.payload.EmailDetails;
import com.mruiz.bookingmanager.repository.UserRepository;
import com.mruiz.bookingmanager.service.EmailService;

import jakarta.mail.SendFailedException;

@Service
public class EmailTemplateService {
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepository userRepository;
	
	public void alertBooking(Booking booking) throws SendFailedException {
		User user = userRepository.findById(booking.getUserId()).get();
		String username = user.getUsername();
		String subject = "Recordatorio de tu reserva";
		
		String day = new SimpleDateFormat("dd-MM-yyyy").format(booking.getDate());
		String time = new SimpleDateFormat("HH:mm:ss").format(booking.getDate());
		String msgBody = "Hola " + username + ", le recordamos su reserva para las "
				+ time + " del " + day;
		
		EmailDetails email = new EmailDetails(user.getEmail(), msgBody, subject);
		emailService.sendSimpleMessage(email);
	}
}

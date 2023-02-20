package com.mruiz.bookingmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mruiz.bookingmanager.entity.Booking;
import com.mruiz.bookingmanager.repository.BookingRepository;
import com.mruiz.bookingmanager.service.impl.EmailTemplateService;

@Component
public class Scheduler {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private EmailTemplateService emailTemplateService;

	@Transactional
	@Scheduled(cron = "5 * * * * *")
	public void emailAlertJob() {
		List<Booking> unnotified = bookingRepository.findUnnotified();
		
		unnotified.forEach(booking -> {
			emailTemplateService.alertBooking(booking);
			bookingRepository.checkNotified(booking.getId());
		});
	}
}

package com.mruiz.bookingmanager.service;

import com.mruiz.bookingmanager.payload.EmailDetails;

public interface EmailService {
	void sendSimpleMessage(EmailDetails emailDetails);
}

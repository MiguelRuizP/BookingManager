package com.mruiz.bookingmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.mruiz.bookingmanager.payload.EmailDetails;
import com.mruiz.bookingmanager.service.EmailService;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(EmailDetails emailDetails) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDetails.getRecipient());
        message.setSubject(emailDetails.getSubject());
        message.setText(emailDetails.getMsgBody());
        emailSender.send(message);
    }
}

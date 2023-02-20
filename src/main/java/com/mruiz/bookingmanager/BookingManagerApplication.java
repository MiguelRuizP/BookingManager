package com.mruiz.bookingmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.mruiz.bookingmanager.security.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
@EnableScheduling
public class BookingManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingManagerApplication.class, args);
	}

}

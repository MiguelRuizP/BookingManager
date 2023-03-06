package com.mruiz.bookingmanager.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Nonnull
	private int userId;
	
	@Nonnull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date date;
	
	@Nonnull
	private boolean notified;
	
	@Nonnull
	private boolean active;
}

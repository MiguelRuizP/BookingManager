package com.mruiz.bookingmanager.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeBookingActiveDto {
	private int id;
	private boolean active;
}

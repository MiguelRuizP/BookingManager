package com.mruiz.bookingmanager.payload;

import java.util.List;

import com.mruiz.bookingmanager.entity.Booking;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingListDto {
	private List<Booking> bookings;
}

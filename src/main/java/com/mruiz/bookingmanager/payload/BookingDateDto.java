package com.mruiz.bookingmanager.payload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDateDto {
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date date;
}

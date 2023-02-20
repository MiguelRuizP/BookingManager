package com.mruiz.bookingmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mruiz.bookingmanager.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	List<Booking> findByUserId(int userId);
	
	@Query(value = "select * from bookings where "
			+ "not(notified=1) and sysdate() > date_sub(`date`, interval 1 hour)",
		nativeQuery = true)
	List<Booking> findUnnotified();
	
	@Modifying
	@Query(value = "update bookings set notified = true where id = :id", nativeQuery = true)
	void checkNotified(@Param("id") int id);
}

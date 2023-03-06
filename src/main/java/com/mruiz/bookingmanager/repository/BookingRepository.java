package com.mruiz.bookingmanager.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mruiz.bookingmanager.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	List<Booking> findByUserId(int userId);
	
	void deleteById(int id);
	
	@Query(value = "select * from bookings where " + 
			"user_id = :id and datediff(`date`, :date) = 0", nativeQuery = true)
	List<Booking> findByUserIdAndDate(@Param("id") int userId, @Param("date") Date date);
	
	@Query(value = "select * from bookings where "
			+ "not(notified=1) and sysdate() > date_sub(`date`, interval 1 day) and active=1",
		nativeQuery = true)
	List<Booking> findUnnotified();
	
	@Modifying
	@Query(value = "update bookings set notified = true where id = :id", nativeQuery = true)
	void checkNotified(@Param("id") int id);
	
	@Modifying
	@Query(value = "update bookings set active = false where sysdate() > `date`", nativeQuery = true)
	void uncheckPastActive();
	
	@Modifying
	@Query(value = "update bookings set active = :active where id = :id", nativeQuery = true)
	void setActive(@Param("id") int id, @Param("active") boolean active);
}

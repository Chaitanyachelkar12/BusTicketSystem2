package com.bookmybus.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookmybus.busbooking.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("SELECT b FROM Booking b WHERE b.seatNumbers = :seatNumbers")
	Booking findBookingBySeatNumbers( @Param ("seatNumbers") String seatNumbers);
	
	@Query(nativeQuery = true, value="Select * from booking where booking.status=:status")
	Booking findBystatus(@Param ("status") String status);
}

package com.bookmybus.busbooking.service;

import java.util.List;

import com.bookmybus.busbooking.entity.Booking;

public interface BookingService {

	Booking createBooking(Booking booking);
    Booking getBookingById(Long bookingId);
    List<Booking> getAllBookings();
    Booking updateBooking(Long bookingId, Booking bookingDetails);
    void deleteBooking(Long bookingId);
    Booking bookTicket(Long userId, Long busId, List<String> seatNumbers) throws Exception;
	Booking getBookingByStatus(String status);
	Booking getBookingBySeatNumbers(String seatNumbers);
}

package com.bookmybus.busbooking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.busbooking.entity.Booking;
import com.bookmybus.busbooking.entity.Bus;
import com.bookmybus.busbooking.entity.User;
import com.bookmybus.busbooking.repository.BookingRepository;
import com.bookmybus.busbooking.repository.BusRepository;
import com.bookmybus.busbooking.repository.UserRepository;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private BusRepository busRepository;

	@Override
	public Booking createBooking(Booking booking) {
		// TODO Auto-generated method stub
		return bookingRepository.save(booking);
	}

	@Override
	public Booking getBookingById(Long bookingId) {
		// TODO Auto-generated method stub
		Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isPresent()) {
            return bookingOptional.get();
        } else {
            throw new RuntimeException("Booking not found with id " + bookingId);
        }
	}

	@Override
	public List<Booking> getAllBookings() {
		// TODO Auto-generated method stub
		return bookingRepository.findAll();
	}

	@Override
	public Booking updateBooking(Long bookingId, Booking bookingDetails) {
		// TODO Auto-generated method stub
		Booking existingBooking = getBookingById(bookingId);
        existingBooking.setStatus(bookingDetails.getStatus());
        existingBooking.setSeatNumbers(bookingDetails.getSeatNumbers());
        existingBooking.setBookingDate(bookingDetails.getBookingDate());
        return bookingRepository.save(existingBooking);
	}

	@Override
	public void deleteBooking(Long bookingId) {
		// TODO Auto-generated method stub
		bookingRepository.deleteById(bookingId);
	}

	@Override
	public Booking bookTicket(Long userId, Long busId, List<String> seatNumbers) throws Exception {
		// TODO Auto-generated method stub
		// Fetch user
		Optional<User> userOptional = userRepository.findById(userId);
		if (!userOptional.isPresent()) {
		    throw new RuntimeException("User not found");
		}
		User user = userOptional.get();

		// Fetch bus
		Optional<Bus> busOptional = busRepository.findById(busId);
		if (!busOptional.isPresent()) {
		    throw new RuntimeException("Bus not found");
		}
		Bus bus = busOptional.get();
		
		// Check seat availability
		if (bus.getSeatsAvailable() < seatNumbers.size()) {
            throw new RuntimeException("Not enough seats available");
        }
		
		// Update seat availability
        bus.setSeatsAvailable(bus.getSeatsAvailable() - seatNumbers.size());
        busRepository.save(bus);
        
     // Create a new booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBus(bus);
        booking.setSeatNumbers(String.join(",", seatNumbers));
        booking.setStatus("confirmed");
        booking.setBookingDate(LocalDateTime.now());

        return bookingRepository.save(booking);
	}
}

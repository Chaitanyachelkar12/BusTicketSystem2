package com.bookmybus.busbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmybus.busbooking.entity.Booking;
import com.bookmybus.busbooking.entity.Bus;
import com.bookmybus.busbooking.service.BookingService;
import com.bookmybus.busbooking.service.BusService;
import com.bookmybus.busbooking.service.BusServiceImpl;

@RestController
@RequestMapping("/searching")
public class SearchController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private BusServiceImpl busService;
	
	@GetMapping("/seatNumbers")
    public Booking FindBySeatNumbers(@RequestParam String seatNumbers) {
		return bookingService.getBookingBySeatNumbers(seatNumbers);
    	
    }
	
	@GetMapping("/busId")
    public ResponseEntity<Bus> getBusById(@RequestParam Long id) {
        Bus bus = busService.getBusById(id);
        return ResponseEntity.ok(bus);
    }
	
	@GetMapping("/status")
	public Booking findByBookingStatus(@RequestParam String status) {
		return bookingService.getBookingByStatus(status);
	}
	
	

}

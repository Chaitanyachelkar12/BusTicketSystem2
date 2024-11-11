package com.bookmybus.busbooking.controller.Exception;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusIsNotFoundException.class)
	public ResponseEntity<?> busNotFoundExceptionHandler(BusIsNotFoundException ex) {

		LocalDateTime time = LocalDateTime.now();
		Map<String, Object> response = new HashMap();
		response.put("message : ", "This Id Bus Is Not Present");
		response.put("staus : ", "failure");
		response.put("timespan : ", time);

		return new ResponseEntity(response, HttpStatus.OK);
	}
}

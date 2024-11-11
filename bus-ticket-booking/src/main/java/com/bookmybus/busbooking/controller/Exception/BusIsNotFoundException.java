package com.bookmybus.busbooking.controller.Exception;

public class BusIsNotFoundException extends RuntimeException{
 
	public BusIsNotFoundException(String message){
		super(message);
	}
}

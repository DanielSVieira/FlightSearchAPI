package com.ryanair.api.searchflights.exception;

public class InvalidAirportIATACode extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5912561312550591823L;
	
	public InvalidAirportIATACode(String message) {
		super(message);
	}
	
}

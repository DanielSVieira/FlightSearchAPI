package com.ryanair.api.searchflights.exception;

public class SameDestinationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3243450770892735279L;
	
	public SameDestinationException(String message) {
		super(message);
	}

}

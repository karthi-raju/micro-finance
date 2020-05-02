package com.spring.microfinance.exception;

public class DuplicateValueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateValueException(String message) {
		super("Given " +message+" is already available");
	}

}

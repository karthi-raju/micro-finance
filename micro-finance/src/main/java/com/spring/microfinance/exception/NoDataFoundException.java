package com.spring.microfinance.borrower.exception;

public class NoUserDataFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoUserDataFoundException(String message) {
		super(message);
	}

}

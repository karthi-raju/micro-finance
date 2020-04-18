package com.spring.microfinance.borrower.exception;

public class NoBorrowerDataFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBorrowerDataFoundException() {
		super("No User details found");
	}

}

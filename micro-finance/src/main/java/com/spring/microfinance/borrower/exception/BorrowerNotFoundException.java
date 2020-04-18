package com.spring.microfinance.borrower.exception;

public class BorrowerNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BorrowerNotFoundException(String userId) {
		super("User not found " + userId);
	}

}

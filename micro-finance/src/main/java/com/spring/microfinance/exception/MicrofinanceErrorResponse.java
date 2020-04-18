package com.spring.microfinance.exception;

import java.util.Date;

import lombok.Data;

@Data
public class MicrofinanceErrorResponse {

	Date timestamp;
	String message;
	String httpStatus;
	String errorCode;
}

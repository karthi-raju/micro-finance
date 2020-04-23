package com.spring.microfinance.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.microfinance.borrower.exception.DuplicateValueException;
import com.spring.microfinance.borrower.exception.NoUserDataFoundException;
import com.spring.microfinance.borrower.exception.UserNotFoundException;

@ControllerAdvice
@RestController
public class MicrofinanceExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger MESSAGE = LoggerFactory.getLogger(MicrofinanceExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
		MicrofinanceErrorResponse exceptionResponse = new MicrofinanceErrorResponse();
		exceptionResponse.setTimestamp(new Date());
		exceptionResponse.setMessage(
				"Unexpected Error. Please try after some time. If issue persists contact operations support");
		exceptionResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		exceptionResponse.setErrorCode("500");
		MESSAGE.error("Handle All Exceptions: " + exception);
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ UserNotFoundException.class, NoUserDataFoundException.class })
	public final ResponseEntity<Object> NotFoundException(Exception exception, WebRequest request) {
		MicrofinanceErrorResponse exceptionResponse = new MicrofinanceErrorResponse();
		exceptionResponse.setTimestamp(new Date());
		exceptionResponse.setMessage(exception.getMessage());
		exceptionResponse.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		exceptionResponse.setErrorCode("404");
		MESSAGE.error("Handle Not Found Exception: " + exception);
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ DuplicateValueException.class })
	public final ResponseEntity<Object> BadRequestException(Exception exception, WebRequest request) {
		MicrofinanceErrorResponse exceptionResponse = new MicrofinanceErrorResponse();
		exceptionResponse.setTimestamp(new Date());
		exceptionResponse.setMessage(exception.getMessage());
		exceptionResponse.setHttpStatus(HttpStatus.BAD_REQUEST.toString());
		exceptionResponse.setErrorCode("400");
		MESSAGE.error("Handle Bad Reuest Exception: " + exception);
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}

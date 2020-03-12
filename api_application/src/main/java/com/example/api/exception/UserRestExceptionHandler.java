package com.example.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exception){
		
		UserErrorResponse userErrorResponse = new UserErrorResponse();
		userErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		userErrorResponse.setMessage(exception.getMessage());
		userErrorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(userErrorResponse,HttpStatus.NOT_FOUND);
		
	}

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(PropertyNotExpectedException exception){
		
		UserErrorResponse userErrorResponse = new UserErrorResponse();
		userErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		userErrorResponse.setMessage(exception.getMessage());
		userErrorResponse.setTimeStamp(System.currentTimeMillis());               
		
		return new ResponseEntity<>(userErrorResponse,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(DataPersistenceException exception){
		
		UserErrorResponse userErrorResponse = new UserErrorResponse();
		userErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		userErrorResponse.setMessage(exception.getMessage());
		userErrorResponse.setTimeStamp(System.currentTimeMillis());               
		
		return new ResponseEntity<>(userErrorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(Exception exception){
		
		UserErrorResponse userErrorResponse = new UserErrorResponse();
		userErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		userErrorResponse.setMessage(exception.getMessage());
		userErrorResponse.setTimeStamp(System.currentTimeMillis());               
		
		return new ResponseEntity<>(userErrorResponse,HttpStatus.BAD_REQUEST);
		
	}
}

package com.stackroute.userservice.exception;



public class UserNotAvailableException extends RuntimeException{
	
	private final String errorMessage;
	
	private static final long serialVersionUID = 1L;

	public UserNotAvailableException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return this.errorMessage;
	}
}

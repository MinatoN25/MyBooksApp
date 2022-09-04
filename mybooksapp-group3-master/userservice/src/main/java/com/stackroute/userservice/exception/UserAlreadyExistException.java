package com.stackroute.userservice.exception;


public class UserAlreadyExistException extends RuntimeException{

	private final String errorMessage;
	
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

}

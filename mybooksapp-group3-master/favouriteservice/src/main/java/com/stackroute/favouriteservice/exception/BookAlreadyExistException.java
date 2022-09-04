package com.stackroute.favouriteservice.exception;

public class BookAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String errorMessage;

	public BookAlreadyExistException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}

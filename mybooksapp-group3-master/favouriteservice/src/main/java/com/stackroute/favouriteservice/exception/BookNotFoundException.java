package com.stackroute.favouriteservice.exception;

public class BookNotFoundException  extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	
	private final String errorMessage;

	public BookNotFoundException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}

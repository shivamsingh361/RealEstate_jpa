package com.cg.exception;

public class UserException extends RuntimeException{

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message) {
		super(message);
	}
}

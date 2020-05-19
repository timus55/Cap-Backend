package com.cg.capstore.exceptions;

public class UserException extends RuntimeException{

	private static final long serialVersionUID = -4005267182579342970L;
	
	public UserException() {

	}
	public UserException(String message) {
		super(message);

	}

	public UserException(Throwable cause) {
		super(cause);

	}

	public UserException(String message, Throwable cause) {
		super(message, cause);

	}

	public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}
}

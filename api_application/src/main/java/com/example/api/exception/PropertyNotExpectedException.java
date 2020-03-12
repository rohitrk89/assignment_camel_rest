package com.example.api.exception;

public class PropertyNotExpectedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PropertyNotExpectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public PropertyNotExpectedException(String message) {
		super(message);
	}

	public PropertyNotExpectedException(Throwable cause) {
		super(cause);
	}
}

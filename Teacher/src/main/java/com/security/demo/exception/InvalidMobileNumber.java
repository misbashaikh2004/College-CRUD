package com.security.demo.exception;

public class InvalidMobileNumber extends RuntimeException {
	

	public InvalidMobileNumber(String message) {
		super(message);
	}
}

package com.security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.security.demo.exception.InvalidDepartment;
import com.security.demo.exception.InvalidEmail;
import com.security.demo.exception.InvalidId;
import com.security.demo.exception.InvalidMobileNumber;
import com.security.demo.exception.InvalidRoll;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(InvalidDepartment.class)
	public ResponseEntity<?> invalidDepartment(InvalidDepartment e) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());

	}

	@ExceptionHandler(InvalidEmail.class)
	public ResponseEntity<?> invalidEmail(InvalidEmail e) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());
	}

	@ExceptionHandler(InvalidId.class)
	public ResponseEntity<?> invalidId(InvalidId e) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());
	}
	
	@ExceptionHandler(InvalidMobileNumber.class)
	public ResponseEntity<?> invalidMobileNumber(InvalidMobileNumber e){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());
	}
	
	@ExceptionHandler(InvalidRoll.class)
	public ResponseEntity<?> invalidRoll(InvalidRoll e){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(e.getMessage());
	}
}

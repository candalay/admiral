package com.game.kalah.exception;

import java.security.InvalidParameterException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class GlobalExceptionAdvisor {

	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleIOException(Exception ex) {
        return notFound().build();
    }

	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<String> invalidRequestParamterException(Exception ex) {
		  return badRequest().body(ex.getLocalizedMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> illegalArgumentException(Exception ex) {
		return status(FORBIDDEN).body(ex.getLocalizedMessage());
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<String> illegalStateException(Exception ex) {
		return  status(CONFLICT).body(ex.getLocalizedMessage());
	}

}

package com.ingenico.account.api;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

import java.security.InvalidParameterException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ingenico.exception.AccountAlreadyExistException;
import com.ingenico.exception.IllegalOperationException;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@ControllerAdvice
public class ExceptionManager {

	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<String> handleIOException(InvalidParameterException ex) {
		return badRequest().body(ex.getLocalizedMessage());
	}

	@ExceptionHandler(AccountAlreadyExistException.class)
	public ResponseEntity<String> handleIOException(AccountAlreadyExistException ex) {
		return status(CONFLICT).body(ex.getLocalizedMessage());
	}

	@ExceptionHandler(IllegalOperationException.class)
	public ResponseEntity<String> handleIOException(IllegalOperationException ex) {
		return status(FORBIDDEN).body(ex.getLocalizedMessage());
	}

}

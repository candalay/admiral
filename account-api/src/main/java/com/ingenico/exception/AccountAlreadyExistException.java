package com.ingenico.exception;

public class AccountAlreadyExistException extends RuntimeException {

	public AccountAlreadyExistException(String s) {
		super(s);
	}
}

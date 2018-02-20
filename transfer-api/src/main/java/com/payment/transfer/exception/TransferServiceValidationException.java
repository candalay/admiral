package com.payment.transfer.exception;

public class TransferServiceValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TransferServiceValidationException(String s) {
		super(s);
	}
}

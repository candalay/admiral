package com.ing.mortgage.dto;

public class ErrorResponse {

	private String error;
	private int errorCode;
	private String message;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

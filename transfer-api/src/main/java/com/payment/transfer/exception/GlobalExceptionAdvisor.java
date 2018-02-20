package com.payment.transfer.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import com.payment.transfer.dto.ErrorResponse;


@ControllerAdvice
public class GlobalExceptionAdvisor {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvisor.class);
    
    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(AsyncRequestTimeoutException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage("Internal exception occurred.");

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(AsyncUncaughtException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(AsyncUncaughtException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage("Async exception occurred.");

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(InterruptedException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage("Thread interruption exception occurred.");

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
   
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> illegalStateExceptionHandler(IllegalStateException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(TransferServiceValidationException.class)
    public ResponseEntity<ErrorResponse> transferServiceValidationExceptionHandler(TransferServiceValidationException ex) {
    	
    	logger.debug("Validation exception is occured");

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());        
        
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(TransferServiceBusinessException.class)
    public ResponseEntity<ErrorResponse> transferServiceBusinessExceptionHandler(TransferServiceBusinessException ex) {
    	
    	logger.debug("Business exception is occured");

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        
       return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
    
    
}

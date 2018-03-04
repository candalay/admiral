package com.ing.mortgage.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ing.mortgage.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionAdvisor {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvisor.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage("Internal exception occurred.");

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> illegalStateExceptionHandler(Exception ex) {

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(Exception ex) {

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MortgageBusinessException.class)
    public ResponseEntity<ErrorResponse> MortgageBusinessException(Exception ex) {

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MortgageMissingParameterException.class)
    public ResponseEntity<ErrorResponse> MortgageMissingParameterException(Exception ex) {

        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

}

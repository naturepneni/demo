package com.naturep.rewards.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Object> handleTransactionNotFoundException(TransactionNotFoundException ex) {

	Map<String, Object> body = new HashMap<>();
	body.put("message", "No transactions found");
	return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex) {

	Map<String, Object> body = new HashMap<>();
	body.put("message", "No customer found");
	return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> processUnmergeException(final MethodArgumentNotValidException ex) {

	Map<String, Object> body = new HashMap<>();
	body.put("message", "Missing input parameters");
	return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRunTimeException(RuntimeException ex) {

	Map<String, Object> body = new HashMap<>();
	body.put("message", "An internal server has occurred. Please contact operations");
	return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

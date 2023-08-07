package com.naturep.rewards.demo.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(Long customerId) {

	super();
    }
}

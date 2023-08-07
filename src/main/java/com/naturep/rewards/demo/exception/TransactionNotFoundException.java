package com.naturep.rewards.demo.exception;

public class TransactionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TransactionNotFoundException(String customerId) {

	super(String.format("Transactions with customer Id %s not found", customerId));
    }
}

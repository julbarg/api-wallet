package com.leovegas.apiwallet.exception;

public class InsufficientFundsException extends BadRequestException{
    private static final String MESSAGE = "It is not possible to make the transaction. Insufficient funds";

    public InsufficientFundsException() {
        super(MESSAGE);
    }
}

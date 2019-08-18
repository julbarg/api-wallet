package com.leovegas.apiwallet.exception;

public class TransactionIdUniqueException extends BadRequestException {
    private static final String MESSAGE = "Transaction ID already exits";

    public TransactionIdUniqueException() {
        super(MESSAGE);
    }
}

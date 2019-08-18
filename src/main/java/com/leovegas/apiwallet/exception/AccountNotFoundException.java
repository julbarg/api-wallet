package com.leovegas.apiwallet.exception;

public class AccountNotFoundException extends NotFoundException{
    private static final String MESSAGE = "Account not found";

    public AccountNotFoundException() {
        super(MESSAGE);
    }
}

package com.danieldias.smart_wallet.exception.custom;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

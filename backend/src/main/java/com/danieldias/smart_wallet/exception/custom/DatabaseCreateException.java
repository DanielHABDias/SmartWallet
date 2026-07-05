package com.danieldias.smart_wallet.exception.custom;

public class DatabaseCreateException extends RuntimeException{
    public DatabaseCreateException() {
        super("Error creating data in database");
    }

    public DatabaseCreateException(String message) {
        super(message);
    }

    public DatabaseCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}

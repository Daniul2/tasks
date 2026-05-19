package com.kodilla.library.exception;

public class AlreadyRentedException extends RuntimeException{
    public AlreadyRentedException(String message) {
        super(message);
    }
}

package com.khaago.lethe.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(){
        super("bad input");
    }

    public InvalidInputException(String message) {
        super(message);
    }
}

package com.akshay.memefinder.exception;

// Custom exception used when meme searching fails
public class MemeSearchException extends RuntimeException {

    // Pass the error message to RuntimeException
    public MemeSearchException(String message) {
        super(message);
    }
}
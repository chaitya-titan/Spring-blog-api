package com.springpractice.blogapi.exception;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(String username) {
        super("Incorrect password for user " + username);
    }
}

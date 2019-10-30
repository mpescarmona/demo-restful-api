package com.mpescarmona.demorestfulapi.exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String email) {
        super("The provided email is already registered: " + email);
    }
}

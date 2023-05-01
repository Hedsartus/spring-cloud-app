package ru.filenko.gateway.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User not found!");
    }
}

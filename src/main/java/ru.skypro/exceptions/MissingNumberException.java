package ru.skypro.exceptions;

public class MissingNumberException extends RuntimeException{
    public MissingNumberException(String message) {
        super(message);
    }
}

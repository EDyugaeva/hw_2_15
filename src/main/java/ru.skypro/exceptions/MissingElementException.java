package ru.skypro.exceptions;

public class MissingElementException extends RuntimeException{
    public MissingElementException(String message) {
        super(message);
    }
}

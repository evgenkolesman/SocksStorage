package ru.koleson.socksstorage.utils.exceptions;

public class ServerErrorException extends RuntimeException {

    public ServerErrorException (Messages serverError) {
        super(serverError.getMessage());
    }
}

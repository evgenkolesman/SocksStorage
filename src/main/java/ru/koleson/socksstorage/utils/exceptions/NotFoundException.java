package ru.koleson.socksstorage.utils.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Messages notfound) {
        super(notfound.getMessage());
    }
}

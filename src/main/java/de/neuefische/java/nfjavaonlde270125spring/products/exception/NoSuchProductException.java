package de.neuefische.java.nfjavaonlde270125spring.products.exception;

import java.util.NoSuchElementException;

public class NoSuchProductException extends NoSuchElementException {

    public NoSuchProductException(String message) {
        super(message);
    }

}

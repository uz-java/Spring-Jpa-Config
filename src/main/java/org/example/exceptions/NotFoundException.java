package org.example.exceptions;

/**
 * @author "Tojaliyev Asliddin"
 * @since 01/08/22 19:47 (Monday)
 * SpringMvcJpa/IntelliJ IDEA
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}

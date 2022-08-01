package org.example.exceptions;

/**
 * @author "Tojaliyev Asliddin"
 * @since 01/08/22 19:48 (Monday)
 * SpringMvcJpa/IntelliJ IDEA
 */
public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String message) {
        super(message);
    }
}

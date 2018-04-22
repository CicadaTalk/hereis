package edu.scu.hereis.exception;

public class MenuServiceException extends RuntimeException {

    public final static String INVALID_INPUT = "非法输入";

    public MenuServiceException(String message) {
        super(message);
    }
}

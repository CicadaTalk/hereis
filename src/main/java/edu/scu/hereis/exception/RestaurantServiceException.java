package edu.scu.hereis.exception;

public class RestaurantServiceException extends RuntimeException{

    public final static String INVALID_INPUT = "非法输入";

    public RestaurantServiceException(String message) {
        super(message);
    }
}

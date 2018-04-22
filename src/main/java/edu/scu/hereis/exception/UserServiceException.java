package edu.scu.hereis.exception;

public class UserServiceException extends RuntimeException {

    public final static String INVALID_INPUT = "非法输入";
    public final static String USER_NOT_EXSIST = "用户不存在";

    public UserServiceException(String message) {
        super(message);
    }
}

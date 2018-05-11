package edu.scu.hereis.exception;

public class MenuServiceException extends RuntimeException {

    public final static String INVALID_INPUT = "非法输入";
    public final static String NOT_EXIST = "菜单不存在";

    public MenuServiceException(String message) {
        super(message);
    }
}

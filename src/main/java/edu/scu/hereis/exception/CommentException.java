package edu.scu.hereis.exception;

/**
 * Created by Vicent_Chen on 2018/5/6.
 */
public class CommentException extends RuntimeException {

    public static final int EMPTY_COMMENT_CODE = 401;
    public static final int EMPTY_ID_CODE = 402;
    public static final int EMPTY_CONTENT_CODE = 403;
    public static final int EMPTY_SPOT_ID_CODE = 404;
    public static final int EMPTY_USER_ID_CODE = 405;
    public static final int EMPTY_TIME_CODE = 406;
    public static final int UNKNOWN_ERROR_CODE = 407;

    public static final String EMPTY_COMMENT = "未知评论";
    public static final String EMPTY_ID = "ID为空";
    public static final String EMPTY_CONTENT = "评论内容为空";
    public static final String EMPTY_SPOT_ID = "评论景点为空";
    public static final String EMPTY_USER_ID = "评论用户为空";
    public static final String EMPTY_TIME = "评论时间为空";
    public static final String UNKNOWN_ERROR = "未知错误";


    private int code;

    public CommentException(String message) {
        super(message);
        code = UNKNOWN_ERROR_CODE;
    }

    public CommentException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

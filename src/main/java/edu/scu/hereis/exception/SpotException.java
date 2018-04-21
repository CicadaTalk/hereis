package edu.scu.hereis.exception;

/**
 * Created by Vicent_Chen on 2018/4/21.
 */
public class SpotException extends RuntimeException {
    public final static int SPOT_EMPTY_ERROR_CODE = 101;
    public final static int GPS_EMPTY_ERROR_CODE = 102;
    public final static int NAME_EMPTY_ERROR_CODE = 103;
    public final static int BRIEF_INTRO_EMPTY_ERROR_CODE = 104;
    public final static int BG_IMG_EMPTY_ERROR_CODE = 105;
    public final static int CATEGORY_EMPTY_ERROR_CODE = 106;
    public final static int UNKNOWN_ERROR_CODE = 107;

    public final static String SPOT_EMPTY_ERROR = "未知地点";
    public final static String GPS_EMPTY_ERROR = "地点GPS信息为空";
    public final static String NAME_EMPTY_ERROR = "地点名称为空";
    public final static String BRIEF_INTRO_EMPTY_ERROR = "地点简介为空";
    public final static String BG_IMG_EMPTY_ERROR = "背景图片为空";
    public final static String CATEGORY_EMPTY_ERROR = "分类为空";
    public final static String UNKNOWN_ERROR = "未知错误";

    private int code;

    public SpotException(String message) {
        super(message);
        code = UNKNOWN_ERROR_CODE;
    }

    public SpotException(int code, String message) {
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

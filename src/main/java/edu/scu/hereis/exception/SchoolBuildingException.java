package edu.scu.hereis.exception;

/**
 * Created by Vicent_Chen on 2018/4/21.
 */
public class SchoolBuildingException extends RuntimeException {

    public final static int SB_EMPTY_ERROR_CODE = 201;
    public final static int FLOOR_EMPTY_ERROR_CODE = 202;
    public final static int CLASSROOM_EMPTY_ERROR_CODE = 203;
    public final static int CLASSTYPE_EMPTY_ERROR_CODE = 204;
    public final static int SPOT_ID_EMPTY_ERROR_CODE = 205;
    public final static int UNKNOWN_ERROR_CODE = 201;

    public final static String SB_EMPTY_ERROR = "未知教学楼";
    public final static String FLOOR_EMPTY_ERROR = "楼层为空";
    public final static String CLASSROOM_EMPTY_ERROR = "教室为空";
    public final static String CLASSTYPE_EMPTY_ERROR = "教室类型为空";
    public final static String SPOT_ID_EMPTY_ERROR = "地点ID为空";
    public final static String UNKNOWN_ERROR = "未知错误";

    private int code;

    public SchoolBuildingException(String message) {
        super(message);
        code = UNKNOWN_ERROR_CODE;
    }

    public SchoolBuildingException(int code, String message) {
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

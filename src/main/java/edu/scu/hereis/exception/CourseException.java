package edu.scu.hereis.exception;

/**
 * Created by Vicent_Chen on 2018/4/22.
 */
public class CourseException extends RuntimeException {
    public final static int ID_EMPTY_ERROR_CODE = 301;
    public final static int NO_EMPTY_ERROR_CODE = 302;
    public final static int NAME_EMPTY_ERROR_CODE = 303;
    public final static int TIME_EMPTY_ERROR_CODE = 304;
    public final static int SB_ID_EMPTY_ERROR_CODE = 305;
    public final static int COURSE_EMPTY_ERROR_CODE = 306;
    public final static int UNKNOWN_ERROR_CODE = 307;

    public final static String ID_EMPTY_ERROR = "课程号为空";
    public final static String NO_EMPTY_ERROR = "课程序列号为空";
    public final static String NAME_EMPTY_ERROR = "课程名称为空";
    public final static String TIME_EMPTY_ERROR = "课程上/下课时间为空";
    public final static String SB_ID_EMPTY = "教学楼ID为空";
    public final static String COURSE_EMPTY_ERROR = "课程为空";
    public final static String UNKNOWN_ERROR = "未知错误";

    private int code;

    public CourseException(String message) {
        super(message);
        code = UNKNOWN_ERROR_CODE;
    }

    public CourseException(int code, String message) {
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

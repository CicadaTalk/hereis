package edu.scu.hereis.exception;

public class ActivityException extends RuntimeException {

	public final static String ACTIVITY_EMPTY_ERROR = "活动为空";
	public final static String ACTIVITY_ID_EMPTY_ERROR = "活动ID为空";
	public final static String SPOT_ID_EMPTY_ERROR = "景点ID为空";
	public final static String UNKNOWN_ERROR = "未知错误";

	public ActivityException(String message){
		super(message);
	}
}

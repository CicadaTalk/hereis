package edu.scu.hereis.exception;

public class ScenicSpotException extends RuntimeException {

	public final static String SCENIC_SPOT_EMPTY_ERROR = "景点为空";
	public final static String SCENIC_SPOT_ID_EMPTY_ERROR = "景点ID为空";
	public final static String UNKNOWN_ERROR = "未知错误";

	public ScenicSpotException(String message){
		super(message);
	}
}
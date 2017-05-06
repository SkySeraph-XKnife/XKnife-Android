package com.skyseraph.xknife.lib.xnet.exception;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public class HttpException extends RuntimeException {

    /**
     * The constant USER_NOT_EXIST.
     */
    public static final int USER_NOT_EXIST = 1000;
    /**
     * The constant WRONG_PASSWORD.
     */
    public static final int WRONG_PASSWORD = 1001;
    private static String message;


    /**
     * Instantiates a new Http exception.
     *
     * @param resultCode the result code
     */
    public HttpException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    /**
     * Instantiates a new Http exception.
     *
     * @param detailMessage the detail message
     */
    public HttpException(String detailMessage) {
        super(detailMessage);
    }

    @Override
    public String getMessage() {
        return message;
    }

    private static String getApiExceptionMessage(int code) {
        switch (code) {
            case USER_NOT_EXIST:
                message = "该用户不存在";
                break;
            case WRONG_PASSWORD:
                message = "密码错误";
                break;
            default:
                message = "未知错误" + code;
                break;

        }
        return message;
    }
}

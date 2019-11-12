package com.zhanyd.app.common;

public enum ResultEnum {
    /** 成功 **/
    SUCCESS(200, "success"),
    /** 系统错误 **/
    SERVER_ERROR(500, "server error"),
    /** token超时 **/
	TOKEN_EXPIRED(501, "Token has expired"),
    /** 参数错误 **/
    PARAMETER_ERROR(400, "parameter error");

    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

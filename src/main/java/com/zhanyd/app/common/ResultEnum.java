package com.zhanyd.app.common;

public enum ResultEnum {
    SUCCESS(200, "success"),
    SERVER_ERROR(500, "server error"),
	TOKEN_EXPIRED(501, "Token has expired");

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

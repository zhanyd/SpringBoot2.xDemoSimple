package com.zhanyd.app.common;

public enum ResultEnum {
    SUCCESS(200, "success"),
    SERVER_ERROR(500, "server error");

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

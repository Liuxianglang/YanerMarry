package com.iyaner.yaner.entity.utils;

public enum CodeEnum {
    ERROR(-1,"失败"),
    SUCCESS(0,"成功"),
    EXIST_USER(1,"用户名已存在")
    ;

    private int code;
    private String msg;
    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}

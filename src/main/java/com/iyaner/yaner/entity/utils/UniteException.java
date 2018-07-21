package com.iyaner.yaner.entity.utils;

public class UniteException extends RuntimeException {

    private int code;
    public UniteException(CodeEnum codeEnum){
        super(codeEnum.getMsg());
        this.code=codeEnum.getCode();
    }

    public int getCode() {
        return code;
    }
}

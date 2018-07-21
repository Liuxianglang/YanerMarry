package com.iyaner.yaner.entity.utils;

import lombok.Data;

@Data
public class PageResult {

    private int code;
    private String resultNote;
    private Object obj;

    public PageResult() {
    }

    public PageResult(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.resultNote = codeEnum.getMsg();
    }
}

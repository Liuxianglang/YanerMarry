package com.iyaner.yaner.entity.utils;

import lombok.Data;

import java.util.List;

@Data
public class LayuiTable {
    private int code;
    private String msg;
    private int count;
    private List data;

    public LayuiTable() {
    }

    public LayuiTable(int code, String msg, int count) {
        this.code = code;
        this.msg = msg;
        this.count = count;
    }

    public LayuiTable(int code, String msg, int count, List data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}

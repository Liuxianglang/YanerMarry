package com.iyaner.yaner.entity.utils;

import lombok.Data;

@Data
public class Page {
    int page;
    int limit;
    String sortField;
    String sortType;
}

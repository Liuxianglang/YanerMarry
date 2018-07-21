package com.iyaner.yaner.entity.utils;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    private Integer userId;
    private String realName;
    private String userName;
    private String email;
    private String phone;
    private Date createDate;
}

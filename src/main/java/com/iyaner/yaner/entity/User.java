package com.iyaner.yaner.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import java.util.Date;

@Data
public class User {
    private Integer userId;
    private String realName;
    private String userName;
    private String salt;
    private String passWord;
    @Email(message = "请正确填写邮箱地址")
    private String email;
    private String phone;
    private Date createDate;

}

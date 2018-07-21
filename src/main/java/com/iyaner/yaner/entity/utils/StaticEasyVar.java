package com.iyaner.yaner.entity.utils;

public class StaticEasyVar {
    //加密用的默认盐
    public  static final String DEFALUTSALT="be874e72-8b37-44a6-bd54-68daf28acfa6";
    //shiro session前缀,用于存储在REDIS中使用
    public  static final String SHIRO_SESSION_PREFIX="YANER-SESSION-";
    //shiro session前缀,用于存储在REDIS中使用
    public  static final String SHIRO_CACHE_PREFIX="YANER-CACHE-";
    //shiro session默认存在时间
    public  static final Long SHIRO_SESSION_DEFAULTTIME=10*60L;
    //shiro cookie的名字，浏览器默认JSESSIONID
    public  static final String SHIRO_COOKIE_NAME="SHIRO-COOKIE";
    //shiro cookie的名字，用于记住登录人信息
    public  static final String SHIRO_COOKIE_REMEMBERME_NAME="SHIRO-REMEBERME-COOKIE";
}

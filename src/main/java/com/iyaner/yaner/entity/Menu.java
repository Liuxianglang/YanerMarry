package com.iyaner.yaner.entity;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private int menuId;
    private int parentId;
    private String menuName;
    private String menuHref;
    private String menuIcons;
    private String orderId;
    private String type;//菜单或者具体按钮
    private List<Menu> menuList;

}

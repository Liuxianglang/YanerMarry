package com.iyaner.yaner.service;

import com.iyaner.yaner.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAllMenus();

    List<Menu> getRootMenus();
}

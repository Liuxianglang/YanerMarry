package com.iyaner.yaner.repository;

import com.iyaner.yaner.entity.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> getAllMenus();

    List<Menu> getRootMenus();
}

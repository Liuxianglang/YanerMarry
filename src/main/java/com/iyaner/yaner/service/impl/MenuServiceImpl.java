package com.iyaner.yaner.service.impl;

import com.iyaner.yaner.entity.Menu;
import com.iyaner.yaner.repository.MenuMapper;
import com.iyaner.yaner.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> getAllMenus() {
        List<Menu> list=menuMapper.getAllMenus();
        List<Menu> result=new ArrayList<>();
        for (Menu menu:list){
            if(menu.getParentId()==0){

            }
        }
        return menuMapper.getAllMenus();
    }

    @Override
    public List<Menu> getRootMenus() {
        return menuMapper.getRootMenus();
    }
}

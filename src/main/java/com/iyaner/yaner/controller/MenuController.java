package com.iyaner.yaner.controller;

import com.iyaner.yaner.entity.Menu;
import com.iyaner.yaner.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/menu/getAllMenus")
    @ResponseBody
    public List<Map<String,Object>> getAllMenus(){
        List<Menu> menuList=menuService.getAllMenus();
        List<Map<String,Object>> list=new ArrayList<>();
        if(menuList.size()>0){
            for (Menu menu:menuList) {
                Map<String,Object> map=new HashMap<>();
                map.put("id",menu.getMenuId());
                map.put("parentId",menu.getParentId());
                map.put("url",menu.getMenuHref()==null?"":menu.getMenuHref());
                map.put("iconCls",menu.getMenuIcons()==null?"":menu.getMenuIcons());
                map.put("name",menu.getMenuName());
                list.add(map);
            }
        }
        return list;
    }

    @GetMapping("/menu/management")
    public String management(){
        return "/menu/menu";
    }

}

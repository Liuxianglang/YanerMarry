package com.iyaner.yaner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(String res, Map<String,String> map){
        if(res!=null){
            if(res.equals("error")){
                map.put("res","用户名或者密码错误！");
            }else if(res.equals("serror")){
                map.put("res","过期！");
            }
        }
        return "index";
    }


}

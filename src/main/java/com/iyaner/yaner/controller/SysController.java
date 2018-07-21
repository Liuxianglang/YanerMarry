package com.iyaner.yaner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SysController {

    @GetMapping("/user")
    public String user(){
        return "/sys/user";
    }
    @GetMapping("/role")
    public String role(){
        return "/sys/role";
    }
    @GetMapping("/menu")
    public String menu(){
        return "/sys/menu";
    }
    @GetMapping("/permission")
    public String permission(){
        return "/sys/permission";
    }
}

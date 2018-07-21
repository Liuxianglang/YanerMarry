package com.iyaner.yaner.controller;

import com.iyaner.yaner.entity.utils.*;
import com.iyaner.yaner.entity.User;
import com.iyaner.yaner.service.MenuService;
import com.iyaner.yaner.service.RedisService;
import com.iyaner.yaner.service.UserService;
import com.iyaner.yaner.utils.EncryptUtils;
import com.iyaner.yaner.utils.PageResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.SerializationUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    private static final Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String loginIndex(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        String shiroCookie="";
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie:cookies) {
                if(cookie.getName().equals(StaticEasyVar.SHIRO_COOKIE_NAME)){
                    shiroCookie=cookie.getValue();
                }
            }
        }
        if(shiroCookie.equals("")){
            return "redirect:/index?res=serror";
        }
        String key=StaticEasyVar.SHIRO_SESSION_PREFIX +shiroCookie;
        byte[] object= (byte[]) redisService.get(key);
        if(object==null){
            return "redirect:/index?res=serror";
        }else{
            Session session= (Session) SerializationUtils.deserialize(object);
            redisService.set(key,SerializationUtils.serialize(session),StaticEasyVar.SHIRO_SESSION_DEFAULTTIME);
            return "/user/home";
        }
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(String name, String pwd, String rememberMe){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(name,pwd);
        try {
            if(rememberMe!=null){
                token.setRememberMe(true);
            }
            subject.login(token);
            return "redirect:/user/home";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/index?res=error";
    }
    @RequestMapping(value = "/user/home")
    public String home(Map<String,Object> map){
        String name=SecurityUtils.getSubject().getPrincipal().toString();
        User user=userService.findByUserName(name);
        map.put("realName",user.getRealName());
        map.put("menuList",menuService.getRootMenus());
        return "/user/home";
    }

    @RequestMapping(value = "/user/logout",method = RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        String key=StaticEasyVar.SHIRO_SESSION_PREFIX+subject.getSession().getId();
        redisService.remove(key);
        return "redirect:/index";
    }

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    @ResponseBody
    public PageResult registerUser(String name, String pwd, @Valid User user, BindingResult result){
        if(result.hasErrors()){
            throw new UniteException(CodeEnum.ERROR);
        }
        PageResult res=null;
        User user2=userService.findByUserName(name);
        if(user2!=null){
            throw new UniteException(CodeEnum.EXIST_USER);
        }else{
            user2=new User();
            user2.setUserName(name);
            String salt= UUID.randomUUID().toString();
            user2.setSalt(salt);
            user2.setPassWord(EncryptUtils.AESEncode(salt,pwd));
            user2.setEmail(user.getEmail());
            userService.insert(user2);
            res=PageResultUtils.success();
        }
        return res;
    }

    @RequestMapping(value = "/user/welcome")
    public String welcome(Map<String,Object> map){
        return "/user/welcome";
    }

    /**
     * LAYUI后台传参数获取数据
     * @param page
     * @return
     */
    @RequestMapping("/user/getAll")
    @ResponseBody
    public LayuiTable getAll(Page page,User user){
        LayuiTable layuiTable=new LayuiTable(0,"",0);
        int count=userService.getAllAccount(user.getRealName(),user.getUserName());
        List list = userService.getAll(page.getPage(),page.getLimit(),page.getSortField(),page.getSortType(),user.getRealName(),user.getUserName());
        layuiTable.setData(list);
        layuiTable.setCount(count);
        return layuiTable;
    }
    @RequestMapping("/user/getRole")
    @ResponseBody
    public List<Map> getUserAndRole(){
        return userService.getRoleInfoByUserID();
    }

    @PostMapping("/user/update")
    @ResponseBody
    public PageResult update(User user){
        User u=userService.findById(user.getUserId());
        u.setEmail(user.getEmail());
        u.setPhone(user.getPhone());
        u.setRealName(user.getRealName());
        userService.update(u);
        return new PageResult(CodeEnum.SUCCESS);
    }
    @PostMapping("/user/delete")
    @ResponseBody
    public PageResult delete(int userId){
        User u=userService.findById(userId);
        userService.delete(u);
        return new PageResult(CodeEnum.SUCCESS);
    }
}

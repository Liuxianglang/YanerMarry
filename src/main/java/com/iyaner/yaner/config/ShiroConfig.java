package com.iyaner.yaner.config;

import com.iyaner.yaner.entity.utils.StaticEasyVar;
import com.iyaner.yaner.shiro.Realm;
import com.iyaner.yaner.shiro.RedisCacheManager;
import com.iyaner.yaner.shiro.RedisSessionDao;
import com.iyaner.yaner.shiro.SessionManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm myRealm(){
        Realm myRealm=new Realm();
        return myRealm;
    }
    @Bean
    public RedisSessionDao mySessionDao(){
        RedisSessionDao sessionDao=new RedisSessionDao();
        return sessionDao;
    }
    @Bean
    public DefaultWebSessionManager mySessionManager(){
        DefaultWebSessionManager sessionManager=new SessionManager();
        Cookie cookie=new SimpleCookie(StaticEasyVar.SHIRO_COOKIE_NAME);
        sessionManager.setSessionIdCookie(cookie);
        sessionManager.setSessionDAO(mySessionDao());
        return sessionManager;
    }
    @Bean
    public RememberMeManager myRememberMeManager(){
        CookieRememberMeManager rememberMeManager=new CookieRememberMeManager();
        Cookie cookie=new SimpleCookie(StaticEasyVar.SHIRO_COOKIE_REMEMBERME_NAME);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7*24*60*60);
        rememberMeManager.setCookie(cookie);
        return rememberMeManager;
    }
    @Bean
    public RedisCacheManager myCacheManager(){
        RedisCacheManager manager=new RedisCacheManager();
        return manager;
    }
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        //使用自定义的REALM
        manager.setRealm(myRealm());
        //使用自定义的SESSION管理
        manager.setSessionManager(mySessionManager());
        //使用自定义的缓存角色权限信息管理
        manager.setCacheManager(myCacheManager());
        //
        manager.setRememberMeManager(myRememberMeManager());
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
//        filterChainDefinitionMap.put("/", "logout");
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/files/**", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        bean.setLoginUrl("/index");
        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
}

package com.iyaner.yaner.shiro;

import com.iyaner.yaner.entity.User;
import com.iyaner.yaner.service.UserService;
import com.iyaner.yaner.utils.EncryptUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class Realm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     *
     * @param collection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
        String userName=collection.getPrimaryPrincipal().toString();
        User user=userService.findByUserName(userName);
        Set<String> roles=userService.findRolesByUser(user);
        Set<String> permissions=userService.findPermissionsByUser(user);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.从主体中获取用户名
        String userName=token.getPrincipal().toString();
        User user=userService.findByUserName(userName);
        if(user!=null){
            String pwd=EncryptUtils.AESDecode(user.getSalt(),user.getPassWord());
            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(userName,pwd,getName());
            return info;
        }
        return null;
    }
}

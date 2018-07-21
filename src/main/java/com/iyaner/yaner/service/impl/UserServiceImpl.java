package com.iyaner.yaner.service.impl;

import com.iyaner.yaner.entity.User;
import com.iyaner.yaner.repository.UserMapper;
import com.iyaner.yaner.service.UserService;
import com.iyaner.yaner.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private User findByOneCondition(String condition, Object value) {
        Map<String,Object> map=new HashMap<>();
        map.put("CXZDM",condition);//设置查询字段名
        map.put("CXZDZ",value);//设置查询字段值
        User user=userMapper.findByCondition(map);
        return user;
    }
    @Override
    public User findByUserNameAndPassWord(String name, String pwd) {
        User user=findByOneCondition("USERNAME",name);
        if(user!=null){
            String salt=user.getSalt();
            String passWord=user.getPassWord();
            if(pwd.equals(EncryptUtils.AESDecode(salt,passWord))){
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByUserName(String name) {
        User user=findByOneCondition("USERNAME",name);
        return user;
    }

    @Override
    public User findById(Integer userId) {
        User user=findByOneCondition("USERID",userId);
        return user;
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<Map> getRoleInfoByUserID() {
        return userMapper.getRoleInfoByUserID();
    }

    @Override
    public Set<String> findRolesByUser(User user) {
        return null;
    }

    @Override
    public Set<String> findPermissionsByUser(User user) {
        return null;
    }

    @Override
    public List<User> getAll(int page,int limit,String sortField,String sortType,String r,String u) {
        Map<String,Object> map=new HashMap<>();
        map.put("REALNAME",r==null?"":"%"+r+"%");
        map.put("USERNAME",u==null?"":"%"+u+"%");
        map.put("PAGE",(page-1)*limit);
        map.put("LIMIT",limit);
        map.put("NAME",sortField);
        map.put("ORDER",sortType);
        return userMapper.getAll(map);
    }

    @Override
    public int getAllAccount(String r,String u) {
        Map<String,Object> map=new HashMap<>();
        map.put("REALNAME",r);
        map.put("USERNAME",u);
        return userMapper.getAllAccount(map);
    }

    @Override
    public void update(User u) {
        userMapper.update(u);
    }

    @Override
    public void delete(User u) {
        userMapper.delete(u.getUserId());
    }
}

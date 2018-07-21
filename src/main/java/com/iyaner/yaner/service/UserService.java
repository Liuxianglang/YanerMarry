package com.iyaner.yaner.service;

import com.iyaner.yaner.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {

    User findByUserNameAndPassWord(String name, String pwd);

    User findByUserName(String name);
    User findById(Integer userId);

    void insert(User user);

    List<Map> getRoleInfoByUserID();

    Set<String> findRolesByUser(User user);

    Set<String> findPermissionsByUser(User user);

    List getAll(int page,int limit,String sortField,String sortType,String r,String u);

    int getAllAccount(String r,String u);

    void update(User u);

    void delete(User u);
}

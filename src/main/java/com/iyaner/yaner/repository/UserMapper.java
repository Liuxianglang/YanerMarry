package com.iyaner.yaner.repository;

import com.iyaner.yaner.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Update("UPDATE USER SET " +
            "REALNAME=#{realName}," +
            "EMAIL=#{email}," +
            "PHONE=#{phone} " +
            "WHERE USERID=#{userId}")
    void update(User user);

    @Delete("DELETE FROM USER WHERE USERID=#{value}")
    void delete(Integer id);

    void insert(User user);

    User findByCondition(Map<String,Object> map);

    List<Map> getRoleInfoByUserID();

    List<User> getAll(Map<String,Object> map);

    int getAllAccount(Map<String,Object> map);
}

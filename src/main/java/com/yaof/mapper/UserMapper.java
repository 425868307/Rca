package com.yaof.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yaof.pojo.Test;
import com.yaof.pojo.User;

@Repository
public interface UserMapper {

    String getUserName(String id);

    int addOneUserAccount(User user) throws Exception;

    List<User> getUserByAccount(String account) throws Exception;

    List<User> getUserByAccountAndPassword(User user);

    int saveUserInfoById(User user) throws Exception;

    void updatePassword(User user) throws Exception;

    List<User> queryAccountByPower(String power) throws Exception;

    int deleteAccountById(String id) throws Exception;

    int updatePasswordByAccount(User user) throws Exception;
}

package com.yaof.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yaof.pojo.User;
import com.yaof.service.UserService;

@Service
public class UserServiceImpl01 implements UserService {

    @Override
    public List<User> getAllUser() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getUserByAccountAndPassword(User user) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getUserByAccount(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addOneUserAccount(User user) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountExist(String account) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean saveUserInfoById(User user) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updatePassword(User user) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public List<User> queryAccountByPower(String power) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteAccountById(String id) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updatePasswordByAccount(User user) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

}

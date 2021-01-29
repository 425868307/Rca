package com.yaof.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yaof.controller.UserController;
import com.yaof.mapper.UserMapper;
import com.yaof.pojo.User;
import com.yaof.service.UserService;

@Service
@Primary
@EnableAsync
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        logger.info("getAllUser");
        return null;
    }

    @Override
    public boolean addOneUserAccount(User user) throws Exception {
        user.setCreateAccount(user.getAccount());//用户注册,用户创建人即使此用户
        int count = userMapper.addOneUserAccount(user);
        if (count == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccountExist(String account) throws Exception {
        logger.info("service - isAccountExist query begin");
        List<User> users = userMapper.getUserByAccount(account);
        userMapper.getUserByAccount(account);
        userMapper.getUserByAccount(account);
        userMapper.getUserByAccount(account);
        logger.info("service - isAccountExist query end");
        if (CollectionUtils.isEmpty(users)) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByAccountAndPassword(User user) {
        List<User> users = userMapper.getUserByAccountAndPassword(user);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User getUserByAccount(User user) {
        List<User> users;
        try {
            users = userMapper.getUserByAccount(user.getAccount());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public boolean saveUserInfoById(User user) throws Exception {
        user.setUpdateAccount(user.getAccount());//用户注册,用户创建人即使此用户
        int count = userMapper.saveUserInfoById(user);
        if (count == 1) {
            return true;
        }
        return false;
    }


    @Override
    @Async
    public void updatePassword(User user) throws Exception {
        System.out.println("修改密码的service");
        userMapper.updatePassword(user);
    }

    @Override
    public List<User> queryAccountByPower(String power) {
        try {
            logger.info("---------");
            return userMapper.queryAccountByPower(power);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean deleteAccountById(String id) throws Exception {
        int result = userMapper.deleteAccountById(id);
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePasswordByAccount(User user) throws Exception {
        int result = userMapper.updatePasswordByAccount(user);
        if (result == 1) {
            return true;
        }
        return false;
    }

}

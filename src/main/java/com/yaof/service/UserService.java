package com.yaof.service;

import java.util.List;

import com.yaof.pojo.User;

public interface UserService {

    List<User> getAllUser();

    /**
     * 根据用户名和密码查询用户，用于登陆
     *
     * @param user
     * @return
     * @author yaofang
     * @date 2018年10月24日
     */
    User getUserByAccountAndPassword(User user) throws Exception;

    User getUserByAccount(User user);

    /**
     * @param user
     * @return
     * @description 插入一条用户账号的记录
     * @author yaofang
     * @date 2018年10月19日
     */
    boolean addOneUserAccount(User user) throws Exception;

    boolean isAccountExist(String account) throws Exception;

    boolean saveUserInfoById(User user) throws Exception;

    void updatePassword(User user) throws Exception;

    List<User> queryAccountByPower(String power);

    boolean deleteAccountById(String id) throws Exception;

    boolean updatePasswordByAccount(User user) throws Exception;
}

package com.yaof.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.yaof.pojo.Test;
import com.yaof.pojo.User;
import com.yaof.pojo.utils.Response;
import com.yaof.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("UserController")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${mysql.username}")
    private String username;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    private com.alibaba.druid.pool.DruidDataSource slaveDataSource;

    @ApiOperation(value = "根据用户名查询用户信息", notes = "根据用户名查询用户信息")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public String getUserInfo(@RequestParam String userId, HttpServletRequest request) {
        logger.info("param--------userId:" + userId);
        logger.info("config-username:" + username);
        request.setAttribute("user", "meme");
        return "test";
    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestBody User user, HttpServletRequest request) {
        logger.info("请求参数:" + JSON.toJSONString(user));
        Response resp = new Response();

        try {
            Thread.sleep(1000);//
            synchronized (user.getAccount().intern()) {
                User res = userService.getUserByAccountAndPassword(user);
                if (res != null) {
                    request.getSession().setAttribute("user", res);
                    resp.setCode(Response.CODE_SUCCESS);
                    resp.setMsg("登陆成功。");
                    return resp;
                } else {
                    resp.setCode(Response.CODE_ERROR);
                    resp.setMsg("登陆失败。");
                }
                logger.info("登陆验证完成。");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resp.setCode(Response.CODE_EXCEPTION);
            resp.setMsg("系统异常。");
        }
        return resp;
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(@RequestBody User user) {
        Response resp = new Response();
        logger.info("用户注册请求参数:" + JSON.toJSONString(user));
        user.setPower("9");
        boolean flag = false;
        try {
            flag = userService.addOneUserAccount(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (flag) {
            resp.setCode(Response.CODE_SUCCESS);
            resp.setMsg("注册成功。");
        } else {
            resp.setCode(Response.CODE_ERROR);
            resp.setMsg("注册失败。");
        }
        return resp;
    }

    @ApiOperation(value = "检查此账号是否已经被注册", notes = "检查此账号是否已经被注册")
    @RequestMapping(value = "/checkAccount", method = RequestMethod.POST)
    public Response checkAccount(String account) {
        Response resp = new Response();
        logger.info("检查此账号是否已经被注册请求参数:" + account);
        boolean flag = false;
        try {
            flag = userService.isAccountExist(account);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resp.setCode(Response.CODE_EXCEPTION);
            resp.setMsg("检查失败，系统错误。");
            return resp;
        }
        if (flag) {
            resp.setCode(Response.CODE_SUCCESS);
            resp.setMsg("恭喜您!此账号可用。");
        } else {
            resp.setCode(Response.CODE_ERROR);
            resp.setMsg("此账号已经被注册。");
        }
        return resp;
    }

    @ApiOperation(value = "登出(注销)", notes = "登出(注销)")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response checkAccount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (!ObjectUtils.isEmpty(user)) {
            session.removeAttribute("user");
        }
        return new Response(Response.CODE_SUCCESS, "注销成功");
    }

    @ApiOperation(value = "用户信息保存", notes = "用户信息保存")
    @RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
    public Response saveUserInfo(@RequestBody User user, HttpSession session) {
        Response resp = new Response();
        logger.info("saveUserInfo请求参数:" + JSON.toJSONString(user));
        user.setAccount(((User) session.getAttribute("user")).getAccount());
        user.setId(((User) session.getAttribute("user")).getId());
        boolean flag = false;
        try {
            flag = userService.saveUserInfoById(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (flag) {
            resp.setCode(Response.CODE_SUCCESS);
            resp.setMsg("保存成功。");
            session.setAttribute("user", userService.getUserByAccount(user));
        } else {
            resp.setCode(Response.CODE_ERROR);
            resp.setMsg("保存失败。");
        }
        return resp;
    }

    @ApiOperation(value = "用户密码修改保存", notes = "用户密码修改保存")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Response updatePassword(@RequestBody Map<String, Object> map, HttpSession session) {
        Response resp = new Response();
        logger.info("saveUserInfo请求参数:" + JSON.toJSONString(map));
        User user = new User();
        user.setAccount(((User) session.getAttribute("user")).getAccount());
        user.setPassword((String) map.get("oldPsd"));
        try {
            User res = userService.getUserByAccountAndPassword(user);
            if (res != null) {
                user.setPassword((String) map.get("newPsd"));
                userService.updatePassword(user);
                System.out.println("updatePasswordController");
                resp.setCode(Response.CODE_SUCCESS);
                resp.setMsg("密码修改成功.");
                return resp;
            }
            resp.setCode(Response.CODE_ERROR);
            resp.setMsg("原始密码错误.");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resp.setCode(Response.CODE_ERROR);
            resp.setMsg("系统错误.");
        }
        return resp;
    }

    @ApiOperation(value = "获取当前用户名", notes = "获取当前用户名")
    @PostMapping("/getAccount")
    private String getAccount(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!ObjectUtils.isEmpty(user)) {
            return user.getAccount();
        }
        return "";
    }

    @ApiOperation(value = "获取当前用户名", notes = "获取当前用户名")
    @PostMapping("/queryAccountByPower")
    private Response<List<User>> queryAccountByPower(@RequestBody User user, HttpSession session) {
        Response<List<User>> resp = null;
        String power = user.getPower();
        if (!StringUtils.isEmpty(power)) {
            User currentUser = (User) session.getAttribute("user");
            if ("9".equals(power)) {
                if (!"0".equals(currentUser.getPower()) && !"1".equals(currentUser.getPower())) {
                    return new Response<>(Response.CODE_ERROR, "此用户没有查看普通用户的权限!");
                }
            }
            if ("1".equals(power)) {
                if (!"0".equals(currentUser.getPower())) {
                    return new Response<>(Response.CODE_ERROR, "此用户没有查看系统管理员的权限!");
                }
            }
            List<User> list = userService.queryAccountByPower(power);
            resp = new Response<>(Response.CODE_SUCCESS, "查询成功");
            resp.setData(list);
        }
        return resp;
    }

    @ApiOperation(value = "通过用户id删除此用户或管理员", notes = "通过用户id删除此用户或管理员")
    @PostMapping("/deleteAccountById")
    private Response deleteAccountById(@RequestParam String id) {
        Response resp = null;
        logger.info("通过用户id删除此用户或管理员,用户Id:" + id);
        try {
            boolean flag = userService.deleteAccountById(id);
            if (flag) {
                resp = new Response(Response.CODE_SUCCESS, "删除此用户成功.");
            } else {
                resp = new Response(Response.CODE_SUCCESS, "删除此用户失败.");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resp = new Response(Response.CODE_SUCCESS, "操作失败,系统异常.");
        }
        return resp;
    }

    @ApiOperation(value = "修改用户或系统管理员密码", notes = "修改用户或系统管理员密码")
    @RequestMapping(value = "/updatePasswordByAccount", method = RequestMethod.POST)
    public Response updatePasswordByAccount(@RequestBody User user, HttpSession session) {
        Response resp = null;
        logger.info("updatePasswordByAccount请求参数:" + JSON.toJSONString(user));
        try {
            User currentUser = (User) session.getAttribute("user");
            user.setUpdateAccount(currentUser.getAccount());    //设置当前用户为修改用户

            boolean res = userService.updatePasswordByAccount(user);
            if (res) {
                resp = new Response(Response.CODE_SUCCESS, "修改此用户密码成功.");
            } else {
                resp = new Response(Response.CODE_SUCCESS, "修改此用户密码失败.");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resp = new Response(Response.CODE_SUCCESS, "修改此用户密码失败,系统异常.");
        }
        return resp;
    }

    @ApiOperation(value = "用户账号添加", notes = "用户账号添加")
    @RequestMapping(value = "/addRcaUserInfo", method = RequestMethod.POST)
    public Response addRcaUserInfo(@RequestBody User user, HttpSession session) {
        Response resp = new Response();
        logger.info("addRcaUserInfo请求参数:" + JSON.toJSONString(user));
        boolean flag = false;
        try {
            User currentUser = (User) session.getAttribute("user");
            user.setCreateAccount(currentUser.getAccount());    //设置当前用户为修改用户

            flag = userService.addOneUserAccount(user);
            if (flag) {
                resp = new Response(Response.CODE_SUCCESS, "添加成功。");
            } else {
                resp = new Response(Response.CODE_ERROR, "添加失败。");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resp = new Response(Response.CODE_ERROR, "添加失败,系统异常");
        }
        return resp;
    }
}

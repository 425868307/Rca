package com.yaof.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yaof.pojo.GameRole;
import com.yaof.pojo.utils.Response;
import com.yaof.service.GameRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("GameRoleController")
@Controller
public class GameRoleController {

    private static final Logger logger = LoggerFactory.getLogger(GameRoleController.class);

    @Autowired
    private GameRoleService gameRoleService;

    @Autowired
    private DataSource dataSource;

    @Value("${environment}")
    private String env;

    @ApiOperation(value = "插入角色信息", notes = "插入角色信息")
    @RequestMapping(value = "/insertRoleInfo", method = RequestMethod.POST)
    @ResponseBody
    public Response<JSONObject> insertRoleInfo(@RequestBody GameRole role, HttpServletRequest request) {
        try {
            gameRoleService.insertRoleInfo(role);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Response<>(Response.CODE_ERROR, "失败");
        }
        return new Response<>(Response.CODE_SUCCESS, "成功");
    }

    @ApiOperation(value = "批量插入角色信息", notes = "批量插入角色信息")
    @RequestMapping(value = "/insertBatchRoleInfo", method = RequestMethod.POST)
    @ResponseBody
    public Response<JSONObject> insertBatchRoleInfo() {
        try {
            gameRoleService.insertBatchRoleInfo();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Response<>(Response.CODE_ERROR, "失败");
        }
        return new Response<>(Response.CODE_SUCCESS, "成功");
    }

    @ApiOperation(value = "查看插入角色待处理线程数", notes = "查看插入角色待处理线程数")
    @RequestMapping(value = "/selectNumber", method = RequestMethod.POST)
    @ResponseBody
    public Response<JSONObject> selectNumber() {
        try {
            int size = gameRoleService.getQueueSize();
            Response<JSONObject> response = new Response<>(Response.CODE_SUCCESS, "成功");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("size", size);
            response.setData(jsonObject);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Response<>(Response.CODE_ERROR, "失败");
        }
    }

    @RequestMapping(value = "/index")
    public String index(@RequestParam String name) {
        Properties properties = System.getProperties();
        String port = properties.getProperty("server.port");
        System.out.println(port);

        System.out.println(env);
        return "index.jsp";
    }
}

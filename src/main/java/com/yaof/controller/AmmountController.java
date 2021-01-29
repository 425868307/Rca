package com.yaof.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.yaof.pojo.AmmountSum;
import com.yaof.pojo.User;
import com.yaof.pojo.utils.Response;
import com.yaof.service.AmmountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("AmmountController")
@RestController
@RequestMapping("/user")
public class AmmountController {

    private static final Logger logger = LoggerFactory.getLogger(AmmountController.class);

    @Autowired
    private AmmountService ammountService;

    @ApiOperation(value = "根据用户Id查询账户信息", notes = "根据用户名查询用户信息")
    @RequestMapping(value = "/queryAccountDetail", method = RequestMethod.POST)
    public Response<List<AmmountSum>> queryAccountDetail(@RequestBody AmmountSum ammountSum,
                                                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!ObjectUtils.isEmpty(user)) {
            ammountSum.setUserId(String.valueOf(user.getId()));
        }
        logger.info("根据用户Id查询账户信息，参数:" + JSON.toJSONString(ammountSum));
        List<AmmountSum> ammountSumList = ammountService.queryAccountDetail(ammountSum);
        logger.info("根据用户Id查询账户信息，返回结果:" + JSON.toJSONString(ammountSumList));

        Response<List<AmmountSum>> resp = new Response<>(Response.CODE_SUCCESS, "查询成功.");
        resp.setData(ammountSumList);
        return resp;
    }

    @ApiOperation(value = "根据当天花费Id查询当天详细信息", notes = "根据当天花费Id查询当天详细信息")
    @RequestMapping(value = "/queryDayDetail", method = RequestMethod.POST)
    public Response<List<AmmountSum>> queryDayDetail(@RequestBody AmmountSum ammountSum, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!ObjectUtils.isEmpty(user)) {
            ammountSum.setUserId(String.valueOf(user.getId()));
        }
        logger.info("根据当天花费Id查询当天详细信息，参数:" + JSON.toJSONString(ammountSum));
        List<AmmountSum> ammountSumList = ammountService.queryDayDetail(ammountSum);
        logger.info("根据当天花费Id查询当天详细信息，返回结果:" + JSON.toJSONString(ammountSumList));

        Response<List<AmmountSum>> resp = new Response<>(Response.CODE_SUCCESS, "查询成功.");
        resp.setData(ammountSumList);
        return resp;
    }

    @ApiOperation(value = "保存添加的费用明细", notes = "保存添加的费用明细")
    @RequestMapping(value = "/addAccountDetail", method = RequestMethod.POST)
    public Response<String> addAccountDetail(@RequestBody List<AmmountSum> details, HttpSession session) {
        logger.info("保存添加的费用明细，参数:" + JSON.toJSONString(details));
        try {
            User user = (User) session.getAttribute("user");
            String userId = "0";
            if (!ObjectUtils.isEmpty(user)) {
                userId = String.valueOf(user.getId());
            }
            boolean flag = ammountService.addAccountDetail(details, userId);
            if (flag) {
                return new Response<String>(Response.CODE_SUCCESS, "添加保存成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new Response<String>(Response.CODE_ERROR, "添加保存失败");
    }

    @ApiOperation(value = "根据id修改花费明细信息", notes = "根据id修改花费明细信息")
    @RequestMapping(value = "/saveDayDetailById", method = RequestMethod.POST)
    public Response<String> saveDayDetailById(@RequestBody AmmountSum ammountSum) {
        logger.info("根据id修改花费明细信息，参数:" + JSON.toJSONString(ammountSum));
        Response<String> resp = null;
        try {
            boolean result = ammountService.saveDayDetailById(ammountSum);
            if (result) {
                resp = new Response<>(Response.CODE_SUCCESS, "修改成功.");
            } else {
                resp = new Response<>(Response.CODE_ERROR, "修改失败.");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resp = new Response<>(Response.CODE_ERROR, "系统异常.");
        }
        return resp;
    }

    @ApiOperation(value = "根据id删除记账明细信息", notes = "根据id删除记账明细信息")
    @RequestMapping(value = "/deleteDayDetailById", method = RequestMethod.POST)
    public Response<String> deleteDayDetailById(@RequestBody AmmountSum ammountSum) {
        logger.info("根据id删除记账明细信息，参数:" + JSON.toJSONString(ammountSum));
        Response<String> resp = null;
        String id = ammountSum.getId();
        if (StringUtils.isEmpty(id)) {
            resp = new Response<>(Response.CODE_SUCCESS, "删除失败,删除明细Id不能为空.");
        }
        try {
            boolean result = ammountService.deleteDayDetailById(id);
            if (result) {
                resp = new Response<>(Response.CODE_SUCCESS, "删除成功.");
            } else {
                resp = new Response<>(Response.CODE_ERROR, "删除失败.");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resp = new Response<>(Response.CODE_ERROR, "删除失败,系统异常.");
        }
        return resp;
    }
}

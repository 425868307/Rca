package com.yaof.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yaof
 * @Date 2020-10-30
 */
@Api("测试的controller类，检测一些实例是否注入到spring容器。。。")
@RestController("TestController")
public class TestController {

//	@Autowired
//	MongoTemplate mongoTemplate;

    @ApiOperation("check")
    @GetMapping("/check")
    public JSONObject check() {
        JSONObject jObj = new JSONObject();
        jObj.put("code", "200");
//		boolean testExits = mongoTemplate.collectionExists("test");
//		System.out.println(testExits);
//		jObj.put("testExits", testExits);

        return jObj;
    }

}

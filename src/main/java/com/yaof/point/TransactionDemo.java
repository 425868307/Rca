package com.yaof.point;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yaof.config.DynamicDataSource;


@Component(value = "transactionDemo")
public class TransactionDemo {

    @Autowired
    HttpServletRequest request;

    private static Logger logger = LoggerFactory.getLogger(TransactionDemo.class);


    public void before() {
        String requestURI = "";
        try {
            requestURI = request.getRequestURI();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("now the url is:" + requestURI);
        if (requestURI.contains("login") || requestURI.contains("addRcaUserInfo")) {
            DynamicDataSource.DbContextHolder.setDbType("slave");    //设置数据库的数据源类别为slave
        } else {
            DynamicDataSource.DbContextHolder.setDbType("master");    //其余情况将数据源设置为master
        }
        logger.info("point before...");
    }

    public void after() {
        logger.info("point after...");
    }
}

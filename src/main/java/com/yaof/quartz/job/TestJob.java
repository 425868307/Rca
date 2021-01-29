package com.yaof.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yaof
 */
@Component
@EnableScheduling   //配合@Scheduled使用，实现定时任务
public class TestJob {

    private static Logger logger = LoggerFactory.getLogger(TestJob.class);

    private int count = 1;

    /**
     * 供测试的定时任务，每5分钟执行一次
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void showSomeThing() {
        logger.info("testJob calculate the count is：" + count++);
//        log.info("testJob calculate the count is：" + count++);
    }


}

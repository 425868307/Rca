package com.yaof.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yaof.pojo.GameRole;
import com.yaof.service.GameRoleService;
import com.yaof.tools.CommonUnit;


/**
 * 定时任务执行，定义相应的定时任务类，和定时任务方法
 * 通过xml配置实现定时任务的执行
 *
 * @author Administrator
 */
public class CustomerJob {

    private static final Logger logger = LoggerFactory.getLogger(CustomerJob.class);

    private static int aa = 1;

    @Autowired
    private GameRoleService roleService;

    private GameRole role = new GameRole();

    //2、创建随机对象
    private static Random rd = new Random();

    {
        System.out.println("CustomerJob has been new...");
    }

    public void executorQuartzJob() {
        CommonUnit.threadLocal.set(new SimpleDateFormat("yyyyMMdd"));
        role.setId(CommonUnit.getRandomUUID());
        role.setAccount(CommonUnit.getRandomAccount());
        role.setName(CommonUnit.getChineseName(4));
        role.setSoleType("0" + (rd.nextInt(7) + 1));
        role.setSoleGenner(rd.nextInt(2) == 0 ? "M" : "F");
        role.setSoleGrade(70 + rd.nextInt(10));
        role.setSoleAppearanceType(rd.nextInt(15));
        role.setSoleGoldNum((7 + rd.nextInt(5)) << 19);
        role.setSoleSilverNum((6 + rd.nextInt(5)) << 22);
        role.setSoleMonnyNum(rd.nextInt(7) << 6);
        role.setUserId(CommonUnit.getRandomCardID());
        role.setUserName(CommonUnit.getChineseName(0));
        role.setIsAdult(CommonUnit.isAdult(role.getUserId()) ? "Y" : "N");
        role.setUserVipType("" + rd.nextInt(6));
        role.setMachineType(String.valueOf(rd.nextInt(3) + 1));
        try {
            roleService.insertRoleInfo(role);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        System.out.println(aa++);
    }
}

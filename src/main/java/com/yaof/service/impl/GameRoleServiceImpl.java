package com.yaof.service.impl;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaof.mapper.GameRoleMapper;
import com.yaof.pojo.GameRole;
import com.yaof.service.GameRoleService;
import com.yaof.tools.CommonUnit;

@Service
public class GameRoleServiceImpl implements GameRoleService {

    private static Logger logger = LoggerFactory.getLogger(GameRoleServiceImpl.class);

    private static int availableProcessors = Runtime.getRuntime().availableProcessors();

    private static LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(100);

    //2、创建随机对象
    private static Random rd = new Random();

    @Autowired
    GameRoleMapper roleMapper;

    /**
     * spring自带的事务管理，默认是抛出错误，或者运行时异常时，会进行数据回滚
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertRoleInfo(GameRole role) throws Exception {
        roleMapper.insertRoleInfo(role);
        insertRoleInfo001(role);
    }

    @Transactional
    public void insertRoleInfo001(GameRole role) throws Exception {
        roleMapper.insertRoleInfo(role);
    }

    @Override
    public int getQueueSize() {
        return queue.size();
    }

    @Override
    public void insertBatchRoleInfo() {
        logger.info("批量调用方法进行数据插入");

        ThreadPoolExecutor pool = new ThreadPoolExecutor(availableProcessors + 1, availableProcessors + 1, 60000, TimeUnit.MILLISECONDS, queue);
        System.out.println(pool.getMaximumPoolSize());
        System.out.println(pool.getCorePoolSize());
        for (int i = 0; i < 100; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    CommonUnit.threadLocal.set(new SimpleDateFormat("yyyyMMdd"));
                    GameRole role = new GameRole();
                    System.out.println(Thread.currentThread().getName());
                    for (int i = 0; i < 50000; i++) {
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
                        roleMapper.insertRoleInfo(role);
                    }
                }
            };
            pool.execute(runnable);
        }
    }

}

package com.yaof.service;

import com.yaof.pojo.GameRole;

public interface GameRoleService {


    void insertRoleInfo(GameRole role) throws Exception;

    void insertBatchRoleInfo();

    int getQueueSize();
}

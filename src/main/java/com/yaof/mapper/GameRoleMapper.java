package com.yaof.mapper;

import org.springframework.stereotype.Repository;

import com.yaof.pojo.GameRole;

@Repository
public interface GameRoleMapper {

    void insertRoleInfo(GameRole role);
}

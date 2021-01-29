package com.yaof.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yaof.pojo.AmmountSum;
import com.yaof.pojo.GameRole;

@Repository
public interface AmmountMapper {

    List<AmmountSum> queryAccountDetail(AmmountSum ammountSum);

    List<AmmountSum> queryDayDetail(AmmountSum ammountSum);

    void addAccountDetail(AmmountSum ammountSum);

    int updateDayDetailById(AmmountSum ammountSum);

    int deleteDayDetailById(String id);

}

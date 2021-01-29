package com.yaof.service;

import java.util.List;

import com.yaof.pojo.AmmountSum;

public interface AmmountService {

    List<AmmountSum> queryAccountDetail(AmmountSum ammountSum);

    List<AmmountSum> queryDayDetail(AmmountSum ammountSum);


    boolean addAccountDetail(List<AmmountSum> details, String userId) throws Exception;

    boolean saveDayDetailById(AmmountSum ammountSum) throws Exception;

    boolean deleteDayDetailById(String id) throws Exception;
}

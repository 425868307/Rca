package com.yaof.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yaof.mapper.AmmountMapper;
import com.yaof.pojo.AmmountSum;
import com.yaof.service.AmmountService;


@Service
public class AmmountServiceImpl implements AmmountService {

    @Autowired
    private AmmountMapper ammountMapper;

    @Override
    public List<AmmountSum> queryAccountDetail(AmmountSum ammountSum) {
        return ammountMapper.queryAccountDetail(ammountSum);
    }

    @Override
    public List<AmmountSum> queryDayDetail(AmmountSum ammountSum) {
        return ammountMapper.queryDayDetail(ammountSum);
    }

    /**
     * 添加费用明细
     */
    @Override
    @Transactional
    public boolean addAccountDetail(List<AmmountSum> details, String userId) throws Exception {
        if (CollectionUtils.isEmpty(details)) {
            return false;
        }
        for (AmmountSum ammountSum : details) {
            ammountSum.setUserId(userId);
            ammountMapper.addAccountDetail(ammountSum);
        }
        return true;
    }

    @Override
    public boolean saveDayDetailById(AmmountSum ammountSum) throws Exception {
        int result = ammountMapper.updateDayDetailById(ammountSum);
        if (result != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteDayDetailById(String id) throws Exception {
        int result = ammountMapper.deleteDayDetailById(id);
        if (result != 1) {
            return false;
        }
        return true;
    }

}

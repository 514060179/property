package com.simon.backstage.service.impl;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.ChargeItemRecordMapper;
import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.backstage.service.ChargeItemRecordService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fengtianying
 * @date 2019/1/27 14:43
 */
@Service
public class ChargeItemRecordServiceImpl implements ChargeItemRecordService {

    @Autowired
    private ChargeItemRecordMapper chargeItemRecordMapper;
    @Override
    public ChargeItemRecord add(ChargeItemRecord chargeItemRecord) {
        chargeItemRecord.setRecordId(UUIDUtil.uidString());
        if (chargeItemRecordMapper.insertSelective(chargeItemRecord)>0){
            return chargeItemRecord;
        }
        return null;
    }

    @Override
    public ChargeItemRecord detail(String recordId) {
        return chargeItemRecordMapper.selectByPrimaryKey(recordId);
    }

    @Override
    public int upd(ChargeItemRecord chargeItemRecord) {
        return chargeItemRecordMapper.updateByPrimaryKeySelective(chargeItemRecord);
    }

    @Override
    public int del(String recordId) {
        return chargeItemRecordMapper.deleteByPrimaryKey(recordId);
    }

    @Override
    public PageInfo<ChargeItemRecord> list(BaseQueryParam baseQueryParam) {
        return new PageInfo<>(chargeItemRecordMapper.selectByCondition(baseQueryParam));
    }
}

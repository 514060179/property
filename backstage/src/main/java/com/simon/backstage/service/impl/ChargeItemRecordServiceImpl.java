package com.simon.backstage.service.impl;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.AdvanceMoneyMapper;
import com.simon.backstage.dao.AdvanceRecordMapper;
import com.simon.backstage.dao.ChargeItemMapper;
import com.simon.backstage.dao.ChargeItemRecordMapper;
import com.simon.backstage.domain.model.AdvanceMoney;
import com.simon.backstage.domain.model.AdvanceRecord;
import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.backstage.service.ChargeItemRecordService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author fengtianying
 * @date 2019/1/27 14:43
 */
@Service
public class ChargeItemRecordServiceImpl implements ChargeItemRecordService {

    @Autowired
    private ChargeItemRecordMapper chargeItemRecordMapper;
    @Autowired
    private ChargeItemMapper chargeItemMapper;
    @Autowired
    private AdvanceMoneyMapper advanceMoneyMapper;
    @Autowired
    private AdvanceRecordMapper advanceRecordMapper;
    @Override
    public ChargeItemRecord add(ChargeItemRecord chargeItemRecord) {
        chargeItemRecord.setRecordId(UUIDUtil.uidString());
        if (chargeItemRecordMapper.insertSelective(chargeItemRecord)>0){
            return chargeItemRecord;
        }
        return null;
    }

    @Transactional
    @Override
    public int addBatch(List<ChargeItemRecord> chargeItemRecordList, List<AdvanceMoney> advanceMonies, List<AdvanceRecord> advanceRecords) {
        //1.更新账户
        if (!Objects.isNull(advanceMonies)&&advanceMonies.size()>0)
            advanceMonies.forEach(advanceMoney -> advanceMoneyMapper.updateByUserId(advanceMoney));
        //2.更新账户记录
        if (!Objects.isNull(advanceRecords)&&advanceRecords.size()>0)
            advanceRecordMapper.batchAdd(advanceRecords);
        //3.批量加入收费记录
        chargeItemRecordMapper.addBatch(chargeItemRecordList);
        //4.更新所有临时订单
        chargeItemMapper.updateAllTemporary();
        return 1;
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

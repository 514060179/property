package com.simon.backstage.service.impl;

import com.simon.backstage.constant.Status;
import com.simon.backstage.dao.AdvanceMoneyMapper;
import com.simon.backstage.dao.AdvanceRecordMapper;
import com.simon.backstage.domain.model.AdvanceMoney;
import com.simon.backstage.domain.model.AdvanceRecord;
import com.simon.backstage.service.AdvanceService;
import com.simon.dal.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author fengtianying
 * @date 2019/1/26 14:35
 */
@Service
public class AdvanceServiceImpl implements AdvanceService {

    @Autowired
    private AdvanceMoneyMapper advanceMoneyMapper;

    @Autowired
    private AdvanceRecordMapper advanceRecordMapper;
    @Override
    public AdvanceMoney findByUserId(String userId) {
        return advanceMoneyMapper.findByUserId(userId);
    }

    @Override
    @Transactional
    public int add(AdvanceMoney advanceMoney) {
        //1.新增流水记录
        //2.账户添加预收金额
        int i = 0;
        StringBuffer describe = new StringBuffer();
        if (Objects.isNull(advanceMoney.getAdvanceId())){//新增账户
            advanceMoney.setAdvanceId(UUIDUtil.uidString());
            describe.append("初次预交费用");
            i = advanceMoneyMapper.insertSelective(advanceMoney);
        }else{//更新账户
            AdvanceMoney update = new AdvanceMoney();
            update.setAdvanceId(advanceMoney.getAdvanceId());
            update.setAdvanceAmount(advanceMoney.getAdvanceAmount());
            describe.append("预交费用");
            i = advanceMoneyMapper.updateByPrimaryKeySelective(update);
        }
        //新增流水
        AdvanceRecord advanceRecord = new AdvanceRecord();
        advanceRecord.setAdvanceRecordId(UUIDUtil.uidString());
        advanceRecord.setAdvanceId(advanceMoney.getAdvanceId());
        advanceRecord.setAdvanceAmount(advanceMoney.getAdvanceAmount());
        advanceRecord.setAdvanceType(Status.advanceTypeIn);//入账
        advanceRecord.setAdvanceDescribe(describe.toString());
        i += advanceRecordMapper.insertSelective(advanceRecord);
        return i;
    }

//    @Override
//    public int create(AdvanceMoney advanceMoney) {
//        return advanceMoneyMapper.insertSelective(advanceMoney);
//    }
}

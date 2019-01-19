package com.simon.backstage.dao;

import com.simon.backstage.domain.model.ChargeItemRecord;

public interface ChargeItemRecordMapper {
    int deleteByPrimaryKey(String recordId);

    int insert(ChargeItemRecord record);

    int insertSelective(ChargeItemRecord record);

    ChargeItemRecord selectByPrimaryKey(String recordId);

    int updateByPrimaryKeySelective(ChargeItemRecord record);

    int updateByPrimaryKey(ChargeItemRecord record);
}
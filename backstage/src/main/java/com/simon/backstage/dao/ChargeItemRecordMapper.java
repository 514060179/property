package com.simon.backstage.dao;

import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;

public interface ChargeItemRecordMapper {
    int deleteByPrimaryKey(String recordId);

    int insert(ChargeItemRecord record);

    int insertSelective(ChargeItemRecord record);

    int addBatch(List<ChargeItemRecord> list);

    ChargeItemRecord selectByPrimaryKey(String recordId);

    List<ChargeItemRecord> selectByCondition(BaseQueryParam baseQueryParam);

    int updateByPrimaryKeySelective(ChargeItemRecord record);

    int updateByPrimaryKey(ChargeItemRecord record);
}
package com.simon.backstage.dao;

import com.simon.backstage.domain.model.AdvanceRecord;

public interface AdvanceRecordMapper {
    int deleteByPrimaryKey(String advanceRecordId);

    int insert(AdvanceRecord record);

    int insertSelective(AdvanceRecord record);

    AdvanceRecord selectByPrimaryKey(String advanceRecordId);

    int updateByPrimaryKeySelective(AdvanceRecord record);

    int updateByPrimaryKey(AdvanceRecord record);
}
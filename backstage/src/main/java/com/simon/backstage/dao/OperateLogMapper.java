package com.simon.backstage.dao;

import com.simon.backstage.domain.model.OperateLog;

public interface OperateLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(OperateLog record);

    int insertSelective(OperateLog record);

    OperateLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(OperateLog record);

    int updateByPrimaryKey(OperateLog record);
}
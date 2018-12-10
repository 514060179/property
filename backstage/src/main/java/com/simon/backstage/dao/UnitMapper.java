package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Unit;

public interface UnitMapper {
    int deleteByPrimaryKey(String unitId);

    int insert(Unit record);

    int insertSelective(Unit record);

    Unit selectByPrimaryKey(String unitId);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);
}
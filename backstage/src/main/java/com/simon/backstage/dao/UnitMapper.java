package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Unit;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;

public interface UnitMapper {
    int deleteByPrimaryKey(String unitId);

    int insert(Unit record);

    int insertSelective(Unit record);

    Unit selectByPrimaryKey(String unitId);

    List<Unit> selectByCondition(BaseQueryParam baseQueryParam);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);
}
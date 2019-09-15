package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Unit;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;

public interface UnitMapper {
    int deleteByPrimaryKey(String unitId);

    int insert(Unit record);

    int insertSelective(Unit record);

    Unit selectByPrimaryKey(String unitId);

    Unit selectByUnitNo(String unitNo);

    List<Unit> selectByCondition(BaseQueryParam baseQueryParam);

    List<Unit> getUnitByUserId(String userId);

    int updateByPrimaryKeySelective(Unit record);

    int updateByPrimaryKey(Unit record);


}
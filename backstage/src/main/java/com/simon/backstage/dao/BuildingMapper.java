package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Building;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;

public interface BuildingMapper {
    int deleteByPrimaryKey(String buildingId);

    int insert(Building record);

    int insertSelective(Building record);

    Building selectByPrimaryKey(String buildingId);

    List<Building> selectByCondition(BaseQueryParam baseQueryParam);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);
}
package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Building;

public interface BuildingMapper {
    int deleteByPrimaryKey(String buildingId);

    int insert(Building record);

    int insertSelective(Building record);

    Building selectByPrimaryKey(String buildingId);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);
}
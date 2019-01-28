package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Building;
import com.simon.backstage.domain.vo.BuildingWithUnit;
import com.simon.backstage.domain.vo.CommunityWithBuilding;
import com.simon.dal.vo.BaseClaims;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuildingMapper {
    int deleteByPrimaryKey(String buildingId);

    int insert(Building record);

    int insertSelective(Building record);

    Building selectByPrimaryKey(String buildingId);

    List<Building> selectByCondition(BaseClaims baseClaims);

    List<CommunityWithBuilding> communityWithBuildingAndUnit(@Param("communityId") String communityId);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);
}
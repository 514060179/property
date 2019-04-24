package com.simon.backstage.dao;

import com.simon.backstage.domain.model.Building;
import com.simon.backstage.domain.model.BuildingChild;
import com.simon.backstage.domain.model.Enclosure;
import com.simon.backstage.domain.model.Floor;
import com.simon.backstage.domain.vo.BuildingWithUnit;
import com.simon.backstage.domain.vo.CommunityWithBuilding;
import com.simon.dal.vo.BaseClaims;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuildingMapper {
    int deleteByPrimaryKey(String buildingId);

    int insert(Building record);

    int insertSelective(Building record);

    int insertFloorSelective(List<Floor> list);

    int insertBuildingChildSelective(List<BuildingChild> list);

    int delBuildingChilds(String buildingId);

    List<Floor> selectFloorListByBuildingId(String buildingId);

    List<BuildingChild> selectBuildingChildListByBuildingId(String buildingId);

    Building selectByPrimaryKey(String buildingId);

    List<Building> selectByCondition(BaseClaims baseClaims);

    List<CommunityWithBuilding> communityWithBuildingAndUnit(@Param("communityId") String communityId);

    List<CommunityWithBuilding> communityWithUnit(@Param("communityId") String communityId);

    int updateByPrimaryKeySelective(Building record);

    int updateFloorByPrimaryKeySelective(Floor floor);

    int updateBuildingChildByPrimaryKeySelective(BuildingChild buildingChild);

    int updateByPrimaryKey(Building record);

    int insertEnclosures(List<Enclosure> list);

    List<String> findUrlFromEnclosure(Enclosure enclosure);

    int delEnclosure(@Param("objectId") String objectId, @Param("enclosureObjectType") Integer enclosureObjectType);
}
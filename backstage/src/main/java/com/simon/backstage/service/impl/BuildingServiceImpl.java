package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.BuildingMapper;
import com.simon.backstage.domain.model.Building;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.vo.CommunityWithBuilding;
import com.simon.backstage.service.BuildingService;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 楼宇管理
 * @author fengtianying
 * @date 2018/12/14 16:59
 */
@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public Building add(Building building) {
        building.setBuildingId(UUIDUtil.uidString());
        buildingMapper.insertSelective(building);
        return building;
    }

    @Override
    public Building detail(String buildingId) {
        return buildingMapper.selectByPrimaryKey(buildingId);
    }

    @Override
    public int upd(Building building) {
        return buildingMapper.updateByPrimaryKeySelective(building);
    }

    @Override
    public int del(String buildingId) {
        return buildingMapper.deleteByPrimaryKey(buildingId);
    }

    @Override
    public PageInfo<Building> list(BaseClaims baseClaims) {
        PageHelper.startPage(baseClaims.getPageNo(),baseClaims.getPageSize());
        return new PageInfo<>(buildingMapper.selectByCondition(baseClaims));
    }

    @Override
    public List<CommunityWithBuilding> communityWithBuildingAndUnit(String communityId) {
        //获取社区下的停车单元
        List<CommunityWithBuilding> communityWithUnitList = buildingMapper.communityWithUnit(communityId);
        Map<String,List<com.simon.backstage.domain.vo.Unit>> map = new HashMap<>();
        communityWithUnitList.forEach(communityWithBuilding -> map.put(communityWithBuilding.getCommunityId(),communityWithBuilding.getUnitList()));
        List<CommunityWithBuilding> communityWithBuildingList = buildingMapper.communityWithBuildingAndUnit(communityId);
        communityWithBuildingList.forEach((communityWithBuilding -> communityWithBuilding.setUnitList(map.get(communityWithBuilding.getCommunityId()))));
        return communityWithBuildingList;
    }
}

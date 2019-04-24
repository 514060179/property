package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.BuildingMapper;
import com.simon.backstage.domain.model.*;
import com.simon.backstage.domain.vo.CommunityWithBuilding;
import com.simon.backstage.service.BuildingService;
import com.simon.dal.constant.Type;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Transactional
    public Building add(Building building) {
        building.setBuildingId(UUIDUtil.uidString());
        buildingMapper.insertSelective(building);
        if (building.getBuildingChildList()!=null&&building.getBuildingChildList().size()>0){
            building.getBuildingChildList().forEach(buildingChild -> {
                buildingChild.setChildId(UUIDUtil.uidString());
                buildingChild.setBuildingId(building.getBuildingId());
            });
            buildingMapper.insertBuildingChildSelective(building.getBuildingChildList());
            building.setBuildingChildList(building.getBuildingChildList());
        }
        if (building.getFloorList()!=null&&building.getFloorList().size()>0){
            building.getFloorList().forEach(floor -> {
                floor.setFloorId(UUIDUtil.uidString());
                floor.setBuildingId(building.getBuildingId());
            });
            buildingMapper.insertFloorSelective(building.getFloorList());
            building.setFloorList(building.getFloorList());
        }
        List<Enclosure> enclosureList = new ArrayList<>();
        if (building.getCommonPdf()!=null&&building.getCommonPdf().size()>0){
            building.getCommonPdf().forEach(s -> {
                Enclosure enclosure = new Enclosure();
                enclosure.setEnclosureId(UUIDUtil.uidString());
                enclosure.setObjectId(building.getBuildingId());
                enclosure.setEnclosureUrl(s);
                enclosure.setEnclosureObjectType(Type.ENCLOSURE_OBJECT_TYPE_BUILDING);
                enclosure.setEnclosureType(Type.ENCLOSURE_TYPE_PDF);
                enclosureList.add(enclosure);
            });
        }
        if (building.getRosterPdf()!=null&&building.getRosterPdf().size()>0){
            building.getRosterPdf().forEach(s -> {
                Enclosure enclosure = new Enclosure();
                enclosure.setEnclosureId(UUIDUtil.uidString());
                enclosure.setObjectId(building.getBuildingId());
                enclosure.setEnclosureUrl(s);
                enclosure.setEnclosureObjectType(Type.ENCLOSURE_OBJECT_TYPE_BUILDING);
                enclosure.setEnclosureType(Type.ENCLOSURE_TYPE_ROSTER);
                enclosureList.add(enclosure);
            });
            buildingMapper.insertEnclosures(enclosureList);
        }
        return building;
    }

    @Override
    public Building detail(String buildingId) {
        Building building = buildingMapper.selectByPrimaryKey(buildingId);
        if (building!=null){//获取楼层
            List<Floor> floors = buildingMapper.selectFloorListByBuildingId(buildingId);
            building.setFloorList(floors);
            building.setBuildingChildList(buildingMapper.selectBuildingChildListByBuildingId(buildingId));
            //获取enclosure文件
            //PDF
            Enclosure find = new Enclosure();
            find.setObjectId(buildingId);
            find.setEnclosureObjectType(Type.ENCLOSURE_OBJECT_TYPE_BUILDING);
            find.setEnclosureType(Type.ENCLOSURE_TYPE_PDF);
            building.setCommonPdf(buildingMapper.findUrlFromEnclosure(find));
            //花名册
            find.setEnclosureType(Type.ENCLOSURE_TYPE_ROSTER);
            building.setRosterPdf(buildingMapper.findUrlFromEnclosure(find));
        }
        return building;
    }

    @Override
    @Transactional
    public int upd(Building building) {
        List<Enclosure> enclosureList = new ArrayList<>();
        if (building.getBuildingChildList()!=null&&building.getBuildingChildList().size()>0){
            building.getBuildingChildList().forEach(buildingChild -> {
                buildingChild.setChildId(UUIDUtil.uidString());
                buildingChild.setBuildingId(building.getBuildingId());
            });
            if (buildingMapper.delBuildingChilds(building.getBuildingId())>=0){
                buildingMapper.insertBuildingChildSelective(building.getBuildingChildList());
                building.setBuildingChildList(building.getBuildingChildList());
            }
        }
        if (building.getCommonPdf()!=null&&building.getCommonPdf().size()>0){
            building.getCommonPdf().forEach(s -> {
                Enclosure enclosure = new Enclosure();
                enclosure.setEnclosureUrl(s);
                enclosure.setEnclosureId(UUIDUtil.uidString());
                enclosure.setObjectId(building.getBuildingId());
                enclosure.setEnclosureObjectType(Type.ENCLOSURE_OBJECT_TYPE_BUILDING);
                enclosure.setEnclosureType(Type.ENCLOSURE_TYPE_PDF);
                enclosureList.add(enclosure);
            });
        }
        if (building.getRosterPdf()!=null&&building.getRosterPdf().size()>0){
            building.getRosterPdf().forEach(s -> {
                Enclosure enclosure = new Enclosure();
                enclosure.setEnclosureUrl(s);
                enclosure.setEnclosureId(UUIDUtil.uidString());
                enclosure.setObjectId(building.getBuildingId());
                enclosure.setEnclosureObjectType(Type.ENCLOSURE_OBJECT_TYPE_BUILDING);
                enclosure.setEnclosureType(Type.ENCLOSURE_TYPE_ROSTER);
                enclosureList.add(enclosure);
            });
            //删除之前的
            buildingMapper.delEnclosure(building.getBuildingId(),Type.ENCLOSURE_OBJECT_TYPE_BUILDING);
            buildingMapper.insertEnclosures(enclosureList);
        }
        return buildingMapper.updateByPrimaryKeySelective(building);
    }

    @Override
    public int updFloor(Floor floor) {
        return buildingMapper.updateFloorByPrimaryKeySelective(floor);
    }

    @Override
    public int updBuildingChild(BuildingChild buildingChild) {
        return buildingMapper.updateBuildingChildByPrimaryKeySelective(buildingChild);
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

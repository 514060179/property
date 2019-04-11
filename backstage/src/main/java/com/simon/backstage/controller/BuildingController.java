package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Building;
import com.simon.backstage.domain.model.BuildingChild;
import com.simon.backstage.domain.model.Floor;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.domain.vo.CommunityWithBuilding;
import com.simon.backstage.service.BuildingService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.vo.BaseClaims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fengtianying
 * @date 2018/12/10 15:59
 */
@RestController
@RequestMapping("/back/building")
@Api(value = "BuildingController", description = "建筑管理")
public class BuildingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BuildingService buildingService;
    @PostMapping("add")
    @ApiOperation("添加建筑")
    public ReturnMsg<Building> add(@RequestBody Building building, HttpServletRequest request){
        logger.info("添加建筑building={}", JSONUtil.objectToJson(building));
        if (StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//超级管理员
            if (StringUtils.isEmpty(building.getCommunityId())){
                return ReturnMsg.fail(Code.missingParameter,"缺少社区参数communityId");
            }
        }else{
            building.setCommunityId(ClaimsUtil.getCommunityId(request));
        }
        return ReturnMsg.success(buildingService.add(building));
    }
    @PostMapping("upd")
    @ApiOperation("修改建筑")
    public ReturnMsg<Building> upd(@RequestBody Building building, HttpServletRequest request){
        logger.info("修改建筑building={}", JSONUtil.objectToJson(building));
        if (!StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//普通管理员
            building.setCommunityId(null);
        }
        return ReturnMsg.success(buildingService.upd(building));
    }
    @PostMapping("updFloor")
    @ApiOperation("修改建筑楼层")
    public ReturnMsg<Building> updFloor(@RequestBody Floor floor){
        logger.info("修改建筑楼层floor={}", JSONUtil.objectToJson(floor));
        return ReturnMsg.success(buildingService.updFloor(floor));
    }
    @PostMapping("updBuildingChild")
    @ApiOperation("修改建筑子部分")
    public ReturnMsg<Building> updBuildingChild(@RequestBody BuildingChild buildingChild){
        logger.info("修改建筑子部分buildingChild={}", JSONUtil.objectToJson(buildingChild));
        return ReturnMsg.success(buildingService.updBuildingChild(buildingChild));
    }
    @GetMapping("del")
    @ApiOperation("删除建筑")
    public ReturnMsg<Building> del(@RequestParam String buildingId){
        logger.info("删除建筑buildingId={}",buildingId);
        return ReturnMsg.success(buildingService.del(buildingId));
    }
    @GetMapping("detail")
    @ApiOperation("建筑详情")
    public ReturnMsg<Building> detail(@RequestParam String buildingId){
        logger.info("建筑详情buildingId={}",buildingId);
        return ReturnMsg.success(buildingService.detail(buildingId));
    }
    @GetMapping("list")
    @ApiOperation("建筑列表")
    public ReturnMsg<PageInfo<Building>> list(BaseClaims baseClaims, HttpServletRequest request){
    	String communityId = ClaimsUtil.getCommunityId(request);
		if(!StringUtils.isEmpty(communityId)){
			baseClaims.setCommunityId(communityId);
		}
        logger.info("建筑列表baseClaims={}", JSONUtil.objectToJson(baseClaims));
        return ReturnMsg.success(buildingService.list(baseClaims));
    }

    @GetMapping("communityWithBuildingAndUnit")
    @ApiOperation("社区+楼宇+单元")
    public ReturnMsg<List<CommunityWithBuilding>> communityWithBuildingAndUnit(HttpServletRequest request){
        String communityId = ClaimsUtil.getCommunityId(request);
        logger.info("社区列表communityId={}",  JSONUtil.objectToJson(communityId));
        return ReturnMsg.success(buildingService.communityWithBuildingAndUnit(communityId));
    }
}

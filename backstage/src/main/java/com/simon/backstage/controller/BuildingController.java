package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Building;
import com.simon.backstage.domain.msg.ReturnMsg;
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
    public ReturnMsg<Building> add(@RequestBody Building building){
        logger.info("添加建筑building={}", JSONUtil.objectToJson(building));
        return ReturnMsg.success(buildingService.add(building));
    }
    @PostMapping("upd")
    @ApiOperation("修改建筑")
    public ReturnMsg<Building> upd(@RequestBody Building building){
        logger.info("修改建筑building={}", JSONUtil.objectToJson(building));
        return ReturnMsg.success(buildingService.upd(building));
    }
    @GetMapping("del")
    @ApiOperation("删除建筑")
    public ReturnMsg<Building> del(@RequestParam String buildingId){
        logger.info("删除建筑buildingId={}",buildingId);
        return ReturnMsg.success(buildingService.del(buildingId));
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
}

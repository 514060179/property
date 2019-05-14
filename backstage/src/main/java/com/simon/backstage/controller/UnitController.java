package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Building;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.model.UserUnit;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.domain.vo.UnitQueryParam;
import com.simon.backstage.service.BuildingService;
import com.simon.backstage.service.UnitService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.User;
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
import java.util.Objects;

/**
 * @author fengtianying
 * @date 2018/12/10 16:43
 */
@RestController
@RequestMapping("/back/unit")
@Api(value = "UnitController", description = "单元")
public class UnitController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UnitService unitService;
    @Autowired
    private BuildingService buildingService;
    @PostMapping("add")
    @ApiOperation("添加单元")
    public ReturnMsg<Unit> add(@RequestBody Unit unit, HttpServletRequest request){
        logger.info("添加单元unit={}", JSONUtil.objectToJson(unit));
        String communityId = ClaimsUtil.getCommunityId(request);
        if (StringUtils.isEmpty(communityId)){//超级管理员
            if (StringUtils.isEmpty(unit.getCommunityId())){
                Building building = buildingService.detail(unit.getBuildingId());
                unit.setCommunityId(building.getCommunityId());
//                return ReturnMsg.fail(Code.missingParameter,"缺少社区参数communityId");
            }
        }else{
            unit.setCommunityId(communityId);
        }
        return ReturnMsg.success(unitService.add(unit));
    }

    @PostMapping("upd")
    @ApiOperation("修改单元")
    public ReturnMsg<Unit> upd(@RequestBody Unit unit){
        logger.info("修改单元unit={}", JSONUtil.objectToJson(unit));
        return ReturnMsg.success(unitService.upd(unit));
    }

    @GetMapping("del")
    @ApiOperation("删除单元")
    public ReturnMsg<Unit> del(@RequestParam String unitId){
        logger.info("删除单元unitId={}", unitId);
        return ReturnMsg.success(unitService.del(unitId));
    }

    @GetMapping("list")
    @ApiOperation("单元列表")
    public ReturnMsg<PageInfo<Unit>> list(UnitQueryParam unitQueryParam,
                                          HttpServletRequest request){
    	String communityId = ClaimsUtil.getCommunityId(request);
		if(!StringUtils.isEmpty(communityId)){
            unitQueryParam.setCommunityId(communityId);
		}
        logger.info("单元列表baseClaims={}", JSONUtil.objectToJson(unitQueryParam));
        return ReturnMsg.success(unitService.list(unitQueryParam));
    }

    @PostMapping("addUser")
    @ApiOperation("房间住户添加")
    public ReturnMsg<UserUnit> addUser(@RequestBody UserUnit userUnit){
    	logger.info("房间住户添加userUnit={}", JSONUtil.objectToJson(userUnit));
        //查询存在业主
        if (userUnit.getOwner()) {//业主
            if (Objects.isNull(unitService.findUserUnitByUnitId(userUnit.getUnitId()))){
                userUnit.setConvincing(true);
            }
        }
        return ReturnMsg.success(unitService.addUser(userUnit));
    }

//    @PostMapping("updUnitUser")
//    @ApiOperation("修改单元的用户信息")
//    public ReturnMsg updUnitUser(@RequestBody UserUnit userUnit){
//        if (userUnit.getOwner()) {//业主
//            if (!Objects.isNull(unitService.findUserUnitByUnitId(userUnit.getUnitId()))){
//                userUnit.setConvincing(false);
//            }
//        }
//        return ReturnMsg.success(unitService.updUserUnit(userUnit));
//    }

    @PostMapping("batchAddUser")
    @ApiOperation("批量房间住户绑定(只需要设定userId以及unitId)")
    public ReturnMsg batchAddUser(@RequestBody List<UserUnit> userUnitList){
        return ReturnMsg.success(unitService.batchAddUser(userUnitList));
    }
    
    @GetMapping("delUser")
    @ApiOperation("房间住户删除")
    public ReturnMsg delUser(@RequestParam String userId, @RequestParam String unitId){
    	logger.info("房间住户删除unitId={}", JSONUtil.objectToJson(unitId));
    	return ReturnMsg.success(unitService.delUser(unitId, userId));
    }

    @GetMapping("unitUserList")
    @ApiOperation("单元住户列表")
    public ReturnMsg<List<User>> unitUserList(@RequestParam String unitId){
        return ReturnMsg.success(unitService.unitUserList(unitId));
    }
}

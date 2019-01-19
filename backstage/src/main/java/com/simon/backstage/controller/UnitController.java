package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.model.UserUnit;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.UnitService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.vo.BaseClaims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("add")
    @ApiOperation("添加单元")
    public ReturnMsg<Unit> add(@RequestBody Unit unit){
        logger.info("添加单元unit={}", JSONUtil.objectToJson(unit));
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
    public ReturnMsg<PageInfo<Unit>> list(BaseClaims baseClaims, HttpServletRequest request){
    	String communityId = ClaimsUtil.getCommunityId(request);
    	baseClaims.setCommunityId(communityId);
        logger.info("单元列表baseClaims={}", JSONUtil.objectToJson(baseClaims));
        return ReturnMsg.success(unitService.list(baseClaims));
    }

    @PostMapping("addUser")
    @ApiOperation("房间住户添加")
    public ReturnMsg<UserUnit> addUser(@RequestBody UserUnit userUnit){
    	logger.info("房间住户添加userUnit={}", JSONUtil.objectToJson(userUnit));
    	return ReturnMsg.success(unitService.addUser(userUnit));
    }
    
    @GetMapping("delUser")
    @ApiOperation("房间住户删除")
    public ReturnMsg delUser(@RequestParam String userId, @RequestParam String unitId){
    	logger.info("房间住户删除unitId={}", JSONUtil.objectToJson(unitId));
    	return ReturnMsg.success(unitService.delUser(unitId, userId));
    }
}

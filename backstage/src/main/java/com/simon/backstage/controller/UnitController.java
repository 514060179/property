package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.UnitService;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public ReturnMsg<PageInfo<Unit>> list(BaseQueryParam baseQueryParam){
        logger.info("单元列表baseQueryParam={}", JSONUtil.objectToJson(baseQueryParam));
        return ReturnMsg.success(unitService.list(baseQueryParam));
    }

}
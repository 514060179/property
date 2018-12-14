package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Building;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PostMapping("add")
    @ApiOperation("添加建筑")
    public ReturnMsg<Building> add(@RequestBody Building building){
        return ReturnMsg.success(building);
    }
    @PostMapping("upd")
    @ApiOperation("修改建筑")
    public ReturnMsg<Building> upd(@RequestBody Building building){
        return ReturnMsg.success(building);
    }
    @GetMapping("del")
    @ApiOperation("删除建筑")
    public ReturnMsg<Building> del(@RequestParam String buildingId){
        return ReturnMsg.success(buildingId);
    }
    @GetMapping("list")
    @ApiOperation("建筑列表")
    public ReturnMsg<PageInfo<Building>> list(BaseQueryParam baseQueryParam){
        return ReturnMsg.success(baseQueryParam);
    }
}

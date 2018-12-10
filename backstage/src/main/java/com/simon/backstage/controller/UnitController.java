package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Manager;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.msg.BaseQueryParam;
import com.simon.backstage.domain.msg.ReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PostMapping("add")
    @ApiOperation("添加单元")
    public ReturnMsg<Unit> add(@RequestBody Unit unit){
        return ReturnMsg.success();
    }

    @PostMapping("upd")
    @ApiOperation("修改单元")
    public ReturnMsg<Unit> upd(@RequestBody Unit unit){
        return ReturnMsg.success();
    }

    @GetMapping("del")
    @ApiOperation("删除单元")
    public ReturnMsg<Unit> del(@RequestParam String unitId){
        return ReturnMsg.success();
    }

    @GetMapping("list")
    @ApiOperation("单元列表")
    public ReturnMsg<PageInfo<Unit>> list(BaseQueryParam baseQueryParam){
        return ReturnMsg.success();
    }

}

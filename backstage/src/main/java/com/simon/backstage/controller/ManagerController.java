package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Manager;
import com.simon.backstage.domain.msg.BaseQueryParam;
import com.simon.backstage.domain.msg.ReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author fengtianying
 * @date 2018/12/10 14:27
 */
@RestController
@RequestMapping("/back/manager")
@Api(value = "ManagerController", description = "管理员")
public class ManagerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("add")
    @ApiOperation("添加管理员")
    public ReturnMsg<Manager> add(@RequestBody Manager manager){
        return ReturnMsg.success();
    }

    @PostMapping("upd")
    @ApiOperation("修改管理员")
    public ReturnMsg<Manager> upd(@RequestBody Manager manager){
        return ReturnMsg.success();
    }

    @GetMapping("del")
    @ApiOperation("删除管理员")
    public ReturnMsg<Manager> del(@RequestParam String managerId){
        return ReturnMsg.success();
    }

    @GetMapping("list")
    @ApiOperation("管理员列表")
    public ReturnMsg<PageInfo<Manager>> list(BaseQueryParam baseQueryParam){
        return ReturnMsg.success();
    }
}

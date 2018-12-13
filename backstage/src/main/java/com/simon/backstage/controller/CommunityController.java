package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.msg.BaseQueryParam;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.CommunityService;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.Community;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fengtianying
 * @date 2018/12/10 11:38
 */
@RestController
@RequestMapping("/back/community")
@Api(value = "CommunityController", description = "社区管理")
public class CommunityController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommunityService communityService;
    @PostMapping("add")
    @ApiOperation("添加社区")
    public ReturnMsg<Community> add(@RequestBody Community community){
        logger.info("添加社区community={}", JSONUtil.objectToJson(community));
        return ReturnMsg.success(communityService.add(community));
    }

    @PostMapping("upd")
    @ApiOperation("修改社区")
    public ReturnMsg upd(@RequestBody Community community){

        return ReturnMsg.success();
    }

    @GetMapping("del")
    @ApiOperation("删除社区")
    public ReturnMsg del(@RequestParam String communityId){

        return ReturnMsg.success();
    }

    @GetMapping("list")
    @ApiOperation("社区列表")
    public ReturnMsg<PageInfo<Community>> list(BaseQueryParam baseQueryParam){

        return ReturnMsg.success();
    }
}

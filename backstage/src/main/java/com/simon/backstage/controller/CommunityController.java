package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.CommunityService;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.Community;
import com.simon.dal.vo.BaseQueryParam;
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
        logger.info("添加社区community={}", JSONUtil.objectToJson(community));
        return ReturnMsg.success(communityService.upd(community));
    }

    @GetMapping("del")
    @ApiOperation("删除社区")
    public ReturnMsg del(@RequestParam String communityId){
        logger.info("删除社区communityId={}", communityId);
        return ReturnMsg.success(communityService.del(communityId));
    }

    @GetMapping("list")
    @ApiOperation("社区列表")
    public ReturnMsg<PageInfo<Community>> list(BaseQueryParam baseQueryParam){
        logger.info("社区列表baseQueryParam={}",  JSONUtil.objectToJson(baseQueryParam));
        return ReturnMsg.success(communityService.list(baseQueryParam));
    }
    
    @GetMapping("detail")
    @ApiOperation("社区详情")
    public ReturnMsg<Community> detail(String communityId){
    	logger.info("社区列表communityId={}",  JSONUtil.objectToJson(communityId));
    	return ReturnMsg.success(communityService.detail(communityId));
    }
}

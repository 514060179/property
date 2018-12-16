package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.CommunityService;
import com.simon.dal.model.Community;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Notice
 * @author fengtianying
 * @date 2018/11/9 15:48
 */
@RestController
@RequestMapping("/api/community")
@Api(tags = "community", description = "社区")
public class CommunityController {


    @Autowired
    private CommunityService communityService;
    @PostMapping("list")
    @ApiOperation("社区列表")
    public ReturnMsg<PageInfo<Community>> list(BaseQueryParam baseQueryParam){
        return ReturnMsg.success(new PageInfo<>(communityService.list(baseQueryParam)));
    }
}
package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.Building;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.CommunityService;
import com.simon.app.util.ClaimsUtil;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fengtianying
 * @date 2019/2/20 16:54
 */
@RestController
@RequestMapping("/api/building")
@Api(tags = "building", description = "建筑")
public class BuildingController {

    @Autowired
    private CommunityService communityService;

    @PostMapping("list")
    @ApiOperation("建筑列表")
    public ReturnMsg<PageInfo<Building>> list(BaseClaims baseQueryParam,HttpServletRequest request){
        String userId = ClaimsUtil.getUserId(request);
        baseQueryParam.setUserId(userId);
//        baseQueryParam.setCommunityId(baseQueryParam.getCommunityId()==null?communityId:baseQueryParam.getCommunityId());
        return ReturnMsg.success(new PageInfo<>(communityService.buildingList(baseQueryParam)));
    }
}

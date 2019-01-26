package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.PlaceService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.Place;
import com.simon.dal.vo.BaseClaims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/back/place")
@Api(value = "PlaceController", description = "场所管理")
public class PlaceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlaceService placeService;
    @PostMapping("add")
    @ApiOperation("添加场所")
    public ReturnMsg<Place> add(@RequestBody @Validated Place place, HttpServletRequest request){
        logger.info("添加场所place={}", JSONUtil.objectToJson(place));
        if (StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//超级管理员
            if (StringUtils.isEmpty(place.getCommunityId())){
                return ReturnMsg.fail(Code.missingParameter,"缺少社区参数communityId");
            }
        }else{
            place.setCommunityId(ClaimsUtil.getCommunityId(request));
        }
        return ReturnMsg.success(placeService.add(place));
    }

    @PostMapping("upd")
    @ApiOperation("修改场所")
    public ReturnMsg<Place> upd(@RequestBody @Validated Place place, HttpServletRequest request){
        logger.info("修改场所place={}", JSONUtil.objectToJson(place));
        if (!StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//普通管理员
            place.setCommunityId(null);
        }
        return ReturnMsg.success(placeService.upd(place));
    }

    @GetMapping("del")
    @ApiOperation("删除场所")
    public ReturnMsg<Place> del(@RequestParam String placeId){
        logger.info("删除场所placeId={}", placeId);
        return ReturnMsg.success(placeService.del(placeId));
    }

    @GetMapping("list")
    @ApiOperation("场所列表")
    public ReturnMsg<PageInfo<Place>> list(BaseClaims baseClaims, HttpServletRequest request){
    	String communityId = ClaimsUtil.getCommunityId(request);
		if(!StringUtils.isEmpty(communityId)){
			baseClaims.setCommunityId(communityId);
		}
        logger.info("场所列表baseClaims={}", JSONUtil.objectToJson(baseClaims));
        return ReturnMsg.success(placeService.list(baseClaims));
    }
}

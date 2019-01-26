package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Advertisement;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.AdvertisementService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author fengtianying
 * @date 2019/1/25 16:51
 */
@RestController
@RequestMapping("/back/adv")
@Api(value = "AdvertisementController", description = "广告管理")
public class AdvertisementController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdvertisementService advertisementService;
    
    @PostMapping("add")
    @ApiOperation("添加广告")
    public ReturnMsg<Advertisement> add(@RequestBody Advertisement advertisement, HttpServletRequest request){
        logger.info("添加广告advertisement={}", JSONUtil.objectToJson(advertisement));
        if (StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//超级管理员

        }else{
            advertisement.setCommunityId(ClaimsUtil.getCommunityId(request));
        }
        return ReturnMsg.success(advertisementService.add(advertisement));
    }

    @PostMapping("upd")
    @ApiOperation("修改广告")
    public ReturnMsg<Advertisement> upd(@RequestBody Advertisement advertisement){
        logger.info("修改广告advertisement={}", JSONUtil.objectToJson(advertisement));
        return ReturnMsg.success(advertisementService.upd(advertisement));
    }

    @GetMapping("del")
    @ApiOperation("删除广告")
    public ReturnMsg<Advertisement> del(@RequestParam String advId){
        logger.info("添加广告advId={}", advId);
        return ReturnMsg.success(advertisementService.del(advId));
    }

    @GetMapping("list")
    @ApiOperation("广告列表")
    public ReturnMsg<PageInfo<Advertisement>> list(BaseQueryParam baseQueryParam, HttpServletRequest request){
        String communityId = ClaimsUtil.getCommunityId(request);
        if(!StringUtils.isEmpty(communityId)){
            baseQueryParam.setCommunityId(communityId);
        }
        logger.info("广告列表baseQueryParam={}", JSONUtil.objectToJson(baseQueryParam));
        return ReturnMsg.success(advertisementService.list(baseQueryParam));
    }

    @GetMapping("publish")
    @ApiOperation("广告发布/取消")
    public ReturnMsg publish(@RequestParam String advId){
        logger.info("广告发布advId={}", advId);
        Advertisement advertisement = advertisementService.detail(advId);
        if (Objects.isNull(advertisement)){
            return ReturnMsg.fail(Code.notfound,"未找到资源!");
        }
        boolean used = false;
        if (!advertisement.getUsed()){
            used = true;
        }
        return ReturnMsg.success(advertisementService.publish(advId,used));
    }


}

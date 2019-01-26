package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.ChargeItem;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.ChargeItemService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fengtianying
 * @date 2019/1/26 15:59
 */
@RestController
@RequestMapping("/back/chargeItem")
@Api(value = "ChargeItemController", description = "收费项目管理")
public class ChargeItemController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChargeItemService chargeItemService;
    @PostMapping("add")
    @ApiOperation("添加收费项目")
    public ReturnMsg<ChargeItem> add(@RequestBody ChargeItem chargeItem, HttpServletRequest request){
        logger.info("添加收费项目chargeItem={}", JSONUtil.objectToJson(chargeItem));
        if (StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//超级管理员
            if (StringUtils.isEmpty(chargeItem.getCommunityId())){
                return ReturnMsg.fail(Code.missingParameter,"缺少社区参数communityId");
            }
        }else{
            chargeItem.setCommunityId(ClaimsUtil.getCommunityId(request));
        }
        return ReturnMsg.success(chargeItemService.add(chargeItem));
    }

    @PostMapping("upd")
    @ApiOperation("修改收费项目")
    public ReturnMsg upd(@RequestBody ChargeItem chargeItem, HttpServletRequest request){
        logger.info("添加收费项目chargeItem={}", JSONUtil.objectToJson(chargeItem));
        if (!StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//普通管理员
            chargeItem.setCommunityId(null);
        }
        return ReturnMsg.success(chargeItemService.upd(chargeItem));
    }

    @GetMapping("del")
    @ApiOperation("删除收费项目")
    public ReturnMsg del(@RequestParam String chargeItemId){
        logger.info("删除收费项目chargeItemId={}", chargeItemId);
        return ReturnMsg.success(chargeItemService.del(chargeItemId));
    }

    @GetMapping("list")
    @ApiOperation("收费项目列表")
    public ReturnMsg<PageInfo<ChargeItem>> list(BaseQueryParam baseQueryParam, HttpServletRequest request){
        logger.info("收费项目列表baseQueryParam={}",  JSONUtil.objectToJson(baseQueryParam));
        if (StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))) {//超级管理员
        }
        return ReturnMsg.success(chargeItemService.list(baseQueryParam));
    }

    @GetMapping("detail")
    @ApiOperation("收费项目详情")
    public ReturnMsg<ChargeItem> detail(String chargeItemId){
        logger.info("收费项目列表chargeItemId={}",  JSONUtil.objectToJson(chargeItemId));
        return ReturnMsg.success(chargeItemService.detail(chargeItemId));
    }

}

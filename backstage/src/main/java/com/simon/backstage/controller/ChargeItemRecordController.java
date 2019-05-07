package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.annotation.Log;
import com.simon.backstage.annotation.OperateType;
import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.domain.vo.QueryWithIdParam;
import com.simon.backstage.service.ChargeItemRecordService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author fengtianying
 * @date 2019/1/27 14:39
 */
@RestController
@RequestMapping("/back/chargeItemRecord")
@Api(value = "ChargeItemController", description = "收费记录管理")
public class ChargeItemRecordController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChargeItemRecordService chargeItemRecordService;

    @PostMapping("upd")
    @ApiOperation("修改收费记录")
    @Log(description = "修改收费记录",operateType = OperateType.modify)
    public ReturnMsg upd(@RequestBody ChargeItemRecord chargeItemRecord, HttpServletRequest request){
        logger.info("修改收费记录chargeItem={}", JSONUtil.objectToJson(chargeItemRecord));
        return ReturnMsg.success(chargeItemRecordService.upd(chargeItemRecord));
    }

    @GetMapping("del")
    @ApiOperation("删除收费记录")
    @Log(description = "删除收费记录",operateType = OperateType.del)
    public ReturnMsg del(@RequestParam String itemId){
        logger.info("删除收费记录itemId={}", itemId);
        return ReturnMsg.success(chargeItemRecordService.del(itemId));
    }

    @GetMapping("list")
    @ApiOperation("收费记录")
    public ReturnMsg<PageInfo<ChargeItemRecord>> list(QueryWithIdParam queryWithIdParam, HttpServletRequest request){
        logger.info("收费记录列表baseQueryParam={}",  JSONUtil.objectToJson(queryWithIdParam));
        if (!StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//普通管理员
            queryWithIdParam.setCommunityId(ClaimsUtil.getCommunityId(request));
        }
        return ReturnMsg.success(chargeItemRecordService.list(queryWithIdParam));
    }

    @GetMapping("detail")
    @ApiOperation("收费记录详情")
    public ReturnMsg<ChargeItemRecord> detail(String itemId){
        logger.info("收费记录列表itemId={}",  JSONUtil.objectToJson(itemId));
        return ReturnMsg.success(chargeItemRecordService.detail(itemId));
    }

    @PostMapping("charge")
    @ApiOperation("设置为已收费")
    public ReturnMsg<ChargeItemRecord> charge(@RequestBody List<String> recordIdList){
        return ReturnMsg.success(chargeItemRecordService.charge(recordIdList));
    }
}

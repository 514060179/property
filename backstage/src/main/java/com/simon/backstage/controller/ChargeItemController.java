package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.annotation.Log;
import com.simon.backstage.annotation.OperateType;
import com.simon.backstage.domain.model.ChargeItem;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.domain.vo.QueryWithIdParam;
import com.simon.backstage.domain.vo.UnitWithItem;
import com.simon.backstage.service.ChargeItemService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.util.UUIDUtil;
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
        String communityId = ClaimsUtil.getCommunityId(request);
        if (StringUtils.isEmpty(communityId) || ClaimsUtil.isMutilString(communityId) ){//超级管理员
            if (StringUtils.isEmpty(chargeItem.getCommunityId())){
                return ReturnMsg.fail(Code.missingParameter,"缺少社区参数communityId");
            }
        }else{
            chargeItem.setCommunityId(communityId);
        }
        return ReturnMsg.success(chargeItemService.add(chargeItem));
    }

    @PostMapping("upd")
    @ApiOperation("修改收费项目")
    @Log(description = "修改收费项目",operateType = OperateType.modify)
    public ReturnMsg upd(@RequestBody ChargeItem chargeItem, HttpServletRequest request){
        logger.info("添加收费项目chargeItem={}", JSONUtil.objectToJson(chargeItem));
        String communityId = ClaimsUtil.getCommunityId(request);
        if (!StringUtils.isEmpty(communityId) && !ClaimsUtil.isMutilString(communityId)){//普通管理员
            chargeItem.setCommunityId(null);
        }
        return ReturnMsg.success(chargeItemService.upd(chargeItem));
    }

    @GetMapping("del")
    @ApiOperation("删除收费项目")
    @Log(description = "删除收费项目",operateType = OperateType.del)
    public ReturnMsg del(@RequestParam String itemId){
        logger.info("删除收费项目itemId={}", itemId);
        return ReturnMsg.success(chargeItemService.del(itemId));
    }

    @GetMapping("delUnitChargeItem")
    @ApiOperation("删除单元的收费项目")
    public ReturnMsg delUnitChargeItem(@RequestParam String unitItemId){
        logger.info("删除单元的收费项目unitItemId={}", unitItemId);
        return ReturnMsg.success(chargeItemService.delUnitChargeItem(unitItemId));
    }

    @GetMapping("list")
    @ApiOperation("收费项目列表")
    public ReturnMsg<PageInfo<ChargeItem>> list(BaseQueryParam baseQueryParam, HttpServletRequest request){
        logger.info("收费项目列表baseQueryParam={}",  JSONUtil.objectToJson(baseQueryParam));
        String communityId = ClaimsUtil.getCommunityId(request);
        if (!StringUtils.isEmpty(communityId)) {//普通管理员
            baseQueryParam.setCommunityId(communityId);
        }
        return ReturnMsg.success(chargeItemService.list(baseQueryParam));
    }

    @GetMapping("detail")
    @ApiOperation("收费项目详情")
    public ReturnMsg<ChargeItem> detail(String itemId){
        logger.info("收费项目列表itemId={}",  JSONUtil.objectToJson(itemId));
        return ReturnMsg.success(chargeItemService.detail(itemId));
    }

    @PostMapping("unitAddItem")
    @ApiOperation("单元添加收费项目")
    @Log(description = "单元添加收费项目",operateType = OperateType.add)
    public ReturnMsg unitAddItem(@RequestBody List<UnitWithItem> unitWithItemList) {
        logger.info("单元添加收费项目unitWithItem={}", JSONUtil.objectToJson(unitWithItemList));
        unitWithItemList.forEach((unitWithItem)->{
            //获取收费项目
            ChargeItem chargeItem = chargeItemService.findItemIdAndUnitId(unitWithItem.getItemId(),unitWithItem.getUnitId());
            if (chargeItem.getRepeat() == null){
                unitWithItem.setType(chargeItem.getBillingMode());
                unitWithItem.setUnitItemId(UUIDUtil.uidString());
            }
        });
        return ReturnMsg.success(chargeItemService.unitAddItem(unitWithItemList));
    }

    @GetMapping("unitItemList")
    @ApiOperation("单元收费项目列表")
//    @ApiImplicitParam(name="unitId",value="单元id",required=true)
    public ReturnMsg<ChargeItem> unitItemList(QueryWithIdParam queryWithIdParam) {
        logger.info("单元收费项目列表unitWithItem={}", JSONUtil.objectToJson(queryWithIdParam));
        return ReturnMsg.success(chargeItemService.unitItemList(queryWithIdParam));
    }
}

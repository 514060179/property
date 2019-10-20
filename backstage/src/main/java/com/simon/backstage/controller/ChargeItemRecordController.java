package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.annotation.Log;
import com.simon.backstage.annotation.OperateType;
import com.simon.backstage.domain.model.Building;
import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.vo.UnitChargeVo;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.domain.vo.QueryWithIdParam;
import com.simon.backstage.domain.vo.UnitCharges;
import com.simon.backstage.domain.vo.UnitChargesUpdVo;
import com.simon.backstage.service.ChargeItemRecordService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    @Log(description = "修改收费记录", operateType = OperateType.modify)
    public ReturnMsg upd(@RequestBody ChargeItemRecord chargeItemRecord, HttpServletRequest request) {
        logger.info("修改收费记录chargeItem={}", JSONUtil.objectToJson(chargeItemRecord));
        return ReturnMsg.success(chargeItemRecordService.upd(chargeItemRecord));
    }

    @GetMapping("del")
    @ApiOperation("删除收费记录")
    @Log(description = "删除收费记录", operateType = OperateType.del)
    public ReturnMsg del(@RequestParam String itemId) {
        logger.info("删除收费记录itemId={}", itemId);
        return ReturnMsg.success(chargeItemRecordService.del(itemId));
    }

    @GetMapping("list")
    @ApiOperation("收费记录")
    public ReturnMsg<PageInfo<ChargeItemRecord>> list(QueryWithIdParam queryWithIdParam, HttpServletRequest request) {
        logger.info("收费记录列表baseQueryParam={}", JSONUtil.objectToJson(queryWithIdParam));
        if (!StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))) {//普通管理员
            queryWithIdParam.setCommunityId(ClaimsUtil.getCommunityId(request));
        }
        return ReturnMsg.success(chargeItemRecordService.list(queryWithIdParam));
    }

    @GetMapping("unitChargeList")
    @ApiOperation("单元收费列表")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "conmunityId",value = "社区Id",required = true,dataTypeClass = String.class),
                    @ApiImplicitParam(name = "recordType",value = "记录类型0物业费1基金收费2订场收费3其他收费",required = false,dataTypeClass = Integer.class)
            })
    public ReturnMsg<UnitCharges> unitChargeList(@RequestParam String conmunityId,@RequestParam(required = false,defaultValue = "0") int recordType) {
        logger.info("单元收费列表conmunityId={}", JSONUtil.objectToJson(conmunityId));
        return ReturnMsg.success(chargeItemRecordService.unitChargeList(conmunityId,recordType));
    }

    @GetMapping("detail")
    @ApiOperation("收费记录详情")
    public ReturnMsg<ChargeItemRecord> detail(String itemId) {
        logger.info("收费记录列表itemId={}", JSONUtil.objectToJson(itemId));
        return ReturnMsg.success(chargeItemRecordService.detail(itemId));
    }

    @PostMapping("charge")
    @ApiOperation("设置为已收费")
    public ReturnMsg<ChargeItemRecord> charge(@RequestBody List<String> recordIdList) {
        return ReturnMsg.success(chargeItemRecordService.charge(recordIdList));
    }

    @PostMapping("import")
    @ApiOperation("导入收费excel")
   /* @ApiImplicitParams(
            {@ApiImplicitParam(name = "file",value = "file",required = true,dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(name = "communityId",value = "社区id",required = true,dataTypeClass = String.class),
            @ApiImplicitParam(name = "recordType",value = "记录类型0物业费1基金收费2订场收费3其他收费",required = false,dataTypeClass = Integer.class)
            })*/
    public ReturnMsg importExcel(MultipartFile file, @RequestParam String communityId, @RequestParam(required = false, defaultValue = "0") int recordType) {
        //同一个社区不能多次导入
        BaseQueryParam baseQueryParam = new QueryWithIdParam();
        baseQueryParam.setCommunityId(communityId);
        PageInfo<ChargeItemRecord> chargeItemRecordPageInfo = chargeItemRecordService.list(baseQueryParam);
        if (chargeItemRecordPageInfo.getList() != null && !chargeItemRecordPageInfo.getList().isEmpty()) {
            return ReturnMsg.fail(Code.error,"同一个社区不能多次导入");
        } else {
            return ReturnMsg.success(chargeItemRecordService.importExcel(file, communityId, recordType));
        }
    }


    @PostMapping("save")
    @ApiOperation("单元收费新增修改（表格）")
    public ReturnMsg save(@RequestBody UnitChargesUpdVo unitChargeVos){
        chargeItemRecordService.saveList(unitChargeVos.getCommunityId(),unitChargeVos.getRecordType(),unitChargeVos.getUnitChargeVos());
        return ReturnMsg.success();
    }
}
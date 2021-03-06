package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.annotation.Log;
import com.simon.backstage.annotation.OperateType;
import com.simon.backstage.domain.model.ChargeItemRecord;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.domain.vo.PlaceRecordQueryParam;
import com.simon.backstage.service.PlaceRecordService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.dal.model.PlaceRecord;
import com.simon.dal.vo.PlaceRecordStatisData;
import com.simon.dal.vo.PlaceRecordStatisQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author fengtianying
 * @date 2019/4/15 9:15
 */
@RestController
@RequestMapping("/back/placeRecord")
@Api(value = "PlaceRecordController", description = "场所记录管理")
public class PlaceRecordController {

    @Autowired
    private PlaceRecordService placeRecordService;
    @PostMapping("upd")
    @ApiOperation("修改场所记录")
    @Log(description = "修改场所记录",operateType = OperateType.modify)
    public ReturnMsg<PlaceRecord> upd(@RequestBody PlaceRecord placeRecord, HttpServletRequest request){
        String communityId = ClaimsUtil.getCommunityId(request);
        if (!StringUtils.isEmpty(communityId) && !ClaimsUtil.isMutilString(communityId)){//普通管理员
            placeRecord.setCommunityId(null);
        }
        return ReturnMsg.success(placeRecordService.upd(placeRecord));
    }

    @GetMapping("detail")
    @ApiOperation("场所记录详情")
    public ReturnMsg<PlaceRecord> detail(@RequestParam String placeId){
        return ReturnMsg.success(placeRecordService.detail(placeId));
    }

    @GetMapping("del")
    @ApiOperation("删除场所记录")
    @Log(description = "删除场所记录",operateType = OperateType.del)
    public ReturnMsg del(@RequestParam String placeId){
        return ReturnMsg.success(placeRecordService.del(placeId));
    }

    @GetMapping("list")
    @ApiOperation("场所记录列表")
    public ReturnMsg<PageInfo<PlaceRecord>> list(PlaceRecordQueryParam placeRecordQueryParam, HttpServletRequest request){
        String communityId = ClaimsUtil.getCommunityId(request);
        if(!StringUtils.isEmpty(communityId)){
            placeRecordQueryParam.setCommunityId(communityId);
        }
        return ReturnMsg.success(placeRecordService.list(placeRecordQueryParam));
    }

    @GetMapping("statis")
    @ApiOperation("场所使用统计")
    public ReturnMsg<List<PlaceRecordStatisData>> statis(PlaceRecordStatisQuery placeRecordStatisQuery, HttpServletRequest request){
        String communityId = ClaimsUtil.getCommunityId(request);
        if(!StringUtils.isEmpty(communityId)){
            placeRecordStatisQuery.setCommunityId(communityId);
        }
        return ReturnMsg.success(placeRecordService.statis(placeRecordStatisQuery));
    }
    @GetMapping("creChargeItemRecord")
    @ApiOperation("列入物业收费")
    public ReturnMsg creChargeItemRecord(@RequestParam String placeRecordId) {
        //获取定场记录
        PlaceRecord placeRecord = placeRecordService.detail(placeRecordId);
        if (Objects.isNull(placeRecord)) {
            return ReturnMsg.fail(Code.notfound, "未找到资源!");
        }
        //获取住户单元
        Unit unit = placeRecordService.getUnitByUserId(placeRecord.getUserId());
        if (unit == null) {
            return ReturnMsg.fail(Code.notfound, "未找到该住户绑定单元!");
        }
        ChargeItemRecord chargeItemRecord = placeRecordService.getChargeItemRecord(placeRecordId);
        if (chargeItemRecord != null) {
            return ReturnMsg.fail(Code.duplicate, "重复添加！!");
        }
        placeRecordService.creChargeItemRecord(placeRecord.getCommunityId(), placeRecord.getUserId(), placeRecordId, unit, placeRecord.getTotalCharge());
        return ReturnMsg.success();
    }
    @GetMapping("getChargeItemRecord")
    @ApiOperation("查询定场收费记录")
    public ReturnMsg getChargeItemRecord(@RequestParam String placeRecordId){
        return ReturnMsg.success(placeRecordService.getChargeItemRecord(placeRecordId));
    }
}

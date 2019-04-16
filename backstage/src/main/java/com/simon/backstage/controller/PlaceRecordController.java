package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.annotation.Log;
import com.simon.backstage.annotation.OperateType;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.domain.vo.PlaceRecordQueryParam;
import com.simon.backstage.service.PlaceRecordService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.dal.model.PlaceRecord;
import com.simon.dal.vo.PlaceRecordStatisQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
        if (!StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//普通管理员
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
    public ReturnMsg<PageInfo<PlaceRecord>> statis(PlaceRecordStatisQuery placeRecordStatisQuery, HttpServletRequest request){
        String communityId = ClaimsUtil.getCommunityId(request);
        if(!StringUtils.isEmpty(communityId)){
            placeRecordStatisQuery.setCommunityId(communityId);
        }
        return ReturnMsg.success(placeRecordService.statis(placeRecordStatisQuery));
    }
}

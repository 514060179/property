package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.PlaceRecordService;
import com.simon.app.util.ClaimsUtil;
import com.simon.dal.model.Complain;
import com.simon.dal.model.PlaceRecord;
import com.simon.dal.util.UUIDUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Notice
 * @author fengtianying
 * @date 2018/11/9 15:48
 */
@RestController
@RequestMapping("/api/placeRecord")
@Api(tags = "placeRecord", description = "订场记录")
public class PlaceRecordController {

	@Autowired
	private PlaceRecordService placeRecordService;
	
    @PostMapping("list")
    @ApiOperation("我的订场记录")
    public ReturnMsg<PageInfo<PlaceRecord>> list(HttpServletRequest request){
    	String userId = ClaimsUtil.getUserId(request);
    	PlaceRecord placeRecord =new PlaceRecord();
    	placeRecord.setUserId(userId);
        return ReturnMsg.success(new PageInfo<>(placeRecordService.selfList(placeRecord)));
    }
    @PostMapping("detail")
    @ApiOperation("详情")
    public ReturnMsg<PlaceRecord> detail(@RequestParam String recordId){

        return ReturnMsg.success(placeRecordService.findOne(recordId));
    }

    @PostMapping("add")
    @ApiOperation("添加")
    public ReturnMsg<PlaceRecord> add(@RequestBody PlaceRecord placeRecord
    		,HttpServletRequest request){
    	String userId = ClaimsUtil.getUserId(request);
    	placeRecord.setRecordId(UUIDUtil.uidString());
    	placeRecord.setUserId(userId);
    	placeRecordService.addPlaceRecord(placeRecord);
        return ReturnMsg.success(placeRecordService.findOne(placeRecord.getRecordId()));
    }


}

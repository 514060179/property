package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.Code;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.PlaceRecordService;
import com.simon.app.service.PlaceService;
import com.simon.app.util.ClaimsUtil;
import com.simon.app.util.DateUtil;
import com.simon.dal.model.Place;
import com.simon.dal.model.PlaceRecord;
import com.simon.dal.util.UUIDUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

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
	@Autowired
	private PlaceService placeService;
	
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
    public ReturnMsg<PlaceRecord> add(@RequestBody PlaceRecord placeRecord,
    		HttpServletRequest request){
    	String userId = ClaimsUtil.getUserId(request);
    	placeRecord.setRecordId(UUIDUtil.uidString());
    	placeRecord.setUserId(userId);
    	Place place = placeService.findOne(placeRecord.getPlaceId());
    	
    	//场地是否开放
    	if(place==null || place.getPlaceStatus()==0){
    		return ReturnMsg.fail("该场地暂未开放",Code.orderFail);
    	}
    	
    	//查询当天该时间段有没有被预约
    	int count = placeRecordService.findPlaceTime(placeRecord);
    	if(count <= 0){
    		return ReturnMsg.fail("该时间段已被预约",Code.orderFail);
    	}
    	
    	//场地需要提前多少天
    	Integer advance = place.getPlaceAdvanceOrderDay();
    	int day = DateUtil.getDifferentMillisecond(new Date(),
    			placeRecord.getOrderDate(),"day");
    	if(day < 0){
    		return ReturnMsg.fail("请选择合法的日期时间",Code.orderFail);
    	}
    	if(day < advance){
    		return ReturnMsg.fail("要提前"+advance+"天预约",Code.orderFail);
    	}
    	
    	//场地预约时间上限
    	Integer limit = place.getPlaceUpperLimit();
    	int hour = DateUtil.getDifferentMillisecond(placeRecord.getOrderStartTime(),
    			placeRecord.getOrderEndTime(),"hour");
    	if(limit < hour){
    		return ReturnMsg.fail("该场地预约时间最大"+limit+"小时",Code.orderFail);
    	}
        return ReturnMsg.success(placeRecordService.addPlaceRecord(placeRecord));
    }
}

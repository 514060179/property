package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.dal.model.Complain;
import com.simon.dal.model.PlaceRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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


    @PostMapping("list")
    @ApiOperation("我的订场记录")
    public ReturnMsg<PageInfo<PlaceRecord>> list(){

        return null;
    }
    @PostMapping("detail")
    @ApiOperation("详情")
    public ReturnMsg<PlaceRecord> detail(@RequestParam String recordId){

        return null;
    }

    @PostMapping("add")
    @ApiOperation("添加")
    public ReturnMsg<PlaceRecord> add(@RequestBody PlaceRecord PlaceRecord){

        return null;
    }


}

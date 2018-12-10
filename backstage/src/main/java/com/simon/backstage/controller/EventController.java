package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Event;
import com.simon.backstage.domain.msg.BaseQueryParam;
import com.simon.backstage.domain.msg.ReturnMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author fengtianying
 * @date 2018/12/10 16:55
 */
@RestController
@RequestMapping("/back/event")
@Api(value = "EventController", description = "事件管理")
public class EventController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("add")
    @ApiOperation("添加事件")
    public ReturnMsg<Event> add(@RequestBody Event event){
        return ReturnMsg.success();
    }

    @PostMapping("upd")
    @ApiOperation("修改事件")
    public ReturnMsg<Event> upd(@RequestBody Event event){
        return ReturnMsg.success();
    }

    @GetMapping("del")
    @ApiOperation("删除事件")
    public ReturnMsg<Event> del(@RequestParam String unitId){
        return ReturnMsg.success();
    }

    @GetMapping("list")
    @ApiOperation("事件列表")
    public ReturnMsg<PageInfo<Event>> list(BaseQueryParam baseQueryParam){
        return ReturnMsg.success();
    }

}

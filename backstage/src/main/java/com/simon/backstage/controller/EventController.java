package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Event;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.EventService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EventService eventService;
    @PostMapping("add")
    @ApiOperation("添加事件")
    public ReturnMsg<Event> add(@RequestBody Event event, HttpServletRequest request){
    	event.setCommunityId(ClaimsUtil.getCommunityId(request));
        logger.info("添加事件event={}", JSONUtil.objectToJson(event));
        return ReturnMsg.success(eventService.add(event));
    }

    @PostMapping("upd")
    @ApiOperation("修改事件")
    public ReturnMsg<Event> upd(@RequestBody Event event){
        logger.info("修改事件event={}", JSONUtil.objectToJson(event));
        return ReturnMsg.success(eventService.upd(event));
    }

    @GetMapping("del")
    @ApiOperation("删除事件")
    public ReturnMsg<Event> del(@RequestParam String eventId){
        logger.info("删除事件eventId={}", eventId);
        return ReturnMsg.success(eventService.del(eventId));
    }

    @GetMapping("list")
    @ApiOperation("事件列表")
    public ReturnMsg<PageInfo<Event>> list(BaseQueryParam baseQueryParam){
        logger.info("事件列表baseQueryParam={}", JSONUtil.objectToJson(baseQueryParam));
        return ReturnMsg.success(eventService.list(baseQueryParam));
    }

}

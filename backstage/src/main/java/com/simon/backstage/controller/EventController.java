package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Event;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.EventService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.EventQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
        logger.info("添加事件event={}", JSONUtil.objectToJson(event));
        if (StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//超级管理员
            if (StringUtils.isEmpty(event.getCommunityId())){
                return ReturnMsg.fail(Code.missingParameter,"缺少社区参数communityId");
            }
        }else{
            event.setCommunityId(ClaimsUtil.getCommunityId(request));
        }
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
    public ReturnMsg<PageInfo<Event>> list(EventQueryParam eventQueryParam, HttpServletRequest request){
        if (StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//超级管理员

        }else{
            eventQueryParam.setCommunityId(ClaimsUtil.getCommunityId(request));
        }
        logger.info("事件列表eventQueryParam={}", JSONUtil.objectToJson(eventQueryParam));
        return ReturnMsg.success(eventService.list(eventQueryParam));
    }

    @GetMapping("changeStatus")
    @ApiOperation("处理事件")
    @ApiImplicitParam(name="eventStatus", value="事件进度:1待定2完成", required=true)
    public ReturnMsg changeStatus(@RequestParam String eventId, String eventStatus,
    		HttpServletRequest request){
    	logger.info("处理事件eventId={}", JSONUtil.objectToJson(eventId));
    	Event event = new Event();
    	event.setEventId(eventId);
    	event.setEventStatus(Integer.parseInt(eventStatus));
    	return ReturnMsg.success(eventService.changeStatus(event));
    }
}

package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.Visitor;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.VisitorService;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fengtianying
 * @date 2018/12/10 16:46
 */
@RestController
@RequestMapping("/back/visitor")
@Api(value = "VisitorController", description = "访问者管理")
public class VisitorController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VisitorService visitorService;
    @GetMapping("list")
    @ApiOperation("访问者列表")
    public ReturnMsg<PageInfo<Visitor>> list(BaseQueryParam baseQueryParam){
        return ReturnMsg.success(visitorService.list(baseQueryParam));
    }
}

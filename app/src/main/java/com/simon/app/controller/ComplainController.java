package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.dal.model.Complain;
import com.simon.dal.model.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Notice
 * @author fengtianying
 * @date 2018/11/9 15:48
 */
@RestController
@RequestMapping("/api/complain")
@Api(tags = "complain", description = "投诉/报修")
public class ComplainController {


    @PostMapping("list")
    @ApiOperation("我的投诉/报修")
    public ReturnMsg<PageInfo<Complain>> list(){

        return null;
    }
    @PostMapping("detail")
    @ApiOperation("详情")
    public ReturnMsg<Complain> detail(@RequestParam String complainId){

        return null;
    }

    @PostMapping("add")
    @ApiOperation("添加")
    public ReturnMsg<Complain> add(@RequestBody Complain complain){

        return null;
    }


}

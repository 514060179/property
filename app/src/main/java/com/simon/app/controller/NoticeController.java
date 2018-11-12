package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.model.vo.UserWithToken;
import com.simon.dal.model.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Notice
 * @author fengtianying
 * @date 2018/11/9 15:48
 */
@RestController
@RequestMapping("/api/notice")
@Api(tags = "notice", description = "公告")
public class NoticeController {


    @PostMapping("list")
    @ApiOperation("公告列表")
    public ReturnMsg<PageInfo<Notice>> list(){

        return null;
    }
    @PostMapping("detail")
    @ApiOperation("公告详情")
    public ReturnMsg<Notice> detail(@RequestParam String noticeId){

        return null;
    }

}

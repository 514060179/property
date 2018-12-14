package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.UserService;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.User;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fengtianying
 * @date 2018/12/11 14:31
 */
@RestController
@RequestMapping("/back/user")
@Api(value = "UserController", description = "住户")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("add")
    @ApiOperation("添加住户")
    public ReturnMsg<User> add(@RequestBody User user){
        logger.info("添加住户user={}", JSONUtil.objectToJson(user));
        return ReturnMsg.success(userService.add(user));
    }

    @PostMapping("upd")
    @ApiOperation("修改住户")
    public ReturnMsg<User> upd(@RequestBody User user){
        return ReturnMsg.success(userService.upd(user));
    }

    @GetMapping("del")
    @ApiOperation("删除住户")
    public ReturnMsg<User> del(@RequestParam String userId){
        return ReturnMsg.success(userService.del(userId));
    }

    @GetMapping("list")
    @ApiOperation("住户列表")
    public ReturnMsg<PageInfo<User>> list(BaseQueryParam baseQueryParam){
        return ReturnMsg.success(userService.list(baseQueryParam));
    }
}

package com.simon.app.controller;

import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.UserService;
import com.simon.app.util.ClaimsUtil;
import com.simon.dal.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Notice
 * @author fengtianying
 * @date 2018/11/9 15:48
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "user", description = "用户")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("detail")
    @ApiOperation("详情")
    public ReturnMsg<User> detail(HttpServletRequest request){
        return ReturnMsg.success(userService.findOne(ClaimsUtil.getUserId(request)));
    }
}

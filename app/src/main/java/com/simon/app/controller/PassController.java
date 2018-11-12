package com.simon.app.controller;

import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.model.vo.UserWithToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fengtianying
 * @date 2018/11/9 15:48
 */
@RestController
@RequestMapping("pass")
@Api(tags = "pass", description = "通行")
public class PassController {


    @PostMapping("login")
    @ApiOperation("用户登录")
    public ReturnMsg<UserWithToken> login(@RequestParam String username, @RequestParam String password){

        return null;
    }

}

package com.simon.app.controller;

import com.simon.app.config.Audience;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.model.vo.UserWithToken;
import com.simon.app.service.UserService;
import com.simon.app.util.JwtHelper;
import com.simon.dal.config.RedisService;
import com.simon.dal.model.User;
import com.simon.dal.util.EncryUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private UserService userService;
	@Autowired
	private Audience audience;
	@Autowired
	private RedisService redis;
	
    @PostMapping("login")
    @ApiOperation("用户登录")
    public ReturnMsg<UserWithToken> login(@RequestParam String username, @RequestParam String password){
    	ReturnMsg<UserWithToken> msg = new ReturnMsg<UserWithToken>();
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(EncryUtil.getMD5(password));
 		User result = userService.findUser(user);
 		if(result != null){//存在该用户名
 			UserWithToken userToken = new UserWithToken();
 			String token = JwtHelper.createJWT(result.getUsername(), result.getUserId(),
 	 				result.getCommunityId(), -1L, audience.getBase64Secret());
 			redis.set(result.getUserId(), token, 600);//有效期十分钟
 			userToken.setToken(token);
 			userToken.setUser(result);
 			msg.setData(userToken);
 			return ReturnMsg.success(userToken);
 		}else{
 			return ReturnMsg.loginFail();
 		}
    }
}

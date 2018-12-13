package com.simon.app.controller;

import com.simon.app.config.Audience;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.model.vo.UserWithToken;
import com.simon.app.service.UserService;
import com.simon.app.util.EncryUtil;
import com.simon.app.util.JwtHelper;
import com.simon.dal.model.User;

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
	
    @PostMapping("login")
    @ApiOperation("用户登录")
    public ReturnMsg<UserWithToken> login(@RequestParam String username, @RequestParam String password){
    	ReturnMsg<UserWithToken> msg = new ReturnMsg<UserWithToken>();
    	String upassword = EncryUtil.getMD5(password);
    	if(!username.isEmpty() && !upassword.isEmpty()){
 			User result = userService.findUser(username,upassword);
 			if(result != null){//存在该用户名
 				UserWithToken userToken = new UserWithToken();
 				String token = JwtHelper.createJWT(result.getUsername(), result.getUserId(), result.getCommunityId(),
 						-1L, audience.getBase64Secret());//登陆成功生成token
 				userToken.setToken(token);
 				userToken.setUserId(result.getUserId());
 				msg.setData(userToken);
 				return ReturnMsg.success(userToken);
 			}
    	}
		return ReturnMsg.fail();
    }
}

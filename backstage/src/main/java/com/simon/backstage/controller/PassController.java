package com.simon.backstage.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simon.backstage.domain.model.Manager;
import com.simon.backstage.domain.model.ManagerWithToken;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.ManagerService;
import com.simon.backstage.shiro.config.Audience;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JwtHelper;
import com.simon.dal.config.RedisService;
import com.simon.dal.util.EncryUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pass")
@Api(value = "LoginController", description = "通行")
public class PassController {
	
	@Autowired
    private ManagerService managerService;
	@Autowired
	private Audience audience;
	@Autowired
	private RedisService redisService;
	
	@PostMapping("login")
	@ApiOperation("用户登陆")
	public ReturnMsg<ManagerWithToken> login(@RequestParam String username,@RequestParam String password){
    	Manager manager = new Manager();
    	manager.setUsername(username);
    	manager.setPassword(EncryUtil.getMD5(password));
    	Manager result = managerService.findManager(manager);
    	if(result != null){
    		String roles = managerService.findManagerAndRole(result.getManagerId());
    		String token = JwtHelper.issueJwt(UUID.randomUUID().toString(), result.getManagerId(),
    				result.getUsername(), result.getCommunityId(), 24*3600*1000L, roles, null, 
    				audience.getBase64Secret());
    		ManagerWithToken withToken = new ManagerWithToken();
    		withToken.setManager(result);
    		withToken.setToken(token);
    		redisService.set(result.getManagerId(), token, 60*15);
    		return ReturnMsg.success(withToken);
    	}
    	return ReturnMsg.fail(Code.loginfail, "账号或密码错误");
    }
	
	@PostMapping("logout")
	@ApiOperation("注销登录")
	public ReturnMsg logout(HttpServletRequest request, @RequestParam(required=false) String userId){
		if(!StringUtils.isEmpty(userId)&&redisService.hasKey(userId)){
			redisService.del(userId);
			return ReturnMsg.success();
		}
		return ReturnMsg.fail(Code.logoutfail, "注销失败");
	}
}
package com.simon.backstage.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simon.backstage.domain.model.Manager;
import com.simon.backstage.domain.model.ManagerWithToken;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.ManagerService;
import com.simon.backstage.shiro.config.Audience;
import com.simon.backstage.util.JwtHelper;
import com.simon.dal.util.EncryUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pass")
@Api(value = "LoginController", description = "通行")
public class LoginController {
	
	@Autowired
    private ManagerService managerService;
	@Autowired
	private Audience audience;
	
	@GetMapping("login")
	@ApiOperation("用户登陆")
	public ReturnMsg<ManagerWithToken> login(@RequestParam String username,@RequestParam String password){
    	Manager manager = new Manager();
    	manager.setUsername(username);
    	manager.setPassword(EncryUtil.getMD5(password));
    	Manager result = managerService.findManager(manager);
    	if(result != null){
    		String roles = managerService.findManagerAndRole(result.getManagerId());
    		String token = JwtHelper.issueJwt(UUID.randomUUID().toString(), result.getManagerId(),
    				result.getUsername(), result.getCommunityId(), 600*1000L, roles, null, 
    				audience.getBase64Secret());
    		ManagerWithToken withToken = new ManagerWithToken();
    		withToken.setManager(result);
    		withToken.setToken(token);
    		return ReturnMsg.success(withToken);
    	}
    	return ReturnMsg.fail(Code.nologin, "账号或密码错误");
    }
}

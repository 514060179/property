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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
	@ApiImplicitParam(name="deviceType", value="终端类型：触摸屏1，PC端可省略")
	public ReturnMsg<ManagerWithToken> login(@RequestParam String username,
			@RequestParam String password,String deviceType){
    	Manager manager = new Manager();
    	manager.setUsername(username);
    	manager.setPassword(EncryUtil.getMD5(password));
    	Manager result = managerService.findManager(manager);
    	if(result != null){
    		String roles = managerService.findManagerAndRole(result.getManagerId());
    		Long time = (long) 15*60*1000;//PC端token有效期为15分钟
    		if(!StringUtils.isEmpty(deviceType)&&deviceType.equals("1")){
    			time = null;//触摸屏：空则不会过期
    		}
    		String token = JwtHelper.issueJwt(UUID.randomUUID().toString(), result.getManagerId(),
    				result.getUsername(), result.getCommunityId(), time, roles, null,
    				audience.getBase64Secret());
    		ManagerWithToken withToken = new ManagerWithToken();
    		withToken.setManager(result);
    		withToken.setToken(token);
    		redisService.set(result.getManagerId(), token);
    		return ReturnMsg.success(withToken);
    	}
    	return ReturnMsg.fail(Code.loginfail, "账号或密码错误");
    }
	
	@PostMapping("/back/logout")
	@ApiOperation("注销登录")
	public ReturnMsg logout(HttpServletRequest request){
		String managerId = ClaimsUtil.getManagerId(request);
		if(!StringUtils.isEmpty(managerId) && redisService.hasKey(managerId)){
			redisService.del(managerId);
			return ReturnMsg.success();
		}
		return ReturnMsg.fail(Code.logoutfail, "注销失败");
	}

	@PostMapping("/back/updatePassword")
	@ApiOperation("修改密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name="password",value="旧密码",required=true),
			@ApiImplicitParam(name="newpassword",value="新密码",required=true)
	})
	public ReturnMsg changePossword(String password, String newpassword, HttpServletRequest request){
		String managerId = ClaimsUtil.getManagerId(request);
		Manager manager = new Manager();
		manager.setManagerId(managerId);
		manager.setPassword(EncryUtil.getMD5(password));
		Manager result = managerService.findManager(manager);
		if(!StringUtils.isEmpty(result)&&result!=null){
			result.setPassword(EncryUtil.getMD5(newpassword));
			managerService.upd(manager);
			return ReturnMsg.success();
		}
		return ReturnMsg.fail(Code.wrongPassword, "旧密码不正确");
	}
}

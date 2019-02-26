package com.simon.app.controller;

import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.UserService;
import com.simon.app.util.ClaimsUtil;
import com.simon.dal.config.RedisService;
import com.simon.dal.model.User;
import com.simon.dal.util.EncryUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired
	private RedisService redisService;
    @PostMapping("update")
    @ApiOperation("用户修改")
    public ReturnMsg<User> update(@RequestBody User user, HttpServletRequest request){
    	String userId = ClaimsUtil.getUserId(request);
    	user.setUserId(userId);
		return ReturnMsg.success(userService.updateByPrimaryKeySelective(user));
    }

    @PostMapping("detail")
    @ApiOperation("用户详情")
    public ReturnMsg<User> detail(HttpServletRequest request){
        return ReturnMsg.success(userService.findOne(ClaimsUtil.getUserId(request)));
    }
    
    @PostMapping("updatePassword")
    @ApiOperation("修改密码")
    @ApiImplicitParams({
    	@ApiImplicitParam(name="password",value="旧密码",paramType="query"),
    	@ApiImplicitParam(name="newpassword",value="新密码",paramType="query")
    })
    public ReturnMsg updatePassword(@RequestParam String password,
    		@RequestParam String newpassword, HttpServletRequest request){
    	String userId = ClaimsUtil.getUserId(request);
    	User user = new User();
    	user.setUserId(userId);
    	user.setPassword(EncryUtil.getMD5(password));
    	User result = userService.findUser(user);
    	if(result != null){
    		result.setPassword(EncryUtil.getMD5(newpassword));
    		if(userService.updateByPrimaryKeySelective(result)>0){
    			//移除redis
				redisService.del(userId);
			}
    	}else{
    		return ReturnMsg.wrongPassword();
    	}
    	return ReturnMsg.success();
    }
}

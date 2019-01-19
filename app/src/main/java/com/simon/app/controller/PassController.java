package com.simon.app.controller;

import com.simon.app.component.LogComponetAop;
import com.simon.app.config.Audience;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.model.vo.UserWithToken;
import com.simon.app.service.UserService;
import com.simon.app.util.JwtHelper;
import com.simon.dal.config.RedisService;
import com.simon.dal.model.User;
import com.simon.dal.util.EncryUtil;

import com.simon.dal.util.JPushUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("login")
    @ApiOperation("用户登录")
    public ReturnMsg<UserWithToken> login(@RequestParam String username, @RequestParam String password,@RequestParam String registrationId ){
    	ReturnMsg<UserWithToken> msg = new ReturnMsg<UserWithToken>();
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(EncryUtil.getMD5(password));
 		User result = userService.findUser(user);
 		if(result != null){//存在该用户名
 			UserWithToken userToken = new UserWithToken();
 			String token = JwtHelper.createJWT(result.getUsername(), result.getUserId(),
 	 				result.getCommunityId(), -1L, audience.getBase64Secret());
 			redis.set(result.getUserId(), token, 60*60*24);//有效期一天
 			userToken.setToken(token);
 			userToken.setUser(result);
 			msg.setData(userToken);
 			//设定推送标签和别名
			String alias = result.getUserId();
			String tag = result.getCommunityId();
			new Thread(()->{
				setPushInfo(registrationId,tag,alias);
			}).start();
 			return ReturnMsg.success(userToken);
 		}else{
 			return ReturnMsg.loginFail();
 		}
    }

    private void setPushInfo(String registrationId,String tag,String alias){
    	//删除该设备所有绑定信息的别名
		if (!JPushUtil.delDeviceAlias(alias)){
			logger.error("给设备{}删除所有别名{}失败",registrationId,alias);
			return;
		}
		logger.info("{}设置推送标签{}/推送别名{}",registrationId,tag,alias);
		if (JPushUtil.addDeviceAlias(registrationId,alias)){
			logger.error("{}设置推送别名{}失败",registrationId,alias);
		}
		if (JPushUtil.addDeviceTag(registrationId,tag)){
			logger.error("{}设置推送标签{}失败",registrationId,tag);
		}
	}
}

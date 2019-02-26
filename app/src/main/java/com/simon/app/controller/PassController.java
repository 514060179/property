package com.simon.app.controller;

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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

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

	@Autowired
	private JavaMailSender javaMailSender;

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
	@PostMapping("resetPassword")
	@ApiOperation("重置密码")
	public ReturnMsg resetPassword(@RequestParam String username){
		User user = new User();
		user.setUsername(username);
		User result = userService.findUser(user);
		if(result != null) {//存在该用户名
			if (Objects.isNull(result.getEmail())){
				return ReturnMsg.noSetEmail();
			}else{
				//1.更新密码
				//2.发送邮箱
				User update = new User();
				update.setUserId(result.getUserId());
				int pwd = (int)(Math.random()*1000000);
				update.setPassword(EncryUtil.getMD5(pwd+""));
				if (userService.updateByPrimaryKeySelective(update)>0){
					emialSendHtml(result.getEmail(),pwd+"");
					redis.del(result.getUserId());
					return ReturnMsg.success();
				}else{
					return ReturnMsg.fail();
				}
			}
		}else{
			return ReturnMsg.accountNoExit();
		}
	}

	private void setPushInfo(String registrationId,String tag,String alias){
		//删除该设备所有绑定信息的别名
		if (!JPushUtil.delDeviceAlias(registrationId)){
			logger.error("给设备{}删除所有别名{}失败",registrationId,alias);
//			return;
		}
		logger.info("{}设置推送标签{}/推送别名{}",registrationId,tag,alias);
		if (!JPushUtil.addDeviceAlias(registrationId,alias)){
			logger.error("{}设置推送别名{}失败",registrationId,alias);
		}
		if (!JPushUtil.addDeviceTag(registrationId,tag)){
			logger.error("{}设置推送标签{}失败",registrationId,tag);
		}
	}

	private void emialSendHtml(String email,String pwd) {
		MimeMessage message = null;
		try {
			message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("2245702722@qq.com");
			helper.setTo(email);
			helper.setSubject("密碼重置");

			StringBuffer sb = new StringBuffer();
			sb.append("<h2 text-align:center >bms物業管理系統APP密碼重置</h2>")
//                    .append("<p style='color:#F00'>红色字</p>")
					.append("<p style='text-align:left'>   您的重置密碼為:").append(pwd).append("</p>").append("<p style='text-align:right'>bms物業管理系統</p>");
			helper.setText(sb.toString(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
	}
}

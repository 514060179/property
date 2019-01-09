package com.simon.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simon.app.config.Audience;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.VisitorService;
import com.simon.app.util.JwtHelper;
import com.simon.dal.config.RedisService;
import com.simon.dal.vo.VisitorWithToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/app/visitor")
@Api(tags="visitor", description="访问者")
public class VisitorController {
	
	@Autowired
	private VisitorService visitorService;
	@Autowired
	private Audience audience;
	@Autowired
	private RedisService redisService;
	
	@PostMapping("record")
	@ApiOperation("访问者登记")
	public ReturnMsg<VisitorWithToken> record(@RequestBody VisitorWithToken visitor){
		int record = visitorService.record(visitor);
		if(record > 0){
			String token = JwtHelper.createJWT(visitor.getVisitorName(), visitor.getVisitorId(),
					visitor.getCommunityId(), -1L, audience.getBase64Secret());
			visitor.setToken(token);
			redisService.set(visitor.getVisitorId(), token, 600);
			return ReturnMsg.success(visitor);
		}
		return ReturnMsg.fail();
	}
}

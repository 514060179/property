package com.simon.backstage.controller;

import javax.servlet.http.HttpServletRequest;

import com.simon.backstage.domain.model.*;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.service.*;
import com.simon.backstage.shiro.config.Audience;
import com.simon.backstage.util.JwtHelper;
import com.simon.dal.config.RedisService;
import com.simon.dal.model.Community;
import com.simon.dal.util.EncryUtil;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.ApiImplicitParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.Notice;
import com.simon.dal.vo.BaseClaims;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.UUID;

@RestController
@Api(value="1.TouchController", description="触摸屏管理")
public class TouchController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private VisitorService visitorService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private CommunityService communityService;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private Audience audience;
	@Autowired
	private AdvertisementService advertisementService;


	@GetMapping("touch/community/list")
	@ApiOperation("社区列表")
	public ReturnMsg<PageInfo<Community>> communityList(BaseQueryParam baseQueryParam){
		logger.info("社区列表baseQueryParam={}",  JSONUtil.objectToJson(baseQueryParam));
		return ReturnMsg.success(communityService.list(baseQueryParam));
	}

	@GetMapping("touch/building/list")
	@ApiOperation("建筑列表")
	public ReturnMsg<PageInfo<Building>> buildingList(BaseClaims baseClaims, String communityId, HttpServletRequest request) {
		logger.info("建筑列表baseClaims={}", JSONUtil.objectToJson(baseClaims));
		baseClaims.setCommunityId(communityId);
		return ReturnMsg.success(buildingService.list(baseClaims));
	}
	@PostMapping("touch/login")
	@ApiOperation("登陆")
	public ReturnMsg<ManagerWithToken> login(@RequestParam String username,
											 @RequestParam String password,@RequestParam String communityId,@RequestParam String buildingId){
		Manager manager = new Manager();
		manager.setUsername(username);
		manager.setPassword(EncryUtil.getMD5(password));
		Manager result = managerService.findManager(manager);
		if(result != null){
			String token = JwtHelper.issueJwt(UUID.randomUUID().toString(), result.getManagerId(),
					result.getUsername(), communityId, "1", null, buildingId, null,
					audience.getBase64Secret());
			ManagerWithToken withToken = new ManagerWithToken();
			withToken.setManager(result);
			withToken.setToken(token);
			redisService.set(result.getManagerId(), token);//不过期
			return ReturnMsg.success(withToken);
		}
		return ReturnMsg.fail(Code.loginfail, "账号或密码错误");
	}

	@PostMapping("/back/touch/visitorAdd")
	@ApiOperation("访问者登记")
	public ReturnMsg<Visitor> visitorAdd(@RequestBody Visitor visitor,
										 HttpServletRequest request){
		String communityId = ClaimsUtil.getCommunityId(request);
		visitor.setCommunityId(communityId);
		logger.info("touch访问者登记visitor={}", JSONUtil.objectToJson(visitor));
		return ReturnMsg.success(visitorService.add(visitor));
	}

	@GetMapping("/back/touch/list")
	@ApiOperation("公告列表")
	public ReturnMsg<PageInfo<Notice>> noticeList(BaseClaims baseClaims, String buildingId, HttpServletRequest request){
		String communityId = ClaimsUtil.getCommunityId(request);
		if(!StringUtils.isEmpty(communityId)){
			baseClaims.setCommunityId(communityId);
		}
		if(!StringUtils.isEmpty(buildingId) && buildingId.trim()!=""){
			baseClaims.setBuildingId(buildingId);
		}
		logger.info("公告列表baseClaims={}", JSONUtil.objectToJson(baseClaims));
		return ReturnMsg.success(new PageInfo<>(noticeService.list(baseClaims)));
	}
	@GetMapping("/back/touch/noticeDetail")
	@ApiOperation("公告详情")
	public ReturnMsg<Notice> noticeDetail(@RequestParam String noticeId){
		logger.info("touch公告详情noticeId={}", JSONUtil.objectToJson(noticeId));
		return ReturnMsg.success(noticeService.findOne(noticeId));
	}

	@GetMapping("/back/touch/advList")
	@ApiOperation("广告列表")
	public ReturnMsg<Advertisement> advList(HttpServletRequest request){
		logger.info("touch广告列表");
		String buildingId = ClaimsUtil.getBuildingId(request);
		String communityId = ClaimsUtil.getCommunityId(request);
		return ReturnMsg.success(advertisementService.touchLlist(communityId,buildingId));
	}

}

package com.simon.backstage.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.simon.backstage.domain.model.Visitor;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.NoticeService;
import com.simon.backstage.service.VisitorService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.Notice;
import com.simon.dal.vo.BaseClaims;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/back/touch")
@Api(value="1.TouchController", description="触摸屏管理")
public class TouchController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private VisitorService visitorService;
	@Autowired
	private NoticeService noticeService;
	
	@PostMapping("visitorAdd")
	@ApiOperation("访问者登记")
	public ReturnMsg<Visitor> visitorAdd(@RequestBody Visitor visitor,
			HttpServletRequest request){
    	String communityId = ClaimsUtil.getCommunityId(request);
    	visitor.setCommunityId(communityId);
    	logger.info("访问者登记visitor={}", JSONUtil.objectToJson(visitor));
		return ReturnMsg.success(visitorService.add(visitor));
	}
	
	@GetMapping("noticeList")
	@ApiOperation("公告列表")
	public ReturnMsg<PageInfo<Notice>> list(BaseClaims baseClaims, String buildingId, HttpServletRequest request){
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
	
	@GetMapping("noticeDetail")
	@ApiOperation("公告详情")
	public ReturnMsg<Notice> noticeDetail(@RequestParam String noticeId){
		logger.info("公告详情noticeId={}", JSONUtil.objectToJson(noticeId));
		return ReturnMsg.success(noticeService.findOne(noticeId));
	}
}

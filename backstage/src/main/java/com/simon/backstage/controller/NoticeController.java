package com.simon.backstage.controller;

import javax.servlet.http.HttpServletRequest;

import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.vo.NoticeQueryParam;
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
import com.simon.backstage.service.NoticeService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.Notice;
import com.simon.dal.vo.BaseClaims;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/back/notice")
@Api(value="NoticeController", description="公告管理")
public class NoticeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticeService noticeService;
	
	@PostMapping("add")
	@ApiOperation("添加公告")
	public ReturnMsg<Notice> add(@RequestBody Notice notice, HttpServletRequest request){
		logger.info("添加公告notice={}", JSONUtil.objectToJson(notice));
		if (StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//超级管理员

		}else{
			notice.setCommunityId(ClaimsUtil.getCommunityId(request));
		}
		return ReturnMsg.success(noticeService.add(notice));
	}
	
	@GetMapping("del")
	@ApiOperation("删除公告")
	public ReturnMsg del(@RequestParam String noticeId){
		logger.info("删除公告noticeId={}", JSONUtil.objectToJson(noticeId));
		return ReturnMsg.success(noticeService.del(noticeId));
	}
	
	@PostMapping("upd")
	@ApiOperation("修改公告")
	public ReturnMsg upd(@RequestBody Notice notice, HttpServletRequest request){
		logger.info("修改公告notice={}", JSONUtil.objectToJson(notice));
		if (!StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//普通管理员
			notice.setCommunityId(null);
		}
		return ReturnMsg.success(noticeService.upd(notice));
	}
	
	@GetMapping("list")
	@ApiOperation("公告列表")
	public ReturnMsg<PageInfo<Notice>> list(NoticeQueryParam baseClaims, String buildingId, HttpServletRequest request){
		String communityId = ClaimsUtil.getCommunityId(request);
		if(!StringUtils.isEmpty(communityId)){
			baseClaims.setCommunityId(communityId);
		}
		logger.info("公告列表baseClaims={}", JSONUtil.objectToJson(baseClaims));
		return ReturnMsg.success(noticeService.list(baseClaims));
	}
	
	@GetMapping("detail")
	@ApiOperation("公告详情")
	public ReturnMsg<Notice> detail(@RequestParam String noticeId){
		logger.info("公告详情noticeId={}", JSONUtil.objectToJson(noticeId));
		return ReturnMsg.success(noticeService.findOne(noticeId));
	}
}

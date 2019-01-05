package com.simon.backstage.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		String communityId = ClaimsUtil.getCommunityId(request);
		notice.setCommunityId(communityId);
		logger.info("添加公告notice={}", JSONUtil.objectToJson(notice));
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
	public ReturnMsg upd(@RequestBody Notice notice){
		logger.info("修改公告notice={}", JSONUtil.objectToJson(notice));
		return ReturnMsg.success(noticeService.upd(notice));
	}
	
	@GetMapping("list")
	@ApiOperation("公告列表")
	public ReturnMsg<PageInfo<Notice>> list(BaseClaims baseClaims, HttpServletRequest request){
		String communityId = ClaimsUtil.getCommunityId(request);
		baseClaims.setCommunityId(communityId);
		logger.info("公告列表baseClaims={}", JSONUtil.objectToJson(baseClaims));
		return ReturnMsg.success(noticeService.list(baseClaims));
	}
}

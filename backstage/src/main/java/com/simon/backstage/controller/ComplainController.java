package com.simon.backstage.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.ComplainService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.Complain;
import com.simon.dal.vo.BaseClaims;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("back/complain")
@Api(value="ComplainController",description="投诉/报修管理")
public class ComplainController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ComplainService complainService;
	
	@GetMapping("list")
	@ApiOperation("投诉/报修列表")
	public ReturnMsg<PageInfo<Complain>> list(BaseClaims baseClaims, HttpServletRequest request){
		String communityId = ClaimsUtil.getCommunityId(request);
		if(!StringUtils.isEmpty(communityId)){
			baseClaims.setCommunityId(communityId);
		}
		logger.info("投诉/报修列表baseClaims={}", JSONUtil.objectToJson(baseClaims));
		return ReturnMsg.success(new PageInfo<>(complainService.list(baseClaims)));
	}
	
	@GetMapping("changStatus")
	@ApiOperation("处理投诉/报修")
	@ApiImplicitParam(name="complainStatus", value="状态:1收到2处理中3处理完成", paramType="query")
	public ReturnMsg changStatus(@RequestParam String complainId,
			String complainStatus, HttpServletRequest request){
		String managerId = ClaimsUtil.getManagerId(request);
		Complain complain = new Complain();
		complain.setComplainId(complainId);
		complain.setComplainStatus(complainStatus);
		return ReturnMsg.success(complainService.changStatus(complain, managerId));
	}
}

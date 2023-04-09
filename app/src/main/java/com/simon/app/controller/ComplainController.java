package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.ComplainService;
import com.simon.app.util.ClaimsUtil;
import com.simon.app.util.JSONUtil;
import com.simon.dal.model.Complain;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Notice
 * @author fengtianying
 * @date 2018/11/9 15:48
 */
@RestController
@RequestMapping("/api/complain")
@Api(tags = "complain", description = "投诉/报修")
public class ComplainController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ComplainService complainService;
	
    @PostMapping("selfList")
    @ApiOperation("我的投诉/报修")
    public ReturnMsg<PageInfo<Complain>> selfList(HttpServletRequest request, BaseClaims baseClaims){ 
    	String userId = ClaimsUtil.getUserId(request);
    	baseClaims.setUserId(userId);
        return ReturnMsg.success(new PageInfo<>(complainService.list(baseClaims)));
    }
    
    @PostMapping("detail")
    @ApiOperation("详情")
    public ReturnMsg<Complain> detail(@RequestParam String complainId){

        return ReturnMsg.success(complainService.findOne(complainId));
    }

    @PostMapping("add")
    @ApiOperation("添加")
    public ReturnMsg<Complain> add(@RequestBody Complain complain,HttpServletRequest request){
        logger.info("添加获取参数：{}", JSONUtil.objectToJson(complain));
        complain.setComplainId(UUIDUtil.uidString());
        complain.setUserId(ClaimsUtil.getUserId(request));
        complain.setCommunityId(ClaimsUtil.getCommunityId(request));
        complain.setComplainStatus("2");
    	complainService.addComplain(complain);
    	return ReturnMsg.success(complain);
    }
}

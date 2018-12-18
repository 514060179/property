package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.ComplainService;
import com.simon.app.util.ClaimsUtil;
import com.simon.dal.model.Complain;
import com.simon.dal.model.Notice;
import com.simon.dal.util.UUIDUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	@Autowired
	private ComplainService complainService;
	
    @PostMapping("list")
    @ApiOperation("我的投诉/报修")
    public ReturnMsg<PageInfo<Complain>> list(HttpServletRequest request){
    	Complain complain = new Complain();
    	String userId = ClaimsUtil.getUserId(request);
    	complain.setUserId(userId);
        return ReturnMsg.success(new PageInfo<>(complainService.selfList(complain)));
    }
    
    @PostMapping("detail")
    @ApiOperation("详情")
    public ReturnMsg<Complain> detail(@RequestParam String complainId){

        return ReturnMsg.success(complainService.findOne(complainId));
    }

    @PostMapping("add")
    @ApiOperation("添加")
    public ReturnMsg<Complain> add(@RequestBody Complain complain){
    	complain.setComplainId(UUIDUtil.uidString());
    	int id = complainService.addComplain(complain);
    	return ReturnMsg.success(complainService.findOne(complain.getComplainId()));
    }


}

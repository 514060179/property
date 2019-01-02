package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.ComplainService;
import com.simon.app.util.ClaimsUtil;
import com.simon.dal.model.Complain;
import com.simon.dal.util.UUIDUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

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
	
    @PostMapping("selfList")
    @ApiOperation("我的投诉/报修")
    public ReturnMsg<PageInfo<Complain>> selfList(HttpServletRequest request){ 
    	String userId = ClaimsUtil.getUserId(request);
        return ReturnMsg.success(new PageInfo<>(complainService.selfList(userId)));
    }
    
    @PostMapping("detail")
    @ApiOperation("详情")
    public ReturnMsg<Complain> detail(@RequestParam String complainId){

        return ReturnMsg.success(complainService.findOne(complainId));
    }

    @PostMapping("add")
    @ApiOperation("添加")
    @ApiImplicitParam(name="paths",value="图片路径（如上传多次文件以“,”隔开）",paramType="query")
    public ReturnMsg<Complain> add(@RequestBody Complain complain,String paths,
    		HttpServletRequest request){
    	complain.setComplainId(UUIDUtil.uidString());
    	complain.setUserId(ClaimsUtil.getUserId(request));
    	complainService.addComplain(complain,paths);
    	return ReturnMsg.success(complainService.findOne(complain.getComplainId()));
    }
}

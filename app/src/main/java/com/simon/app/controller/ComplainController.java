package com.simon.app.controller;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.ComplainService;
import com.simon.app.service.ImageService;
import com.simon.app.util.ClaimsUtil;
import com.simon.dal.model.Complain;
import com.simon.dal.model.Image;
import com.simon.dal.model.Notice;
import com.simon.dal.util.UUIDUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Iterator;
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
	@Autowired
	private ImageService imageService;
	
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
    public ReturnMsg<Complain> add(@RequestBody Complain complain,@RequestParam String paths){
    	complain.setComplainId(UUIDUtil.uidString());
    	complainService.addComplain(complain);
    	if(paths != "" && paths != null){
    		String[] path = paths.split(",");
    		List<Image> list = new ArrayList<Image>();
    		for (String url : path) {
    			Image image = new Image();
    			image.setComplainId(complain.getComplainId());
				image.setImageId(UUIDUtil.uidString());
				image.setImageUrl(url);
				list.add(image);
			}
    		imageService.insertBatch(list);
    	}
    	return ReturnMsg.success(complainService.findOne(complain.getComplainId()));
    }
}

package com.simon.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.NoticeService;
import com.simon.app.util.ClaimsUtil;
import com.simon.dal.model.Notice;
import com.simon.dal.vo.BaseClaims;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Notice
 * @author fengtianying
 * @date 2018/11/9 15:48
 */
@RestController
@RequestMapping("/api/notice")
@Api(tags = "notice", description = "公告")
public class NoticeController {

	@Autowired
	private NoticeService noticeSercice;

    @PostMapping("list")
    @ApiOperation("公告列表")
    public ReturnMsg<PageInfo<Notice>> list(BaseClaims baseClaims, HttpServletRequest request){
    	String communityId = ClaimsUtil.getCommunityId(request);
    	baseClaims.setCommunityId(communityId);
        return ReturnMsg.success(new PageInfo<>(noticeSercice.list(baseClaims)));
    }
    @PostMapping("detail")
    @ApiOperation("公告详情")
    public ReturnMsg<Notice> detail(@RequestParam String noticeId){
    	
        return ReturnMsg.success(noticeSercice.findOne(noticeId));
    }

}

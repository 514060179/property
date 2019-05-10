package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.service.UserService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.User;
import com.simon.dal.model.UserWithCommunity;
import com.simon.dal.vo.BaseClaims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fengtianying
 * @date 2018/12/11 14:31
 */
@RestController
@RequestMapping("/back/user")
@Api(value = "UserController", description = "住户")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("add")
    @ApiOperation("添加住户")
    public ReturnMsg<User> add(@RequestBody User user, HttpServletRequest request){
        logger.info("添加住户user={}", JSONUtil.objectToJson(user));
        if (StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//超级管理员
            if (StringUtils.isEmpty(user.getCommunityId())&&user.getUserWithCommunities().size()>0){
                return ReturnMsg.fail(Code.missingParameter,"缺少社区参数communityId");
            }
        }else{
            user.setCommunityId(ClaimsUtil.getCommunityId(request));
        }
        return ReturnMsg.success(userService.add(user));
    }

    @PostMapping("upd")
    @ApiOperation("修改住户")
    public ReturnMsg<User> upd(@RequestBody User user, HttpServletRequest request){
        logger.info("修改住户user={}", JSONUtil.objectToJson(user));
        if (!StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){//普通管理员
            user.setCommunityId(null);
            user.setUserWithCommunities(null);
        }
        return ReturnMsg.success(userService.upd(user));
    }

    @GetMapping("del")
    @ApiOperation("删除住户")
    public ReturnMsg<User> del(@RequestParam String userId){
        logger.info("删除住户userId={}", userId);
        return ReturnMsg.success(userService.del(userId));
    }

    @GetMapping("detail")
    @ApiOperation("住户详情")
    public ReturnMsg<User> detail(@RequestParam String userId){
        return ReturnMsg.success(userService.detail(userId));
    }

    @GetMapping("delUserCommunity")
    @ApiOperation("删除用户社区")
    public ReturnMsg<User> delUserCommunity(@RequestParam String userId,@RequestParam String communityId){
        return ReturnMsg.success(userService.delUserCommunity(userId,communityId));
    }

    @PostMapping("addUserCommunity")
    @ApiOperation("添加绑定用户社区")
    public ReturnMsg<User> addUserCommunity(@RequestBody List<UserWithCommunity> userWithCommunityList){
        if (userWithCommunityList != null && userWithCommunityList.size() > 0) {
            return ReturnMsg.success(userService.addUserCommunity(userWithCommunityList));
        }
        return ReturnMsg.fail(Code.missingParameter, "userWithCommunityList参数为空");
    }


    @GetMapping("list")
    @ApiOperation("住户列表")
    public ReturnMsg<PageInfo<User>> list(BaseClaims baseClaims, HttpServletRequest request){
    	String communityId = ClaimsUtil.getCommunityId(request);
		if(!StringUtils.isEmpty(communityId)){
			baseClaims.setCommunityId(communityId);
		}
        logger.info("住户列表baseQueryParam={}", JSONUtil.objectToJson(baseClaims));
        return ReturnMsg.success(userService.list(baseClaims));
    }
}

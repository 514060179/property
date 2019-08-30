package com.simon.backstage.controller;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.backstage.domain.vo.ExcelData;
import com.simon.backstage.service.UserService;
import com.simon.backstage.util.ClaimsUtil;
import com.simon.backstage.util.ExcelUtils;
import com.simon.backstage.util.JSONUtil;
import com.simon.dal.model.User;
import com.simon.dal.model.UserWithCommunity;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.BaseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            if (StringUtils.isEmpty(user.getCommunityId())&&user.getUserWithCommunities().size()<0){
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
    @GetMapping("export")
    @ApiOperation("导出")
    public void export(BaseQueryParam baseQueryParam,HttpServletRequest request, HttpServletResponse response){
    	String communityId = ClaimsUtil.getCommunityId(request);
		if(!StringUtils.isEmpty(communityId)){
            baseQueryParam.setCommunityId(communityId);
		}
        ExcelData data = new ExcelData();
        data.setName("住戶");
        List<String> titles = new ArrayList();
        titles.add("社區編號");
        titles.add("社區名稱");
        titles.add("ID");
        titles.add("名稱");
        titles.add("英文名稱");
        titles.add("手機號碼");
        titles.add("郵箱");
        titles.add("身分證");
        titles.add("登陸帳號");
        titles.add("登陸密碼");
        titles.add("婚姻制度");
        titles.add("配偶名稱");
        titles.add("單位");
        data.setTitles(titles);
        List<Map<String,Object>> mapList = userService.excelUserList(baseQueryParam);
        List<List<Object>> rows = new ArrayList();
        mapList.forEach(map->{
            List<Object> objectList = new ArrayList<>();
            objectList.add(map.get("community_no"));
            objectList.add(map.get("community_name"));
            objectList.add(map.get("user_id"));
            objectList.add(map.get("name"));
            objectList.add(map.get("english_name"));
            objectList.add(map.get("tel"));
            objectList.add(map.get("email"));
            objectList.add(map.get("id_card"));
            objectList.add(map.get("username"));
            objectList.add(map.get("password"));
            objectList.add(map.get("marriage_system"));
            objectList.add(map.get("mate_name"));
            objectList.add(map.get("unit_name"));
            rows.add(objectList);
        });
        data.setRows(rows);
        try {
            ExcelUtils.exportExcel(response,"bms澳門物業住戶",data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("澳門物業住戶導出失敗！",e);
        }
    }

    @PostMapping("import")
    @ApiOperation("导入业主")
    @ApiImplicitParam(name = "file",value = "资源文件(字节码)",paramType = "payload")
    public ReturnMsg importExcel(HttpServletRequest request,String communityId) throws IOException {
        if (StringUtils.isEmpty(communityId)&&StringUtils.isEmpty(ClaimsUtil.getCommunityId(request))){
            return ReturnMsg.fail(Code.missingParameter,"缺少參數communityId");
        }
        return ReturnMsg.success(userService.importExcel(request,communityId));
    }
}
